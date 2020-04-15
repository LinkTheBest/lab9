package server;

import java.io.IOException;
import java.util.Scanner;

public class ServerConnection {
    private final static String FILE_NAME = "C:\\Users\\Nikitka\\IdeaProjects\\lab7\\src\\test.json";

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Для начала работы введите порт или 'exit.'");
        System.out.println("PORT: ");
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
                        System.out.println("Invalid port!");
                    } else{
                        try {
                            ServerMain serverMain = new ServerMain(port, FILE_NAME);
                            serverMain.start();
                        }catch (IOException e){
                        }catch (ClassNotFoundException e){}

                    }
                }catch (NumberFormatException e){
                }//catch (BindException e){}
            }

        }

    }



}
