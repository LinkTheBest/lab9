package com.gnida.izkadetov.commands;


import com.gnida.izkadetov.*;

public class InfoCommand extends FatherOfCommands {
    public InfoCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {
        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        if (!dataBaseManager.checkLogin(command.getUserLogin())) {
            return new MessageToClient("Вы не авторизованы!");
        } else {
            return new MessageToClient(dataBaseManager.toString());
        }
    }
}
