package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.Collection;
import com.gnida.izkadetov.Command;
import com.gnida.izkadetov.MessageToClient;
import com.gnida.izkadetov.TbI_PROSTO_SUPER;

public class ClearCommand extends FatherOfCommands {

    public ClearCommand(Collection collection, TbI_PROSTO_SUPER kryto){

        super(collection, kryto);
    }
    @Override
    public MessageToClient executeCommand(Command command) {
        collection.clearCollection();
        return new MessageToClient("Коллекция была очищена");
    }
}
