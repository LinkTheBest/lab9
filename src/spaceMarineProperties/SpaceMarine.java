package spaceMarineProperties;

import java.io.Serializable;
import java.time.LocalDate;

public class SpaceMarine implements Comparable, Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer health; //Поле может быть null, Значение поля должно быть больше 0
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле может быть null
    private Chapter chapter; //Поле может быть null

    public SpaceMarine() {
        double temp;
        temp = Math.random() * 1000000;
        id = (int) temp;
        creationDate = LocalDate.now();
    }

    public int getId() { return id; }

    public void setId(int id){ this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public String cordinatesToString() {
        return "X: "+ String.valueOf(coordinates.getX())+"\n" + "Y: " + String.valueOf(coordinates.getY());
    }

    public Coordinates getCoordinates(){return  coordinates;}

    public Integer getHealth() {
        return health;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public String chapterToString() {
        return chapter.getName() + chapter.getParentLegion();
    }

    public Chapter getChapter(){return chapter;}


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

    public String toString(){
        String object = "ID: " + this.getId() + "\n"
                + "Name: "+ this.getName() + "\n"
                + "Coordinates: " + this.cordinatesToString() + "\n"
                + "Health: " + this.getHealth() +"\n"
                + "Category: " +this.getCategory() + "\n"
                + "Chapter: " +this.chapterToString() + "\n"
                + "MeleeWeapon: " +this.getMeleeWeapon() + "\n"
                + "Weapon type: " +this.getWeaponType() + "\n";
        return object;
    }
}


