package server;

import spaceMarineProperties.SpaceMarine;

import java.io.Serializable;
import java.util.ArrayDeque;

public class MessageToClient implements Serializable {
    private String message;
    private ArrayDeque<SpaceMarine> spaceMarines;

    public MessageToClient(String message){
        this.message = message;
    }

    public MessageToClient(String message, ArrayDeque<SpaceMarine> spaceMarines){
        this.message = message;
        this.spaceMarines = spaceMarines;
    }

    public ArrayDeque<SpaceMarine> getSpaceMarines(){
        return spaceMarines;
    }
    public String getMessage(){
        return message;
    }
}
