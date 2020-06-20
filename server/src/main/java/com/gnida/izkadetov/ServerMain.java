package com.gnida.izkadetov;

import com.gnida.izkadetov.DataBase.DataBaseInitializer;
import com.gnida.izkadetov.commands.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class ServerMain implements TbI_PROSTO_SUPER {

    private DataBaseInitializer dataBaseInitializer;
    private ForkJoinPool pool = new ForkJoinPool(50);
    private ExecutorService cachedThread = Executors.newCachedThreadPool();
    private Scanner scn = new Scanner(System.in);
    private String serverInput = "";
    private FromClientMessageHandler fromClientMessageHandler;
    private ToClientMessageHandler toClientMessageHandler;
    private DataBaseManager dataBaseManager;
    private Socket clientSocket;
    private ServerSocket server;
    private int port;
    private StartUpObjectLoader startUpObjectLoader;
    private MessageToClient message;
    private final String PATH_TO_CONFIG_FILE = "config.properties";

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
    private final FatherOfCommands registrationCommand;
    private final FatherOfCommands loginCommand;
    private final FatherOfCommands logoutCommand;
    private final FatherOfCommands getIdCommand;

    {
        dataBaseManager = new DataBaseManager();
        helpCommand = new HelpCommand(dataBaseManager, this);
        exitCommand = new ExitCommand(dataBaseManager, this);
        infoCommand = new InfoCommand(dataBaseManager, this);
        showCommand = new ShowCommand(dataBaseManager, this);
        addCommand = new addcommand(dataBaseManager, this);
        removeByIdCommand = new RemoveByIdCommand(dataBaseManager, this);
        clearCommand = new ClearCommand(dataBaseManager, this);
        executeScriptCommand = new ExecuteScriptCommand(dataBaseManager, this);
        addIfMaxCommand = new AddIfMaxCommand(dataBaseManager, this);
        addIfMinCommand = new AddIfMinCommand(dataBaseManager, this);
        removeLowerCommand = new RemoveLowerCommand(dataBaseManager, this);
        sumOfHealthCommand = new SumOfHealthCommand(dataBaseManager, this);
        printDescendingCommand = new PrintDescendingCommand(dataBaseManager, this);
        printDescendingHealthCommand = new PrintFieldDescendingHealth(dataBaseManager, this);
        registrationCommand = new RegistrationCommand(dataBaseManager, this);
        loginCommand = new LoginCommand(dataBaseManager, this);
        logoutCommand = new LogoutCommand(dataBaseManager, this);
        getIdCommand = new getUserIdCommand(dataBaseManager, this);
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
                    try {
                        this.port = Integer.valueOf(temp);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("Введите адекватные циферки:");
                    }
                }
            }
        }

    }

    public void dataBaseConnect() {
        Properties properties = new Properties();
        while (true) {
            try {
                InputStream fileInputStream = ServerMain.class.getClassLoader().getResourceAsStream(PATH_TO_CONFIG_FILE);
                properties.load(fileInputStream);
                String host = properties.getProperty("host");
                String name = properties.getProperty("name");
                String user = properties.getProperty("login");
                String pwd = properties.getProperty("pwd");
                int bPort = Integer.valueOf(properties.getProperty("port"));
                dataBaseInitializer = new DataBaseInitializer(host, bPort, name, user, pwd);
                break;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        boolean connected = dataBaseInitializer.ifDataBaseConnected();
        boolean created = dataBaseInitializer.ifTablesCreated();
        dataBaseManager.setDataBaseInitializer(dataBaseInitializer);
        if (connected & created) {
            System.out.println("База данных подключена, таблицы созданы!");
        } else {
            System.out.println("При загрузкe БД произошла ошибка");
        }
        startUpObjectLoader = new StartUpObjectLoader(dataBaseInitializer.getConnection());
        dataBaseManager.setObjects(startUpObjectLoader.loader());

    }


    public void start() {
        dataBaseConnect();
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
                pool.execute(() -> {
                    clientMultiDataProccesor(clientSocket);
                });
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
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
            case REGISTRATION:
                return registrationCommand.executeCommand(command);
            case LOGIN:
                return loginCommand.executeCommand(command);
            case LOGOUT:
                return logoutCommand.executeCommand(command);
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
            case GET_USER_ID:
                return getIdCommand.executeCommand(command);
            default:
                System.out.print(Colors.RED_UNDERLINED);
                System.out.println("Лол");
        }
        return null;
    }

    public void clientMultiDataProccesor(Socket socket) {
        try {
            fromClientMessageHandler = new FromClientMessageHandler(socket);
            Command command = fromClientMessageHandler.getMessage();
            message = prostoKlass(command);
            clientMultiSenderProccesor(message, socket);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void clientMultiSenderProccesor(MessageToClient message, Socket socket) {
        cachedThread.execute(() -> {
            try {
                toClientMessageHandler = new ToClientMessageHandler(socket);
                toClientMessageHandler.send(message);
            } catch (IOException e) {
            }
        });
    }
}
