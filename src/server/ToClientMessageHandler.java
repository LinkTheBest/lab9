package server;

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
            return "Отправлено клиенту";
        } else {
            return "Клиенту отправлено сообщение:" + message.getMessage() + "\n";
        }
    }


}
