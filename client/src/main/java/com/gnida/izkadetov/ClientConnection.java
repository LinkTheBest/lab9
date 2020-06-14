package com.gnida.izkadetov;

import java.util.Scanner;

public class ClientConnection {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Для начала работы введите порт или 'exit.'");
        System.out.print(Colors.GREEN_BOLD);
        System.out.print("PORT: ");
        while (true) {
            if (!scn.hasNextLine()) {
                break;
            }
            String userInput = scn.nextLine();
            if (userInput.equals("exit") || userInput.equals("Exit") || userInput.equals("EXIT")) {
                System.exit(0);
            } else {
                try {
                    int port = Integer.valueOf(userInput);
                    if (port < 0 || port >= 65535) {
                        System.out.print(Colors.RED_BOLD);
                        System.out.println("Invalid port!");
                    } else {
                        ClientMain clientMain = new ClientMain(port);
                        clientMain.start();
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Порт некорректен!");
                }
            }
        }
    }
}

