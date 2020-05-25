package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;

public class ClearCommand extends FatherOfCommands {

    public ClearCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto){

        super(dataBaseManager, kryto);
    }
    @Override
    public MessageToClient executeCommand(Command command) {
        dataBaseManager.clearCollection();
        return new MessageToClient("Коллекция была очищена");
    }
}
