package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;
import com.gnida.izkadetov.commandsRealization.FindMinElement;


public class AddIfMinCommand extends FatherOfCommands {
    public AddIfMinCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto){

        super(dataBaseManager, kryto);
    }
    @Override
    public MessageToClient executeCommand(Command command) {
        FindMinElement finMin = new FindMinElement();
        return new MessageToClient("Результат работы:", finMin.makeDecision(dataBaseManager.getObjects()));
    }
}
