package server;

import client.Colors;
import commands.*;
import commandsRealization.Command;
import commandsRealization.ListOfCommands;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain implements TbI_PROSTO_SUPER {
    private String usersInput = "";
    private Scanner scn = new Scanner(System.in);
    private FromClientMessageHandler fromClientMessageHandler;
    private ToClientMessageHandler toClientMessageHandler;
    private Collection collection;
    private Socket clientSocket;
    private ServerSocket server;
    private int port;
    private JsonDataHandler jsonDataHandler;
    private StartUpObjectLoader startUpObjectLoader;

    private FatherOfCommands helpCommand;
    private FatherOfCommands exitCommand;
    private FatherOfCommands infoCommand;
    private FatherOfCommands showCommand;
    private FatherOfCommands addCommand;
    private FatherOfCommands removeByIdCommand;
    private FatherOfCommands clearCommand;
    private FatherOfCommands executeScriptCommand;
    private FatherOfCommands addIfMaxCommand;
    private FatherOfCommands addIfMinCommand;
    private FatherOfCommands removeLowerCommand;
    private FatherOfCommands sumOfHealthCommand;
    private FatherOfCommands printDescendingCommand;
    private FatherOfCommands printDescendingHealthCommand;


    public ServerMain(int port, String fileName) {
        collection = new Collection();
        this.port = port;
        try { server = new ServerSocket(port); } catch (IOException e) {}

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
    }

    public void start() throws IOException, ClassNotFoundException {
        System.out.print(Colors.CYAN_BOLD);
        System.out.println("Сервер работает!\nДля Выхода введите 'exit'");
        Thread stopServer = new Thread(() -> stopServer());
        stopServer.start();
        Thread socketWork = new Thread(() -> socketWork(server));
        socketWork.setDaemon(true);
        socketWork.start();
    }

    public void socketWork(ServerSocket serverSocket) {
        try {
                clientSocket = serverSocket.accept();
                System.out.print(Colors.CYAN_BOLD);
                System.out.println("Соединение установлено");
        } catch (IOException e) {}
        while (true) {
            try {
                fromClientMessageHandler = new FromClientMessageHandler(clientSocket);
                toClientMessageHandler = new ToClientMessageHandler(clientSocket);
                Command command = fromClientMessageHandler.getMessage();
                MessageToClient message = prostoKlass(command);
                String report = toClientMessageHandler.send(message);
                System.out.println(report);
            } catch (IOException e) {
                System.out.println(Colors.RED_BOLD);
                System.out.println(e.getClass());
            } catch (ClassNotFoundException e) {
            }
        }
    }

    public void stopServer() {
        String userCommand = "";
        Scanner scn = new Scanner(System.in);
        userCommand = scn.nextLine();
        if (userCommand != "exit") {
            prostoKlass(new Command(ListOfCommands.EXIT));
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
            default:
                System.out.print(Colors.RED_UNDERLINED);
                System.out.println("Лол");
        }
        return null;
    }
}
