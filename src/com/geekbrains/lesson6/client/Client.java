package com.geekbrains.lesson6.client;

import com.geekbrains.lesson6.Connectable;

import java.io.*;
import java.net.Socket;

import static java.awt.EventQueue.invokeLater;

public class Client implements Connectable {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    Connectable.sendMessage(out);
                    String serverMsg = in.readUTF();
                    if (serverMsg.equalsIgnoreCase("/end"))
                        Connectable.closeConnection(socket, out, in);
                    System.out.println(serverMsg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        invokeLater(Client::new);
    }
}
