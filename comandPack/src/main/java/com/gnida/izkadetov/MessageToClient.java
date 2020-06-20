package com.gnida.izkadetov;


import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MessageToClient implements Serializable {

    private int id;
    private String message;
    private ArrayDeque<SpaceMarine> spaceMarines;
    private List<String> helpArray;
    private ArrayList<MessageToClient> messageToClientsList = new ArrayList<>();

    public MessageToClient(String message) {
        this.message = message;
    }

    public MessageToClient(String message, ArrayDeque<SpaceMarine> spaceMarines) {
        this.message = message;
        this.spaceMarines = spaceMarines;
    }

    public MessageToClient(String message, ArrayList<MessageToClient> messageToClientsList) {
        this.message = message;
        this.messageToClientsList = messageToClientsList;
    }

    public MessageToClient(String message, List<String> helpArray) {
        this.message = message;
        this.helpArray = helpArray;
    }

    public MessageToClient(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public ArrayDeque<SpaceMarine> getSpaceMarines() {
        return spaceMarines;
    }

    public List<String> getHelpArray() {
        return helpArray;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<MessageToClient> getMessageToClientsList() {
        return messageToClientsList;
    }

    public int getId() {
        return id;
    }
}
