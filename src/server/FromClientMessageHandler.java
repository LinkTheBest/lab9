package server;

import client.Colors;
import client.ConnectionChecker;
import client.FromServerMessageHandler;
import commandsRealization.Command;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FromClientMessageHandler {
    private ConnectionChecker connectionChecker;
    private Socket clientSocket;

    public FromClientMessageHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
    }

    public Command getMessage() throws IOException, ClassNotFoundException {
        BufferedInputStream inputStream = new BufferedInputStream(clientSocket.getInputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Command command = (Command) objectInputStream.readObject();
        System.out.print(Colors.GREEN_BOLD);
        System.out.println("\nБыла получена команда:" + command.getCommand());
        return command;
    }
}
