package server;

import client.Colors;
import client.FromServerMessageHandler;
import commandsRealization.Command;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class FromClientMessageHandler {
    private Socket socket;

    public FromClientMessageHandler(Socket socket) {
        this.socket = socket;
    }

    public Command getMessage() throws IOException, ClassNotFoundException {
        BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Command command = (Command) objectInputStream.readObject();
        System.out.print(Colors.CYAN_BOLD);
        System.out.println("Была получена команда:" + command.getCommand());
        objectInputStream.close();
        socket.close();
        return command;
    }
}
