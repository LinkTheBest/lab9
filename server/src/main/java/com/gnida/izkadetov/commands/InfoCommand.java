package com.gnida.izkadetov.commands;


import com.gnida.izkadetov.Collection;
import com.gnida.izkadetov.Command;
import com.gnida.izkadetov.MessageToClient;
import com.gnida.izkadetov.TbI_PROSTO_SUPER;

public class InfoCommand extends FatherOfCommands {
    public InfoCommand(Collection collection, TbI_PROSTO_SUPER kryto) {
        super(collection, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) { return new MessageToClient(collection.toString()); }
}
