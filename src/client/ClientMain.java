package client;

import commandsRealization.Command;
import server.MessageToClient;
import spaceMarineProperties.SpaceMarine;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    private FromStringToCommand fromStringToCommand;
    private Scanner userInput = new Scanner(System.in);
    private String userCommand;
    private static Socket clientSocket;
    private int port;
    private ToServerMessageHandler toServerMessageHandler;
    private FromServerMessageHandler fromServerMessageHandler;

    public ClientMain(int port) throws IOException {
        this.port = port;
        clientSocket = new Socket("localhost", port);
    }

    public void start() {
        System.out.print(Colors.CYAN);
        System.out.println("Для ознакомлением со списком команд, введите 'info'");
        while (!userCommand.equals("exit")) {
            if (!userInput.hasNextLine()) {
                break;
            } else {
                userCommand = userInput.nextLine();
                Command command = fromStringToCommand.getCommandFromString(userCommand);
                toServerMessageHandler = new ToServerMessageHandler(clientSocket);
                fromServerMessageHandler = new FromServerMessageHandler(clientSocket);
                toServerMessageHandler.sendMessage(command);
                MessageToClient message = fromServerMessageHandler.getMessage();
                if (message != null) {
                    if (message.getMessage() != null) {
                        System.out.print(Colors.CYAN_BOLD);
                        System.out.print("Сервер: ");
                        System.out.println(message.getMessage());
                    } else {
                        System.out.println("От сервера нет ответа");
                    }
                    if (message.getSpaceMarines() != null) {
                        for (SpaceMarine scp : message.getSpaceMarines()) {
                            System.out.println(scp);
                        }
                    }
                } else {
                    System.out.println("Ответ некорректен");
                }
            }
        }

    }

}


