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

    public Command(ListOfCommands comandName, String userLogin, String userPassword) {
        this.comandName = comandName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;

    }

    public Command(ListOfCommands comandName, List<String> commandList, String userLogin, String userPassword) {
        this.comandName = comandName;
        this.commandList = commandList;
        this.userLogin = userLogin;
        this.userPassword = userPassword;

    }

    public Command(ListOfCommands comandName, int id, String userLogin, String userPassword) {
        this.comandName = comandName;
        this.id = id;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public Command(ListOfCommands comandName, String objectName, String userLogin, String userPassword) {
        this.comandName = comandName;
        this.objectName = objectName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public Command(ListOfCommands comandName, SpaceMarine spaceMarine, String userLogin, String userPassword) {
        this.comandName = comandName;
        this.spaceMarine = spaceMarine;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
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

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getObjectName() {
        return objectName;
    }


}
