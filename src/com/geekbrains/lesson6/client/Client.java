package com.geekbrains.lesson6.client;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.awt.EventQueue.invokeLater;

public class Client {

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

            receiveMsg(executorService);
            sendMsg(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) socket.close();
            executorService.shutdown();
        }
    }

    private void receiveMsg(ExecutorService service) {
        service.execute(() -> {
            while (!Thread.currentThread().isInterrupted()){
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
            String msgToServer = reader.readLine();
            out.writeUTF(msgToServer);
            if (msgToServer.equalsIgnoreCase("/end")) break;
        }
    }

    public static void main(String[] args) {
        invokeLater(Client::new);
    }
}
