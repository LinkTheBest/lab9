package commands;

import ComandPack.Command;
import commandsRealization.FindMaxElement;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;

public class AddIfMaxCommand extends FatherOfCommands  {
    public AddIfMaxCommand(Collection collection, TbI_PROSTO_SUPER kryto){

        super(collection, kryto);
    }
    @Override
    public MessageToClient executeCommand(Command command) {
        FindMaxElement findMaxElement = new FindMaxElement();
        return new MessageToClient("Результат операции:", findMaxElement.makeDecision(collection));
    }

}
