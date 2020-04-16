package server;

import client.Colors;

import java.io.IOException;
import java.util.Scanner;

public class ServerConnection {
    private final static String FILE_NAME = "C:\\Users\\Nikitka\\IdeaProjects\\lab7\\src\\test.json";

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print(Colors.CYAN_BOLD);
        System.out.println("Для начала работы введите порт или 'exit.'");
        System.out.print(Colors.GREEN_BOLD);
        System.out.print("PORT: ");
        while(true) {
            if (!scn.hasNextLine()) {
                break;
            }

            String userInput = scn.nextLine();
            if(userInput == "exit" || userInput == "Exit" || userInput == "EXIT"){
                System.exit(0);
            }else{
                try{
                    int port = Integer.valueOf(userInput);
                    if(port < 0 || port >= 65535){
                        System.out.print(Colors.RED_BOLD);
                        System.out.println("Invalid port!");
                    } else{
                        try {
                            ServerMain serverMain = new ServerMain(port, FILE_NAME);
                            serverMain.start();
                        }catch (IOException e){
                            System.out.print(Colors.RED_BOLD);
                            System.out.println("Ошибка");
                            System.out.printf(e.getMessage());
                            System.out.print(Colors.CYAN_BOLD);

                        }catch (ClassNotFoundException e){}

                    }
                }catch (NumberFormatException e){
                }//catch (BindException e){}
            }

        }

    }



}
