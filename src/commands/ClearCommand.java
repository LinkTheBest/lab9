package commands;

import ComandPack.Command;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;

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
