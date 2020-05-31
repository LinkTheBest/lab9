package com.gnida.izkadetov;

public class CreatingNewObject {
    private String name;

    public CreatingNewObject(String name) {
        this.name = name;
    }

    public SpaceMarine createObject() {
        AddCommandMethods addCommandMethods = new AddCommandMethods();
        SpaceMarine addedSpcMrn = new SpaceMarine();
        System.out.println(Colors.BLACK_BOLD);
        addedSpcMrn.setName(name);
        addedSpcMrn.setId(addCommandMethods.readId());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setXCoordinate(addCommandMethods.readXCoordinate().getX());
        addedSpcMrn.setYCoordinate(addCommandMethods.readYCoordinate().getY());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setHealth(addCommandMethods.readHealth());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setChapter(addCommandMethods.readChapterName().getName());
// --------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setWeaponType(addCommandMethods.readWeaponType().getWeaponName());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setMeleeWeapon(addCommandMethods.readMeleeWeaponCategory().getMeleeWeaponName());
// ---------------------------------------------------------------------------------------------------------------------
        addedSpcMrn.setCategory(addCommandMethods.readAstartesCategory().getCategoryName());
// ---------------------------------------------------------------------------------------------------------------------
        return addedSpcMrn;
    }
}
