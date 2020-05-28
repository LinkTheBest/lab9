package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;

import java.util.ArrayDeque;

public class addcommand extends FatherOfCommands {

    public addcommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {

        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        if (!dataBaseManager.checkLogin(command.getUserLogin())) {
            return new MessageToClient("Вы не авторизованы!");
        } else {
            ArrayDeque<SpaceMarine> tempDeque = dataBaseManager.getObjects();
            tempDeque.add(command.getSpaceMarine());
            dataBaseManager.setObjects(tempDeque);
            System.out.println("Элемент успешно добавлен!");
            dataBaseManager.uptadeDateChange();
            return new MessageToClient("Элемент успешно добавлен! \n" + command.getSpaceMarine().toString());
        }
    }
}
