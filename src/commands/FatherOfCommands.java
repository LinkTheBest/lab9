package commands;

import commandsRealization.Command;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;


public abstract class FatherOfCommands {
    protected Collection collection;
    protected TbI_PROSTO_SUPER kryto;

    public FatherOfCommands(Collection collection, TbI_PROSTO_SUPER kryto){

        this.collection = collection;
        this.kryto = kryto;
    }

public abstract MessageToClient executeCommand(Command command);

}
