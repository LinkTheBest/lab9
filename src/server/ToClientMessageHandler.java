package server;

import client.Colors;
import spaceMarineProperties.SpaceMarine;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ToClientMessageHandler {
    private Socket clientSocket;

    public ToClientMessageHandler(Socket socket){
        clientSocket = socket;
    }

    public String send(MessageToClient message) throws IOException {

        OutputStream outputStream = new BufferedOutputStream(clientSocket.getOutputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
        if(!message.getMessage().equals("")) {
            return "Клиенту отправлено сообщение:" + message.getMessage();
        } else {
            return "Отправлено клиенту";
        }
    }

}
