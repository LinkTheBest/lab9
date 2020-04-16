package commands;

import commandsRealization.AddCommandMethods;
import commandsRealization.Command;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;
import spaceMarineProperties.SpaceMarine;

import java.util.ArrayDeque;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

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
