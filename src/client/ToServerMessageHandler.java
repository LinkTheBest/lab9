package client;

import commandsRealization.Command;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ToServerMessageHandler {
    private Socket socket;

    public ToServerMessageHandler(Socket socket) {
        this.socket = socket;
    }

    public void sendMessage(Command command) throws IOException {
        BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(command);
        System.out.print(Colors.GREEN_BOLD);
        System.out.println("Отправлено на сервер: " + command.getCommand());
        objectOutputStream.flush();
       // objectOutputStream.close();

    }
}
