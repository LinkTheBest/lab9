package commands;

import commandsRealization.Command;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HelpCommand extends FatherOfCommands {
    public HelpCommand(Collection collection, TbI_PROSTO_SUPER kryto) {
        super(collection, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        Map<String, String> commandList = new HashMap<>();
        commandList.put("help", "вывести справку по доступным командам");
        commandList.put("info", "вывести в стандартный поток вывода информацию о коллекции");
        commandList.put("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        commandList.put("add {element}", "добавить новый элемент в коллекцию");
        commandList.put("update_id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        commandList.put("remove_by_id id", "удалить элемент из коллекции по его id");
        commandList.put("clear", "очистить коллекцию");
        commandList.put("save", "сохранить коллекцию в файл");
        commandList.put("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        commandList.put("exit", "завершить программу (без сохранения в файл)");
        commandList.put("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        commandList.put("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        commandList.put("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        commandList.put("sum_of_health", "вывести сумму значений поля health для всех элементов коллекции");
        commandList.put("print_descending", "вывести элементы коллекции в порядке убывания");
        commandList.put("print_field_descending_health {health}", "вывести значения поля health в порядке убывания");

        Iterator<Map.Entry<String, String>> iterator = commandList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        return new MessageToClient("");
    }
}
