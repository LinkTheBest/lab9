package commands;

import commandsRealization.Command;
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
        //addCommand(name);
        // List<SpaceMarine> temp_list = new ArrayList<>(spaceDeque);
        // spaceDeque = findMax.makeDecision(temp_list);

        return null;
    }
}
