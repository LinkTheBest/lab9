package com.gnida.izkadetov;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ToServerMessageHandler {
    private Socket clientSocket;
    private int port;

    public ToServerMessageHandler(Socket clientSocket, int port) {
        this.port = port;
        this.clientSocket = clientSocket;
    }

    public Socket sendMessage(Command command) {
        while (true) {
            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(clientSocket.getOutputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(command);
                objectOutputStream.flush();
                break;
            } catch (IOException e) {
                System.out.println(Colors.RED_BOLD);
                System.out.println("Не удалось отправить сообщение, повторяю попытку...");
                try {
                    clientSocket = new Socket("localhost", port);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
        return clientSocket;
    }
}

