package com.geekbrains.lesson6.server;

import com.geekbrains.lesson6.Connectable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Connectable {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String clientMsg = in.readUTF();
                if (clientMsg.equalsIgnoreCase("/end"))
                    Connectable.closeConnection(socket, out, in);
                System.out.println(clientMsg);
                Connectable.sendMessage(out);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

