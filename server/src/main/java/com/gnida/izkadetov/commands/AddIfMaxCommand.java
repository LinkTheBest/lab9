package com.gnida.izkadetov.commands;
import com.gnida.izkadetov.*;
import com.gnida.izkadetov.commandsRealization.FindMaxElement;

public class AddIfMaxCommand extends FatherOfCommands  {
    public AddIfMaxCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto){

        super(dataBaseManager, kryto);
    }
    @Override
    public MessageToClient executeCommand(Command command) {
        FindMaxElement findMaxElement = new FindMaxElement();
        return new MessageToClient("Результат операции:", findMaxElement.makeDecision(dataBaseManager.getObjects()));
    }

}
