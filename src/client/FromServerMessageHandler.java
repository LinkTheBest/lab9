package client;

import server.MessageToClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class FromServerMessageHandler {
    private Socket socket;
    private MessageToClient message;

    public FromServerMessageHandler(Socket socket) {
        this.socket = socket;
    }

    public MessageToClient getMessage() {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            message = (MessageToClient) objectInputStream.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return message;
    }
}
