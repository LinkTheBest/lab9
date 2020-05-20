package com.gnida.izkadetov;

import com.gnida.izkadetov.commands.*;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerMain implements TbI_PROSTO_SUPER {

    private Scanner scn = new Scanner(System.in);
    private String serverInput = "";
    private FromClientMessageHandler fromClientMessageHandler;
    private ToClientMessageHandler toClientMessageHandler;
    private Collection collection;
    private Socket clientSocket;
    private ServerSocket server;
    private int port;
    private JsonDataHandler jsonDataHandler;
    private StartUpObjectLoader startUpObjectLoader;

    private final FatherOfCommands helpCommand;
    private final FatherOfCommands exitCommand;
    private final FatherOfCommands infoCommand;
    private final FatherOfCommands showCommand;
    private final FatherOfCommands addCommand;
    private final FatherOfCommands removeByIdCommand;
    private final FatherOfCommands clearCommand;
    private final FatherOfCommands executeScriptCommand;
    private final FatherOfCommands addIfMaxCommand;
    private final FatherOfCommands addIfMinCommand;
    private final FatherOfCommands removeLowerCommand;
    private final FatherOfCommands sumOfHealthCommand;
    private final FatherOfCommands printDescendingCommand;
    private final FatherOfCommands printDescendingHealthCommand;
    private final FatherOfCommands saveCommand;

    public ServerMain(int port, String fileName) {
        collection = new Collection();
        this.port = port;
        while (true) {
            try {
                server = new ServerSocket(this.port);
                break;
            } catch (IOException e) {
                String temp = "";
                System.out.println(Colors.RED_BOLD);
                System.out.println("Порт занят, Введите другой!");
                System.out.println(Colors.GREEN_BOLD);
                System.out.println("PORT:");
                temp = scn.nextLine();
                if (temp.equals("exit") | temp.equals("Exit") | temp.equals("EXIT")) {
                    System.exit(0);
                } else {
                    this.port = Integer.valueOf(temp);
                }
            }
        }
        jsonDataHandler = new JsonDataHandler(fileName);
        startUpObjectLoader = new StartUpObjectLoader(jsonDataHandler.getJsonCollectionSize(), jsonDataHandler);
        collection.setObjects(startUpObjectLoader.getSpaceDeque());

        helpCommand = new HelpCommand(collection, this);
        exitCommand = new ExitCommand(collection, this);
        infoCommand = new InfoCommand(collection, this);
        showCommand = new ShowCommand(collection, this);
        addCommand = new AddCommand(collection, this);
        removeByIdCommand = new RemoveByIdCommand(collection, this);
        clearCommand = new ClearCommand(collection, this);
        executeScriptCommand = new ExecuteScriptCommand(collection, this);
        addIfMaxCommand = new AddIfMaxCommand(collection, this);
        addIfMinCommand = new AddIfMinCommand(collection, this);
        removeLowerCommand = new RemoveLowerCommand(collection, this);
        sumOfHealthCommand = new SumOfHealthCommand(collection, this);
        printDescendingCommand = new PrintDescendingCommand(collection, this);
        printDescendingHealthCommand = new PrintFieldDescendingHealth(collection, this);
        saveCommand = new SaveCommand(collection, this);
    }

    public void start() {
        System.out.print(Colors.CYAN_BOLD);
        System.out.println("Сервер работает! Для Выхода введите 'exit'");
        System.out.println("Для сохранения введите: save; Для выхода: exit");
        Thread stopServer = new Thread(() -> stopServer());
        stopServer.setDaemon(false);
        Thread socketWork = new Thread(() -> socketWork(server));
        socketWork.setDaemon(true);
        stopServer.start();
        socketWork.start();
    }

    public void socketWork(ServerSocket serverSocket) {
        try {
            clientSocket = serverSocket.accept();

            System.out.print(Colors.CYAN_BOLD);
            System.out.println("Соединение установлено");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            try {
                server.close();
                fromClientMessageHandler = new FromClientMessageHandler(clientSocket);
                toClientMessageHandler = new ToClientMessageHandler(clientSocket);
                Command command = fromClientMessageHandler.getMessage();
                MessageToClient message = prostoKlass(command);
                toClientMessageHandler.send(message);
                System.out.print(Colors.RED_BOLD);
                System.out.println("$odmen_servera: ");
            } catch (IOException e) {
                System.out.print(Colors.RED_BOLD);
                System.out.println("Соединение потеряно! Пытаюсь восстановить...");
                try {
                    server = new ServerSocket(port);
                    clientSocket = server.accept();
                    if (clientSocket.isConnected()) {
                        System.out.print(Colors.CYAN_BOLD);
                        System.out.println("Соединение установлено");
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void stopServer() {

        Scanner serverScn = new Scanner(System.in);
        System.out.print(Colors.RED_BOLD);
        System.out.println("$odmen_servera: ");
        while (true) {
            if (!serverScn.hasNextLine()) {
                break;
            } else {
                serverInput = serverScn.nextLine();
                switch (serverInput) {
                    case "exit":
                        prostoKlass(new Command(ListOfCommands.SAVE));
                        prostoKlass(new Command(ListOfCommands.EXIT));
                    case "save":
                        System.out.println("Сохранено!");
                        prostoKlass(new Command(ListOfCommands.SAVE));
                        collection.updateSaveDate();
                        break;
                    default:
                        System.out.println("Неопознанная команда!");
                }
                System.out.print(Colors.RED_BOLD);
                System.out.print("$odmen_servera: ");

            }
        }

    }

    @Override
    public MessageToClient prostoKlass(Command command) {
        switch (command.getCommand()) {
            case HELP:
                return helpCommand.executeCommand(command);
            case INFO:
                return infoCommand.executeCommand(command);
            case ADD:
                return addCommand.executeCommand(command);
            case REMOVE_BY_ID:
                return removeByIdCommand.executeCommand(command);
            case SHOW:
                return showCommand.executeCommand(command);
            case CLEAR:
                return clearCommand.executeCommand(command);
            case EXECUTE_SCRIPT:
                return executeScriptCommand.executeCommand(command);
            case ADD_IF_MAX:
                return addIfMaxCommand.executeCommand(command);
            case ADD_IF_MIN:
                return addIfMinCommand.executeCommand(command);
            case REMOVE_LOWER:
                return removeLowerCommand.executeCommand(command);
            case SUM_OF_HEALTH:
                return sumOfHealthCommand.executeCommand(command);
            case PRINT_DESCENDING:
                return printDescendingCommand.executeCommand(command);
            case PRINT_DESCENDING_HEALTH:
                return printDescendingHealthCommand.executeCommand(command);
            case EXIT:
                return exitCommand.executeCommand(command);
            case SAVE:
                return saveCommand.executeCommand(command);
            default:
                System.out.print(Colors.RED_UNDERLINED);
                System.out.println("Лол");
        }
        return null;
    }
}
