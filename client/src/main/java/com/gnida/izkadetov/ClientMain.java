package com.gnida.izkadetov;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    private ConnectionChecker connectionChecker;
    private FromStringToCommand fromStringToCommand;
    private Scanner userInput = new Scanner(System.in);
    private String userCommand = "";
    private Socket clientSocket;
    private ToServerMessageHandler toServerMessageHandler;
    private FromServerMessageHandler fromServerMessageHandler;
    private int port;

    public ClientMain(int port) {
        this.port = port;
        connectionChecker = new ConnectionChecker(port);
        clientSocket = connectionChecker.socketConnector();
    }

    public void start() {
        fromStringToCommand = new FromStringToCommand();
        System.out.print(Colors.CYAN);
        System.out.println("Для ознакомлением со списком команд, введите 'help'");
        while (true) {


            System.out.print(Colors.GREEN_BOLD);
            System.out.print("Введите команду: ");
            if (!userInput.hasNextLine()) {
                break;
            } else {
                userCommand = userInput.nextLine();
                if (userCommand.equals("exit")) {
                    System.exit(0);
                }
                try {
                    toServerMessageHandler = new ToServerMessageHandler(clientSocket, port);
                    Command command = fromStringToCommand.getCommandFromString(userCommand);
                    //fromServerMessageHandler = new FromServerMessageHandler(toServerMessageHandler.sendMessage(command));
                    clientSocket = toServerMessageHandler.sendMessage(command);
                    fromServerMessageHandler = new FromServerMessageHandler(clientSocket);
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

                        if (message.getMessageToClientsList() != null) {
                            for (MessageToClient mstoc : message.getMessageToClientsList()) {
                                System.out.println(mstoc.getMessage());
                                if (mstoc.getSpaceMarines() != null) {
                                    for (SpaceMarine scp : mstoc.getSpaceMarines()) {
                                        System.out.print(Colors.BLACK_BOLD);
                                        System.out.println(scp.toString());
                                    }

                                }
                            }
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
                } catch (ClassNotFoundException | IOException e) {
                }
            }
        }
    }

//    private boolean portInUse(int port) {
//        boolean result = false;
//        try {
//            new Socket("localhost", port);
//            result = false;
//        } catch (Exception e) {
//            result = true;
//        }
//        return result;
//    }
}


