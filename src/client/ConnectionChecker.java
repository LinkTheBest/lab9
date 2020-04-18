package client;

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
                System.out.println("Соединение не удалось, повторяю попытки... ");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                }
            }
        }
        return socket;
    }


}
