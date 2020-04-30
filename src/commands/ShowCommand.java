package commands;

import ComandPack.Command;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;

public class ShowCommand extends FatherOfCommands {
    public ShowCommand(Collection collection, TbI_PROSTO_SUPER kryto) {
        super(collection, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        if (collection.getObjects() == null) {
            return new MessageToClient("Коллекция пуста!");
        } else {
            return new MessageToClient("Коллeкция:", collection.getObjects());
        }
    }
}
