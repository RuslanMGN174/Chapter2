package com.geekbrains.lesson6;

import java.io.*;
import java.net.Socket;

public interface Connectable {

    static void sendMessage(DataOutputStream out) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            out.writeUTF(reader.readLine());
        } catch (IOException e) {
            System.err.println("Ошибка отправки сообщения");
            e.printStackTrace();
        }
    }

    static void closeConnection(Socket socket, DataOutputStream out, DataInputStream in) {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
