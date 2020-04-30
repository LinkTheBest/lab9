package commands;

import ComandPack.Command;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;
import spaceMarineProperties.SpaceMarine;

import java.util.ArrayDeque;

public class AddCommand extends FatherOfCommands {

    public AddCommand(Collection collection, TbI_PROSTO_SUPER kryto){

        super(collection, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command){
        ArrayDeque<SpaceMarine> tempDeque = collection.getObjects();
        tempDeque.add(command.getSpaceMarine());
        collection.setObjects(tempDeque);
        System.out.println("Элемент успешно добавлен!");
        return new MessageToClient("Элемент успешно добавлен! \n" +  command.getSpaceMarine().toString());
    }
}
