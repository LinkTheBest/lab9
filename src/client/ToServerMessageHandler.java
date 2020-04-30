package client;

import ComandPack.Command;

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
                System.out.print(Colors.GREEN_BOLD);
                //System.out.println("Отправлено на сервер: " + command.getCommand());
                objectOutputStream.flush();
                break;
            } catch (IOException e) {
                System.out.println(Colors.RED_BOLD);
                System.out.println("Не удалось отправить сообщение, повторяю попытку...");
                try {
                    clientSocket = new Socket("localhost", port);
                } catch (IOException ex) {
                }

            }
        }
        return clientSocket;
    }
}

