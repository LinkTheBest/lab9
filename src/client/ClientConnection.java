package client;

import java.io.IOException;
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
            if (userInput == "exit" || userInput == "Exit" || userInput == "EXIT") {
                System.exit(0);
            } else {
                try {
                    int port = Integer.valueOf(userInput);
                    if (port < 0 || port >= 65535) {
                        System.out.print(Colors.RED_BOLD);
                        System.out.println("Invalid port!");
                    } else {
                        try {
                            ClientMain clientMain = new ClientMain(port);
                            clientMain.start();
                        } catch (IOException e) {
                            System.out.print(Colors.RED_BOLD);
                            System.out.println("Ошибка");
                            System.out.printf(e.getMessage());
                            System.out.print(Colors.CYAN_BOLD);
                        }

                    }
                } catch (NumberFormatException e) {
                }//catch (BindException e){}
            }

        }

    }
}

