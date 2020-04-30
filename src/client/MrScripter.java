package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MrScripter {

    private Scanner fileScanner;
    private File file;
    private FromStringToCommand fromStringToCommand = new FromStringToCommand();
    private List<String> commandList = new ArrayList<>();

    public MrScripter(String fileName) throws FileNotFoundException {
        file = new File(fileName);
        fileScanner = new Scanner(file);
    }

    public List<String> getCommandFromFile() {
        while (fileScanner.hasNextLine()) {
            commandList.add(fileScanner.nextLine());

//            if (fileScanner.nextLine().startsWith("execute")) {
//                System.out.println(Colors.RED_BOLD);
//                System.out.println("Скрипт уже выполняется! Данный шаг будет пропущен!");
//                continue;
//            }
        }

        return commandList;
    }


}
