package com.gnida.izkadetov;


import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FromStringToCommand {
    private LoginPasswordReader loginPasswordReader = new LoginPasswordReader();
    private String userLogin;
    private String userPassword;


    public Command getCommandFromString(String userCommand) {
        String devidedComand[];
        Command command = new Command(ListOfCommands.HELP);
        devidedComand = userCommand.split("\\s");
        System.out.print(Colors.GREEN_BOLD);
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
                if (devidedComand[devidedComand.length - 1].equals("add")) {
                    System.out.println(Colors.RED_BOLD);
                    System.out.println("Введите имя!");
                    command = new Command(ListOfCommands.HELP);
                    return command;
                }
                CreatingNewObject creatingNewObject = new CreatingNewObject(devidedComand[1]);
                command = new Command(ListOfCommands.ADD, creatingNewObject.createObject(), userLogin, userPassword);
                return command;
            case "remove_by_id":
                if (devidedComand[devidedComand.length - 1].equals("remove_by_id")) {
                    System.out.println("Введите id после команды!");
                    command = new Command(ListOfCommands.HELP);
                    return command;
                } else {
                    command = new Command(ListOfCommands.REMOVE_BY_ID, Integer.valueOf(devidedComand[1]), userLogin, userPassword);
                    return command;
                }
            case "clear":
                command = new Command(ListOfCommands.CLEAR);
                return command;
            case "save":
                command = new Command(ListOfCommands.SAVE);
                return command;
            case "execute_script":
                if (devidedComand[devidedComand.length - 1].equals("execute_script")) {
                    System.out.println("Введите название файла после команды!");
                    command = new Command(ListOfCommands.HELP);
                    return command;
                } else {
                    try {
                        MrScripter mrScripter = new MrScripter(devidedComand[1]);
                        command = new Command(ListOfCommands.EXECUTE_SCRIPT, mrScripter.getCommandFromFile(), userLogin, userPassword);
                    } catch (FileNotFoundException e) {
                        System.out.println(Colors.RED_BOLD);
                        System.out.println("Файл не найден!");
                    }
                    return command;
                }

            case "add_if_max":
                if (devidedComand[devidedComand.length - 1].equals("add_if_max")) {
                    System.out.println("Введите имя объекта после команды!");
                    command = new Command(ListOfCommands.HELP);
                    return command;
                } else {
                    command = new Command(ListOfCommands.ADD_IF_MAX, devidedComand[1], userLogin, userPassword);
                    return command;
                }
            case "add_if_min":
                if (devidedComand[devidedComand.length - 1].equals("add_if_min")) {
                    System.out.println("Введите имя объекта после команды!");
                    command = new Command(ListOfCommands.HELP);
                    return command;
                } else {
                    command = new Command(ListOfCommands.ADD_IF_MIN, devidedComand[1], userLogin, userPassword);
                    return command;
                }
            case "remove_lower":
                if (devidedComand[devidedComand.length - 1].equals("remove_lower")) {
                    System.out.println("Введите имя объекта после команды!");
                    command = new Command(ListOfCommands.HELP);
                    return command;
                } else {
                    command = new Command(ListOfCommands.REMOVE_LOWER, Integer.valueOf(devidedComand[1]), userLogin, userPassword);
                    return command;
                }
            case "sum_of_health":
                command = new Command(ListOfCommands.SUM_OF_HEALTH);
                return command;
            case "print_descending":
                command = new Command(ListOfCommands.PRINT_DESCENDING);
                return command;
            case "print_descending_health":
                command = new Command(ListOfCommands.PRINT_DESCENDING_HEALTH);
                return command;
            case "registration":
                userLogin = loginPasswordReader.getLogin();
                userPassword = encryptPassword(loginPasswordReader.getPassword());
                command = new Command(ListOfCommands.REGISTRATION, userLogin, userPassword);
                return command;
            case "login":
                userLogin = loginPasswordReader.getLogin();
                userPassword = encryptPassword(loginPasswordReader.getPassword());
                command = new Command(ListOfCommands.LOGIN, userLogin, userPassword);
                return command;
            default:
                System.out.print(Colors.RED_BOLD);
                System.out.println("НЕ ШУТИ ТАК!");
                command = new Command(ListOfCommands.HELP);
                break;
        }
        return command;
    }

    private String encryptPassword(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] messageDigest = md.digest(pwd.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}