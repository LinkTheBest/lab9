package server;

import client.Colors;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ToClientMessageHandler {
    private Socket clientSocket;

    public ToClientMessageHandler(Socket socket){
        clientSocket = socket;
    }

    public void send(MessageToClient message) throws IOException {
        System.out.println(message.getMessage());
        BufferedOutputStream outputStream = new BufferedOutputStream(clientSocket.getOutputStream());
        System.out.println("FFFFFFFFFF");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        System.out.println("FFFFFFFFFF");
        objectOutputStream.writeObject(message);
        System.out.println("FFFFFFFFFF");
        objectOutputStream.flush();
        objectOutputStream.close();
        clientSocket.close();
    }


}
