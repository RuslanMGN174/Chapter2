package com.geekbrains.lesson6.client;

import com.geekbrains.lesson6.Сommunicatable;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.awt.EventQueue.invokeLater;

public class Client implements Сommunicatable {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ExecutorService executorService;


    public Client() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            System.out.println("Client started...");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            executorService = Executors.newSingleThreadExecutor();

            receiveMsg(executorService, in, "Server");
            sendMsg(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) socket.close();
            executorService.shutdown();
        }
    }

    public static void main(String[] args) {
        invokeLater(Client::new);
    }
}
