package com.gnida.izkadetov.commands;
import com.gnida.izkadetov.*;

public abstract class FatherOfCommands {
    protected DataBaseManager dataBaseManager;
    protected TbI_PROSTO_SUPER kryto;

    public FatherOfCommands(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto){

        this.dataBaseManager = dataBaseManager;
        this.kryto = kryto;
    }

public abstract MessageToClient executeCommand(Command command);

}
