package client;

import commandsRealization.AddCommandMethods;
import spaceMarineProperties.SpaceMarine;

import java.util.Scanner;

public class CreatingNewObject {

    public SpaceMarine createObject() {
        Scanner scn = new Scanner(System.in);
        AddCommandMethods addCommandMethods = new AddCommandMethods();
        SpaceMarine addedSpcMrn = new SpaceMarine();
        System.out.println(Colors.BLACK_BOLD);
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
        return addedSpcMrn;
    }
}
