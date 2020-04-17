package server;

import client.Colors;
import spaceMarineProperties.SpaceMarine;

import java.io.*;
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
        if(message.getMessage().equals("")) {
            return "Отправлено клиенту\n";
        } else {
            return "Клиенту отправлено сообщение:" + message.getMessage() + "\n";
        }
    }

    public void send() throws IOException {
        OutputStreamWriter outputStream = new OutputStreamWriter(clientSocket.getOutputStream());
        outputStream.write("Отправка сообщения отменена");
    }

}
