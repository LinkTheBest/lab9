package com.gnida.izkadetov;

import java.io.IOException;
import java.net.Socket;

public class ConnectionChecker {
    private int port;
    private String host;
    private Socket socket;

    public ConnectionChecker(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void socketConnector() {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            System.out.print(Colors.RED_BOLD);
            System.out.println("Сервер занят или же недоступен...");
        }
    }

    public Socket getSocket() {
        return socket;
    }

}
