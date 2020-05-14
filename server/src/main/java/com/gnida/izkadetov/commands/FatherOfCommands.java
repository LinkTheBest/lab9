package com.gnida.izkadetov.commands;
import com.gnida.izkadetov.Collection;
import com.gnida.izkadetov.Command;
import com.gnida.izkadetov.MessageToClient;
import com.gnida.izkadetov.TbI_PROSTO_SUPER;

public abstract class FatherOfCommands {
    protected Collection collection;
    protected TbI_PROSTO_SUPER kryto;

    public FatherOfCommands(Collection collection, TbI_PROSTO_SUPER kryto){

        this.collection = collection;
        this.kryto = kryto;
    }

public abstract MessageToClient executeCommand(Command command);

}
