package commands;

import ComandPack.Command;
import commandsRealization.FindMinElement;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;


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
