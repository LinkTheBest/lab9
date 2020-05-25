package com.gnida.izkadetov.commands;


import com.gnida.izkadetov.*;

import java.util.ArrayList;
import java.util.List;


public class HelpCommand extends FatherOfCommands {
    public HelpCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {
        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        List<String> helpArray = new ArrayList<>();
        helpArray.add("register: зарегистрироваться");
        helpArray.add("login: войти, если вы уже создали аккаунт");
        helpArray.add("help: вывести справку по доступным командам");
        helpArray.add("info: вывести в стандартный поток вывода информацию о коллекции");
        helpArray.add("show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        helpArray.add("add {element}: добавить новый элемент в коллекцию");
        helpArray.add("update_id {element}: обновить значение элемента коллекции, id которого равен заданному");
        helpArray.add("remove_by_id id: удалить элемент из коллекции по его id");
        helpArray.add("clear: очистить коллекцию");
        //commandList.put("save", "сохранить коллекцию в файл");
        helpArray.add("execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        //commandList.put("exit", "завершить программу (без сохранения в файл)");
        helpArray.add("add_if_max {element}: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        helpArray.add("add_if_min {element}: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        helpArray.add("remove_lower {element}: удалить из коллекции все элементы, меньшие, чем заданный");
        helpArray.add("sum_of_health: вывести сумму значений поля health для всех элементов коллекции");
        helpArray.add("print_descending: вывести элементы коллекции в порядке убывания");
        helpArray.add("print_descending_health {health}: вывести значения поля health в порядке убывания");

        return new MessageToClient("",helpArray);
    }
}
