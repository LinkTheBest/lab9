package commands;

import commandsRealization.Command;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;

public class ExecuteScriptCommand extends FatherOfCommands{
    public ExecuteScriptCommand(Collection collection, TbI_PROSTO_SUPER kryto){
        super(collection, kryto);
    }
    @Override
    public MessageToClient executeCommand(Command command) {

        return null;
    }
}
