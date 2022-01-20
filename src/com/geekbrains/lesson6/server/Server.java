package com.geekbrains.lesson6.server;

import com.geekbrains.lesson6.Сommunicatable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.awt.EventQueue.invokeLater;

public class Server implements Сommunicatable {

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

            receiveMsg(executorService, in, "Client");
            sendMsg(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) serverSocket.close();
            if (socket != null) socket.close();
            executorService.shutdown();
        }
    }

    public static void main(String[] args) {
        invokeLater(Server::new);
    }
}

