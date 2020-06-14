package com.gnida.izkadetov;

import java.io.IOException;
import java.net.Socket;

public class ConnectionChecker {
    private int port;
    private Socket socket;

    public ConnectionChecker(int port) {
        this.port = port;
    }

    public Socket socketConnector() {
        while (true) {
            try {
                socket = new Socket("localhost", port);
                break;
            } catch (IOException e) {
                System.out.print(Colors.RED_BOLD);
                System.out.println("Сервер занят или же недоступен...");
                System.out.println("Соединение не удалось, повторяю попытки... ");
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return socket;
    }
}
