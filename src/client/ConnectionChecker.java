package client;

import commandsRealization.Command;
import server.FromClientMessageHandler;
import server.ToClientMessageHandler;

import java.io.IOException;
import java.net.*;
import java.nio.channels.SocketChannel;

public class ConnectionChecker {
    private Socket veryFirstSocket;
    private SocketChannel socketChannel;
    private SocketAddress socketAddress;
    private FromServerMessageHandler fromServerMessageHandler;
    private FromClientMessageHandler fromClientMessageHandler;
    private ToServerMessageHandler toServerMessageHandler;
    private ToClientMessageHandler toClientMessageHandler;
    private int port;


    public ConnectionChecker(int port) {
        this.port = port;
        socketAddress = new InetSocketAddress("localhost", port);
    }

    public boolean checkConnectionSender() throws IOException {
        while (true) {
            try {
                socketChannel = SocketChannel.open();
                socketChannel.connect(socketAddress);
                break;
            } catch (ConnectException e) {
                System.out.println(Colors.RED_BOLD);
                System.out.println("Подключение не удалось. Пытюсь восстановиться соединение...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                }
            }
        }
        socketChannel.finishConnect();
        socketChannel.close();
        return true;
    }

    public void checkConnectionReciever(ServerSocket serverSocket) throws IOException{
        Socket tempClientSocket = serverSocket.accept();
        fromClientMessageHandler = new FromClientMessageHandler(tempClientSocket);
        toClientMessageHandler = new ToClientMessageHandler(tempClientSocket);
        while (!tempClientSocket.isConnected()) {
            System.out.println(Colors.RED_BOLD);
            System.out.println("Жду подключения клиента");
        }
        System.out.println("Проверка подключения прошла успешно!");
    }

    public void checkForStuckedCommands(){

    }
}
