package com.gnida.izkadetov;

import java.io.Serializable;
import java.util.List;

public class Command implements Serializable {
    private List<String> commandList;
    private SpaceMarine spaceMarine;
    private String objectName;
    private String userLogin;
    private String userPassword;
    private ListOfCommands comandName;
    private int id;

    public Command(ListOfCommands comandName) {
        this.comandName = comandName;
    }

    public Command(ListOfCommands comandName, List<String> commandList) {
        this.comandName = comandName;
        this.commandList = commandList;
    }

    public Command(ListOfCommands comandName, int id) {
        this.comandName = comandName;
        this.id = id;
    }

    public Command(ListOfCommands comandName, String objectName) {
        this.comandName = comandName;
        this.objectName = objectName;
    }

    public Command(ListOfCommands comandName, SpaceMarine spaceMarine) {
        this.comandName = comandName;
        this.spaceMarine = spaceMarine;
    }

    public List<String> getCommandList() {
        return commandList;
    }

    public ListOfCommands getCommand() {
        return comandName;
    }

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }

    public int getId() {
        return id;
    }

    public String getObjectName() {
        return objectName;
    }


}
