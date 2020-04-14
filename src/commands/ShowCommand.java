package commands;

import commandsRealization.Command;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;

public class ShowCommand extends FatherOfCommands {
    public ShowCommand(Collection collection, TbI_PROSTO_SUPER kryto) {
        super(collection, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        return new MessageToClient("Коллкция:", collection.getObjects());
    }
}
