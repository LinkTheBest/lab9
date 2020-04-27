package client;

import commandsRealization.AddCommandMethods;
import spaceMarineProperties.SpaceMarine;

import java.util.Scanner;

public class CreatingNewObject {
    private String name;

    public CreatingNewObject(String name){
        this.name = name;
    }

    public SpaceMarine createObject() {
        Scanner scn = new Scanner(System.in);
        AddCommandMethods addCommandMethods = new AddCommandMethods();
        SpaceMarine addedSpcMrn = new SpaceMarine();
        System.out.println(Colors.BLACK_BOLD);
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
