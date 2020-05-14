package com.gnida.izkadetov.commands;
import com.gnida.izkadetov.Collection;
import com.gnida.izkadetov.Command;
import com.gnida.izkadetov.MessageToClient;
import com.gnida.izkadetov.TbI_PROSTO_SUPER;
import com.gnida.izkadetov.commandsRealization.FindMaxElement;

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
