package com.javarush.task.task30.task3008;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен!");
            while (true){
                new Handler(serverSocket.accept()).start();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static class Handler extends Thread {
        Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Connection connection :
                connectionMap.values()) {
            try {
                connection.send(message);
            }
            catch (IOException e){
                ConsoleHelper.writeMessage(message.getData());
            }
        }
    }
}
