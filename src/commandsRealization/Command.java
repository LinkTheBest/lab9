package commandsRealization;

import java.io.Serializable;
import java.util.ArrayList;

public class Command implements Serializable {
    private String objectName;
    private ListOfCommands comandName;
    private ArrayList<Command> commandsForScript;
    private int id;

    public Command(ListOfCommands comandName){
        this.comandName = comandName;
    }

    public Command(ListOfCommands comandName, int id){
        this.comandName = comandName;
        this.id = id;
    }

    public Command(ListOfCommands comandName, String objectName ){
        this.comandName = comandName;
        this.objectName = objectName;
    }

    public ListOfCommands getCommand(){
        return comandName;
    }

    public int getId(){
        return id;
    }

    public String getObjectName(){
        return objectName;
    }


}
