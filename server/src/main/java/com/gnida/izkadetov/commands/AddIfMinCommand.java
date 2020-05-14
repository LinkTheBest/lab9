package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.Collection;
import com.gnida.izkadetov.Command;
import com.gnida.izkadetov.MessageToClient;
import com.gnida.izkadetov.TbI_PROSTO_SUPER;
import com.gnida.izkadetov.commandsRealization.FindMinElement;


public class AddIfMinCommand extends FatherOfCommands {
    public AddIfMinCommand(Collection collection, TbI_PROSTO_SUPER kryto){

        super(collection, kryto);
    }
    @Override
    public MessageToClient executeCommand(Command command) {
        FindMinElement finMin = new FindMinElement();
        return new MessageToClient("Результат работы:", finMin.makeDecision(collection));
    }
}
