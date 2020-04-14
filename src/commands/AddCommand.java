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
        Scanner scn = new Scanner(System.in);
       List<SpaceMarine> tempList = new ArrayList<>(collection.getObjects());
        AddCommandMethods addCommandMethods = new AddCommandMethods();
        SpaceMarine addedSpcMrn = new SpaceMarine();
        System.out.println("Введите имя:");
        String name = scn.nextLine();
        addedSpcMrn.setName(name);
        addedSpcMrn.setId(addCommandMethods.readId());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setCoordinates(addCommandMethods.readCoordinates());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setHealth(addCommandMethods.readHealth());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setChapter(addCommandMethods.readChapterName());
// --------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setWeaponType(addCommandMethods.readWeaponType());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setMeleeWeapon(addCommandMethods.readMeleeWeaponCategory());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setCategory(addCommandMethods.readAstartesCategory());
// ---------------------------------------------------------------------------------------------------------------------
        tempList.add(addedSpcMrn);
        //spaceDeque = new ArrayDeque<>(tempList);
        System.out.println("Элемент успешно добавлен!");

        return new MessageToClient("Элемент успешно добавлен!", new ArrayDeque<>(tempList));
    }
}
