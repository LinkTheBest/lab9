package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class RemoveByIdCommand extends FatherOfCommands {
    public RemoveByIdCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {

        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        if (!dataBaseManager.checkLogin(command.getUserLogin())) {
            return new MessageToClient("Вы не авторизованы!");
        } else {
            ArrayDeque<SpaceMarine> spc = dataBaseManager.getObjects();
            int startSize = spc.size();
            if (spc.size() > 0) {
                int id = command.getId();
                spc.removeAll((spc.stream().filter(lil -> lil.getId() == id)
                        .collect(Collectors.toCollection(ArrayDeque::new))));
                if (startSize == spc.size()) {
                    return new MessageToClient("Элемент с id " + id + " не существует.");
                }
                dataBaseManager.uptadeDateChange();
                return new MessageToClient("Элемент коллекции успешно удалён.");
            } else return new MessageToClient("Коллекция пуста.");
        }
    }

}

