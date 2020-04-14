package server;

import commandsRealization.Command;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMain implements TbI_PROSTO_SUPER {
    private FromClientMessageHandler fromClientMessageHandler;
    private ToClientMessageHandler toClientMessageHandler;
    private Collection collection;
    private  Socket clientSocket;
    private  ServerSocket server;
    private int port;
    private JsonDataHandler jsonDataHandler;
    private StartUpObjectLoader startUpObjectLoader;


    public ServerMain(int port, String fileName){
        collection = new Collection();
        this.port = port;
        try {
            server = new ServerSocket(port);
        }catch (IOException e){}
        jsonDataHandler = new JsonDataHandler(fileName);
        startUpObjectLoader = new StartUpObjectLoader(jsonDataHandler.getJsonCollectionSize(), jsonDataHandler);
        collection.setObjects(startUpObjectLoader.getSpaceDeque());

    }

    public void start() throws IOException, ClassNotFoundException {
        clientSocket = server.accept();
        fromClientMessageHandler = new FromClientMessageHandler(clientSocket);
        toClientMessageHandler = new ToClientMessageHandler(clientSocket);
        Command command =  fromClientMessageHandler.getMessage();
        MessageToClient message = prostoKlass(command);

    }

    @Override
    public MessageToClient prostoKlass(Command command) {

    }
}
