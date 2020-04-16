package client;

import server.MessageToClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class FromServerMessageHandler {
    private Socket clientSocket;


    public FromServerMessageHandler(Socket socket) {
        clientSocket = socket;
    }

    public MessageToClient getMessage() throws IOException, ClassNotFoundException {
        BufferedInputStream inputStream = new BufferedInputStream(clientSocket.getInputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        MessageToClient message = (MessageToClient) objectInputStream.readObject();
        return message;
    }
}
