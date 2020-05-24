package com.gnida.izkadetov;

import com.gnida.izkadetov.commands.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class ServerMain implements TbI_PROSTO_SUPER, Runnable {

    private ForkJoinPool pool = new ForkJoinPool(50);
    private ExecutorService cachedThread = Executors.newCachedThreadPool();
    private Socket runSocket;
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
    private MessageToClient message;

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

    {
        final String FILE_NAME = System.getenv("JSON");
        collection = new Collection();
        jsonDataHandler = new JsonDataHandler(FILE_NAME);
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

    public ServerMain(int port) {
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

    }


    public ServerMain(Socket runSocket) {
        this.runSocket = runSocket;
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
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.print(Colors.CYAN_BOLD);
                System.out.println("Соединение установлено");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            pool.execute(() -> {
                new Thread(new ServerMain(clientSocket)).start();
            });
            cachedThread.submit(() -> {
                try {
                    toClientMessageHandler = new ToClientMessageHandler(clientSocket);
                    toClientMessageHandler.send(message);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });

//                toClientMessageHandler = new ToClientMessageHandler(clientSocket);
//                toClientMessageHandler.send(message);
            System.out.print(Colors.RED_BOLD);
            System.out.println("$odmen_servera: ");
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

    @Override
    public void run() {
        try {
            fromClientMessageHandler = new FromClientMessageHandler(runSocket);
            Command command = fromClientMessageHandler.getMessage();
            message = prostoKlass(command);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
