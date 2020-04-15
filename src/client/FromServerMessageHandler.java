package client;

import server.MessageToClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class FromServerMessageHandler {
    private Socket socket;


    public FromServerMessageHandler(Socket socket) {
        this.socket = socket;
    }

    public MessageToClient getMessage() throws IOException, ClassNotFoundException {

        BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        MessageToClient message = (MessageToClient) objectInputStream.readObject();
        System.out.print(Colors.CYAN_BOLD);
        System.out.println("Была получена message:" + message.getMessage());
        objectInputStream.close();
        socket.close();

        return message;
    }
}
