import client.Client;
import client.ClientData;
import commands.CommandData;
import commands.CommandExecuter;
import managers.CSVManager;
import managers.CommandManager;
import database.DataBaseCommandExecutor;
import database.User;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        Client client;
            try {
                CSVManager csvManager = new CSVManager();
                ClientData clientData = new ClientData();
                client = new Client(clientData);
                CommandManager commandManager = new CommandManager(csvManager, client);
                CommandExecuter executer = new CommandExecuter(commandManager);
                CommandData commandData = new CommandData();
                Scanner scanner = new Scanner(System.in);
                DataBaseCommandExecutor dataBaseCommandExecutor = new DataBaseCommandExecutor();

                commandManager.regCommands();
                dataBaseCommandExecutor.registerAllCommands();

                User user;
                while (true) {
                    System.out.println("У Вас уже есть аккаунт? \n" + "Введите \"2\" , если вы новенький. \n" + "Введите \"1\" , если вы уже смешарик.");
                    String command = scanner.nextLine();
                    user = dataBaseCommandExecutor.executeCommand(command.trim());
                    commandData.setUser(user);
                    if (user != null) {
                        client.sendObjectToServer(user);
                        client.receivedObjectFromServer();
                        if (clientData.getRegFlag() && command.equals("1") && client.getFlag()) {user.setRegFlag(3); break;}
                        else if (clientData.getLoginFlag() && command.equals("2") && client.getFlag()) {user.setRegFlag(3); break;}
                        clientData.setRegFlag(true);
                        clientData.setLoginFlag(true);
                    }
                }

                while (true){
                    System.out.println("Введите команду:");
                    client.sendObjectToServer(user);
                    executer.startExecuting(System.in);
                    client.receivedObjectFromServer();
                }

            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}