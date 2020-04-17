package client;

import server.MessageToClient;

import java.io.*;
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

    public String returnedMessage() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        if(reader.readLine() == null){
            return "";
        }
        return reader.readLine();
    }
}
