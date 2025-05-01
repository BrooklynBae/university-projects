package client;

import managers.CSVManager;
import model.StudyGroup;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Objects;
import java.util.Stack;

public class Client {
    private DatagramSocket ds;
    private InetAddress host;
    private int port = 6789;
    private boolean flag = false;
    private String message;
    private ClientData clientData;
    private byte[] recievedData;
    private DatagramPacket responsePacket;

    public Client(ClientData clientData) throws IOException {
        this.clientData = clientData;
        ds = new DatagramSocket();
        host = InetAddress.getByName("localhost");
    }

    public Boolean getFlag() {
        return flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public <T> void sendObjectToServer(T object) {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(object);
 //           objectStream.flush();
            byte[] serializedData = byteStream.toByteArray();
            DatagramPacket dp = new DatagramPacket(serializedData, serializedData.length, host, port);
            ds.send(dp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receivedObjectFromServer() {
        try {
            flag = false;
            while (true) {
                ds.setSoTimeout(500);
                byte[] responseData = new byte[1048576];
                responsePacket = new DatagramPacket(responseData, responseData.length);
                ds.receive(responsePacket);

                recievedData = responsePacket.getData();

                printReceivedData();

            }
        } catch (SocketTimeoutException e) {
            if (flag) System.out.println("✅ Сервер закончил отправку данных.");
            else System.out.println("❌ Сервер временно не доступен ИЛИ ваш запрос введен неверно.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printReceivedData() {
        try {
            flag = true;
            String receivedMessage = new String(recievedData, 0, responsePacket.getLength());
            setMessage(receivedMessage);

            if (receivedMessage.equals("Кажется, вы уже зарегистрированы!")) {
                clientData.setRegFlag(false);
            }

            if (receivedMessage.equals("Такой пользователь еще не зарегистрирован!")) {
                clientData.setRegFlag(false);
            }
            System.out.println(receivedMessage);
        } catch (NullPointerException e) {
            System.out.println("От сервера не поступало никаких ответов.");
        }
    }

}