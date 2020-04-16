package server;

import commandsRealization.ListOfCommands;
import spaceMarineProperties.SpaceMarine;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.List;

public class MessageToClient implements Serializable {
    private SpaceMarine spaceMarine;
    private String message;
    private ArrayDeque<SpaceMarine> spaceMarines;
    private List<String> helpArray;

    public MessageToClient(String message){
        this.message = message;
    }

    public MessageToClient(String message, ArrayDeque<SpaceMarine> spaceMarines){
        this.message = message;
        this.spaceMarines = spaceMarines;
    }

    public MessageToClient(String message, List<String> helpArray){
        this.message = message;
        this.helpArray = helpArray;
    }

    public ArrayDeque<SpaceMarine> getSpaceMarines(){
        return spaceMarines;
    }
    public List<String> getHelpArray(){return helpArray;}
    public String getMessage(){
        return message;
    }
}
