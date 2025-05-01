package server;

import commands.Command;
import database.DatabaseHandler;
import database.User;
import exceptions.BuildObjectException;
import managers.CommandManager;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());
    private DatagramSocket ds;
    private final int port = 6789;
    private CommandManager commandManager;
    private CommandData commandData;
    private DatabaseHandler databaseHandler;

    private final ExecutorService cachedThreadPool;
    private final ForkJoinPool forkJoinPool;

    // Поля для хранения информации о клиенте
    private InetAddress clientAddress;
    private int clientPort;

    public Server(CommandData commandData, DatabaseHandler databaseHandler) {
        this.commandData = commandData;
        this.databaseHandler = databaseHandler;
        try {
            ds = new DatagramSocket(port);
            logger.info("Server started and listening on port " + port);
        } catch (Exception e) {
            logger.severe("Error occurred while starting the server: " + e.getMessage());
            e.printStackTrace();
        }

        cachedThreadPool = Executors.newCachedThreadPool();
        forkJoinPool = new ForkJoinPool();
    }

    public void run() {
        try {
            this.commandManager = commandData.getManager();
            while (true) {
                byte[] buffer = new byte[1048576];
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                ds.receive(dp);

                // Сохраняем информацию о клиенте
                clientAddress = dp.getAddress();
                clientPort = dp.getPort();

                // Создаем новый поток для обработки запроса
                Thread requestThread = new Thread(() -> handleRequest(dp));
                requestThread.start();
            }
        } catch (Exception e) {
            logger.severe("Error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }

    private void handleRequest(DatagramPacket dp) {
        cachedThreadPool.submit(() -> {
            try {
                ByteArrayInputStream byteInput = new ByteArrayInputStream(dp.getData());
                ObjectInputStream objectInput = new ObjectInputStream(byteInput);
                Object receivedObject = objectInput.readObject();

                logger.info("Received object of type: " + receivedObject.getClass());
                if (receivedObject instanceof Command) {
                    //((Command) receivedObject).initialize(commandData.getCsvManager(), this);
                    ((Command) receivedObject).execute(commandData);
                    //commandManager.executeCommand((Command) receivedObject);
                } else if (receivedObject.getClass().toString().contains("database.")) {
                    User user = (User) receivedObject;
                    if (user.getRegFlag() == 0) {
                        databaseHandler.signUpUser(user);
                        if (databaseHandler.getSignUpFlag()) {
                            sendMessageToClient("Пользователь успешно зарегистрирован!");
                            databaseHandler.setSignUpFlag(false);
                            user.setRegFlag(3);
                            commandData.setUser(user); //////////////////////////////////////////////////////////////////
                        } else {
                            sendMessageToClient("Кажется, вы уже зарегистрированы!");
                        }
                    } else if (user.getRegFlag() == 1) {
                        databaseHandler.loginUser(user);
                        if (databaseHandler.getLoginFlag()) {
                            sendMessageToClient("Вы успешно вошли!");
                            user.setRegFlag(3);
                            databaseHandler.setLoginFlag(false);
                            commandData.setUser(user); ////////////////////////////////////////////////////////////////
                        } else {
                            sendMessageToClient("Такой пользователь еще не зарегистрирован!");
                        }
                    }
                }
            } catch (Exception e) {
                logger.severe("Error occurred: " + e.getMessage());
                e.printStackTrace();
            } catch (BuildObjectException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void sendMessageToClient(String message) {
        forkJoinPool.execute(() -> {
            try {
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                ds.send(sendPacket);
            } catch (Exception e) {
                logger.severe("Error occurred while sending message to client: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}

