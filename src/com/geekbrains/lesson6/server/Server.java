package com.geekbrains.lesson6.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.awt.EventQueue.invokeLater;

public class Server {

    private final int SERVER_PORT = 8189;

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ExecutorService executorService;

    public Server() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Сервер запущен, ожидаем подключения...");

            socket = serverSocket.accept();
            System.out.println("Клиент подключился");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            executorService = Executors.newSingleThreadExecutor();

            receiveMsg(executorService);
            sendMsg(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) serverSocket.close();
            if (socket != null) socket.close();
            executorService.shutdown();
        }
    }

    private void receiveMsg(ExecutorService service) {
        service.execute(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String msgFromClient = in.readUTF();
                    System.out.println("Client: " + msgFromClient);
                    if (msgFromClient.equalsIgnoreCase("/end")) System.exit(0);
                } catch (IOException e) {
                    System.out.println("Connection was closed!");
                    break;
                }
            }
        });
    }

    private void sendMsg(DataOutputStream out) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String msgToClient = reader.readLine();
            out.writeUTF(msgToClient);
            if (msgToClient.equalsIgnoreCase("/end")) break;
        }
    }

    public static void main(String[] args) {
        invokeLater(Server::new);
    }
}

