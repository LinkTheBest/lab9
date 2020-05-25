package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;

public class ShowCommand extends FatherOfCommands {
    public ShowCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {
        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        if (dataBaseManager.getObjects() == null) {
            return new MessageToClient("Коллекция пуста!");
        } else {
            return new MessageToClient("Коллeкция:", dataBaseManager.getObjects());
        }
    }
}
