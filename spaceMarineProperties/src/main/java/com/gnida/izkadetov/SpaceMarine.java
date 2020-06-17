package com.gnida.izkadetov;

import java.io.Serializable;
import java.time.LocalDate;

public class SpaceMarine implements Comparable, Serializable {
    private int id;
    private Double x;
    private Float y; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates = new Coordinates(); //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer health; //Поле может быть null, Значение поля должно быть больше 0
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле может быть null
    private Chapter chapter; //Поле может быть null

    public SpaceMarine() {
        creationDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setXCoordinate(Double xCoordinate) {
        x = xCoordinate;
        coordinates.setX(xCoordinate);
    }

    public void setYCoordinate(Float yCoordinate) {

        y = yCoordinate;
        coordinates.setY(yCoordinate);
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public void setCategory(String categoryName) {

        switch (categoryName) {
            case "agressor":
                category = AstartesCategory.AGGRESSOR;
                return;
            case "inceptor":
                category = AstartesCategory.INCEPTOR;
                return;
            case "tactical":
                category = AstartesCategory.TACTICAL;
                return;
            case "terminator":
                category = AstartesCategory.TERMINATOR;
                return;
        }
    }

    public void setWeaponType(String weaponName) {
        switch (weaponName) {
            case "meltagun":
                weaponType = Weapon.MELTAGUN;
                return;
            case "boltPistol":
                weaponType = Weapon.BOLT_PISTOL;
                return;
            case "combiFlamer":
                weaponType = Weapon.COMBI_FLAMER;
                return;
            case "combiPlasmaGun":
                weaponType = Weapon.COMBI_PLASMA_GUN;
                return;
            case "missileLauncher":
                weaponType = Weapon.MISSILE_LAUNCHER;
                return;
        }
    }

    public void setMeleeWeapon(String meleeWeaponName) {
        switch (meleeWeaponName) {
            case "chainSword":
                meleeWeapon = MeleeWeapon.CHAIN_SWORD;
                return;
            case "manreaper":
                meleeWeapon = MeleeWeapon.MANREAPER;
                return;
            case "powerBlade":
                meleeWeapon = MeleeWeapon.POWER_BLADE;
                return;
        }
    }

    public void setChapter(String chapterName) {
        chapter = new Chapter();
        chapter.setName(chapterName);
    }

    public String cordinatesToString() {
        return "X: " + String.valueOf(coordinates.getX()) + "\n" + "Y: " + String.valueOf(coordinates.getY());
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Integer getHealth() {
        return health;
    }

    public String getCategory() {
        return category.getCategoryName();
    }

    public String getWeaponType() {
        return weaponType.getWeaponName();
    }

    public String getMeleeWeapon() {
        return meleeWeapon.getMeleeWeaponName();
    }

    public String chapterToString() {
        return chapter.getName();
    }

    public Chapter getChapter() {
        return chapter;
    }


    @Override
    public int compareTo(Object o) {
        SpaceMarine temp_space_marine = (SpaceMarine) o;
        if (this.health > temp_space_marine.health) {
            return -1;
        } else if (this.health < temp_space_marine.health) {
            return 1;
        }
        return 0;
    }

    public String toString() {
        String object = "ID: " + this.getId() + "\n"
                + "Name: " + this.getName() + "\n"
                + "Coordinates: " + this.cordinatesToString() + "\n"
                + "Health: " + this.getHealth() + "\n"
                + "Category: " + this.getCategory() + "\n"
                + "Chapter: " + this.chapterToString() + "\n"
                + "MeleeWeapon: " + this.getMeleeWeapon() + "\n"
                + "Weapon type: " + this.getWeaponType() + "\n";
        return object;
    }
}


