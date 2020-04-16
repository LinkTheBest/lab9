package client;

import commandsRealization.Command;
import server.MessageToClient;
import spaceMarineProperties.SpaceMarine;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ClientMain {
    private FromStringToCommand fromStringToCommand;
    private Scanner userInput = new Scanner(System.in);
    private String userCommand = "";
    private static Socket clientSocket;
    private ToServerMessageHandler toServerMessageHandler;
    private FromServerMessageHandler fromServerMessageHandler;

    public ClientMain(int port) throws IOException {
        clientSocket = new Socket("localhost", port);
    }

    public void start() {
        System.out.print(Colors.CYAN);
        System.out.println("Для ознакомлением со списком команд, введите 'help'");
        while (!userCommand.equals("exit")) {
            System.out.print(Colors.GREEN_BOLD);
            System.out.print("Введите команду: ");
            if (!userInput.hasNextLine()) {
                break;
            } else {
                userCommand = userInput.nextLine();
                try {
                    fromStringToCommand = new FromStringToCommand();
                    Command command = fromStringToCommand.getCommandFromString(userCommand);
                    toServerMessageHandler = new ToServerMessageHandler(clientSocket);
                    fromServerMessageHandler = new FromServerMessageHandler(clientSocket);
                    toServerMessageHandler.sendMessage(command);
                    MessageToClient message = fromServerMessageHandler.getMessage();
                    if (message != null) {
                        if (message.getMessage() != null) {
                            if (message.getMessage() == " ") {
                            } else {
                                System.out.print(Colors.GREEN_BOLD);
                                System.out.print("Сообщение от сервера: ");
                                System.out.print(Colors.BLACK_BOLD);
                                System.out.println(message.getMessage());
                            }
                        } else {
                            System.out.print(Colors.GREEN_BOLD);
                            System.out.println("От сервера нет ответа");
                        }
                        if (message.getSpaceMarines() != null) {
                            for (SpaceMarine scp : message.getSpaceMarines()) {
                                System.out.print(Colors.BLACK_BOLD);
                                System.out.println(scp.toString());
                            }
                            System.out.print(Colors.CYAN_BOLD);
                        }
                        if (message.getHelpArray() != null) {
                            for (String str : message.getHelpArray()) {
                                System.out.print(Colors.BLACK_BOLD);
                                System.out.println(str);
                            }
                            System.out.print(Colors.CYAN_BOLD);
                        }
                    } else {
                        System.out.print(Colors.GREEN_BOLD);
                        System.out.println("Ответ некорректен");
                    }
                } catch (IOException e) {
                } catch (ClassNotFoundException e) {
                }
            }
        }
    }
}


