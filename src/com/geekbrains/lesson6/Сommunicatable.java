package com.geekbrains.lesson6;

import java.io.*;
import java.util.concurrent.ExecutorService;

public interface Сommunicatable {

    void openConnection() throws IOException;

    default void receiveMsg(ExecutorService service, DataInputStream in, String fromWhomMsg) {
        service.execute(() -> {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    String msgFromClient = in.readUTF();
                    System.out.printf("%s: %s\n", fromWhomMsg, msgFromClient);
                    if (msgFromClient.equalsIgnoreCase("/end")) System.exit(0);
                } catch (IOException e) {
                    System.out.println("Connection was closed!");
                    break;
                }
            }
        });
    }

    default void sendMsg(DataOutputStream out) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String msgToServer = reader.readLine();
            out.writeUTF(msgToServer);
            if (msgToServer.equalsIgnoreCase("/end")) break;
        }
    }
}
