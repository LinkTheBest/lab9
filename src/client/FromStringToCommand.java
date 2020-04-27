package client;

import commandsRealization.Command;
import commandsRealization.ListOfCommands;

public class FromStringToCommand {


    public Command getCommandFromString(String userCommand) {
        String devidedComand[];
        Command command = null;
        devidedComand = userCommand.split("\\s");

        switch (devidedComand[0]) {
            case "":
                System.out.println("Команда отсутствует");
                command = new Command(ListOfCommands.HELP);
                return command;
            case "help":
                command = new Command(ListOfCommands.HELP);
                return command;
            case "info":
                command = new Command(ListOfCommands.INFO);
                return command;
            case "show":
                command = new Command(ListOfCommands.SHOW);
                return command;
            case "add":
                CreatingNewObject creatingNewObject = new CreatingNewObject();
                command = new Command(ListOfCommands.ADD, creatingNewObject.createObject());
                return command;
            case "remove_by_id":
                if (devidedComand[devidedComand.length - 1].equals("remove_by_id")) {
                    System.out.println("Введите id после команды!");
                    break;
                } else {
                    command = new Command(ListOfCommands.REMOVE_BY_ID, Integer.valueOf(devidedComand[1]));
                    return command;
                }
            case "clear":
                command = new Command(ListOfCommands.CLEAR);
                return command;
            case "exit":
                System.exit(0);
            case "execute_script":
                if (devidedComand[devidedComand.length - 1].equals("execute_script")) {
                    System.out.println("Введите название файла после команды!");
                    break;
                } else {
                    command = new Command(ListOfCommands.EXECUTE_SCRIPT, devidedComand[1]);
                    return command;
                }
            case "add_if_max":
                if (devidedComand[devidedComand.length - 1].equals("add_if_max")) {
                    System.out.println("Введите имя объекта после команды!");
                    break;
                } else {
                    command = new Command(ListOfCommands.ADD_IF_MAX, devidedComand[1]);
                    return command;
                }
            case "add_if_min":
                if (devidedComand[devidedComand.length - 1].equals("add_if_min")) {
                    System.out.println("Введите имя объекта после команды!");
                    break;
                } else {
                    command = new Command(ListOfCommands.ADD_IF_MIN, devidedComand[1]);
                    return command;
                }
            case "remove_lower":
                command = new Command(ListOfCommands.REMOVE_LOWER);
                return command;
            case "sum_of_health":
                command = new Command(ListOfCommands.SUM_OF_HEALTH);
                return command;
            case "print_descending":
                command = new Command(ListOfCommands.PRINT_DESCENDING);
                return command;
            case "print_descending_health":
                command = new Command(ListOfCommands.PRINT_DESCENDING_HEALTH);
                return command;
            default:
                System.out.print(Colors.RED_BOLD);
                System.out.println("НЕ ШУТИ ТАК!");
                command = new Command(ListOfCommands.HELP);
                break;
        }
        return command;
    }
}
