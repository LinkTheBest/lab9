package commands;

import client.Colors;
import client.CreatingNewObject;
import ComandPack.Command;
import commandsRealization.ListOfCommands;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;

import java.util.ArrayList;

public class ExecuteScriptCommand extends FatherOfCommands{

    public ExecuteScriptCommand(Collection collection, TbI_PROSTO_SUPER kryto){
        super(collection, kryto);
    }
    ArrayList<MessageToClient> messageToClientsList = new ArrayList<>();

    public MessageToClient executeCommand(Command command) {
        if(command.getCommandList().isEmpty()){
            System.out.println("Команд не обнаружено!");
        }
        FatherOfCommands foc;
        MessageToClient messageToClient;
        int elementId = 0;
        int counter = command.getCommandList().size();

        while(counter > 0){
            String commandFromList[] = command.getCommandList().get(elementId).split("\\s");
            switch (commandFromList[0]){
                case "":
                    messageToClient = new MessageToClient("Команда отсутствует");
                    messageToClientsList.add(messageToClient);
                    break;
                case "help":
                    foc = new HelpCommand(collection, kryto);
                    messageToClient = foc.executeCommand(new Command(ListOfCommands.HELP));
                    messageToClientsList.add(messageToClient);
                    break;
                case "info":
                    foc = new InfoCommand(collection, kryto);
                    messageToClient = foc.executeCommand(new Command(ListOfCommands.INFO));
                    messageToClientsList.add(messageToClient);
                    break;
                case "show":
                    foc = new ShowCommand(collection, kryto);
                    messageToClient = foc.executeCommand(new Command(ListOfCommands.SHOW));
                    messageToClientsList.add(messageToClient);
                    break;
                case "add":
                    if (commandFromList[commandFromList.length - 1].equals("add")) {
                        messageToClient = new MessageToClient("Введите имя!");
                        messageToClientsList.add(messageToClient);
                        break;
                    }
                    CreatingNewObject creatingNewObject = new CreatingNewObject(commandFromList[1]);
                    foc = new AddCommand(collection, kryto);
                    messageToClient = foc.executeCommand(new Command(ListOfCommands.ADD, creatingNewObject.createObject()));
                    messageToClientsList.add(messageToClient);
                    break;
                case "remove_by_id":
                    if (commandFromList[commandFromList.length - 1].equals("remove_by_id")) {
                        messageToClient = new MessageToClient("Введите id!");
                        messageToClientsList.add(messageToClient);
                        break;
                    } else {
                        foc = new RemoveByIdCommand(collection, kryto);
                        messageToClient = foc.executeCommand(new Command(ListOfCommands.REMOVE_BY_ID, Integer.valueOf(commandFromList[1])));
                        messageToClientsList.add(messageToClient);
                        break;
                    }
                case "clear":
                    foc = new ClearCommand(collection, kryto);
                    messageToClient = foc.executeCommand(new Command(ListOfCommands.CLEAR));
                    messageToClientsList.add(messageToClient);
                    break;
                case "save":
                    foc = new SaveCommand(collection, kryto);
                    messageToClient = foc.executeCommand(new Command(ListOfCommands.SAVE));
                    messageToClientsList.add(messageToClient);
                    break;

                case "add_if_max":
                    if (commandFromList[commandFromList.length - 1].equals("add_if_max")) {
                        messageToClient = new MessageToClient("Введите имя!");
                        messageToClientsList.add(messageToClient);
                        break;
                    } else {
                        foc = new AddIfMaxCommand(collection, kryto);
                        messageToClient = foc.executeCommand(new Command(ListOfCommands.ADD_IF_MAX));
                        messageToClientsList.add(messageToClient);
                        break;
                    }
                case "add_if_min":
                    if (commandFromList[commandFromList.length - 1].equals("add_if_min")) {
                        messageToClient = new MessageToClient("Введите имя!");
                        messageToClientsList.add(messageToClient);
                        break;
                    } else {
                        foc = new AddIfMaxCommand(collection, kryto);
                        messageToClient = foc.executeCommand(new Command(ListOfCommands.ADD_IF_MIN));
                        messageToClientsList.add(messageToClient);
                        break;
                    }
                case "remove_lower":
                    foc = new RemoveLowerCommand(collection, kryto);
                    messageToClient = foc.executeCommand(new Command(ListOfCommands.REMOVE_LOWER));
                    messageToClientsList.add(messageToClient);
                    break;
                case "sum_of_health":
                    foc = new SumOfHealthCommand(collection, kryto);
                    messageToClient = foc.executeCommand(new Command(ListOfCommands.SUM_OF_HEALTH));
                    messageToClientsList.add(messageToClient);
                    break;
                case "print_descending":
                    foc = new PrintDescendingCommand(collection, kryto);
                    messageToClient = foc.executeCommand(new Command(ListOfCommands.PRINT_DESCENDING));
                    messageToClientsList.add(messageToClient);
                    break;
                case "print_descending_health":
                    foc = new PrintFieldDescendingHealth(collection, kryto);
                    messageToClient = foc.executeCommand(new Command(ListOfCommands.PRINT_DESCENDING_HEALTH));
                    messageToClientsList.add(messageToClient);
                    break;
                default:
                    System.out.print(Colors.RED_BOLD);
                    System.out.println("НЕ ШУТИ ТАК!");
                    command = new Command(ListOfCommands.HELP);
                    break;
            }


            elementId++;
            counter--;
        }

        return new MessageToClient("Команды были выполнены", messageToClientsList);
    }
}
