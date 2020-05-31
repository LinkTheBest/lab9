package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PrintFieldDescendingHealth extends FatherOfCommands {
    public PrintFieldDescendingHealth(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {
        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        if (!dataBaseManager.checkLogin(command.getUserLogin())) {
            return new MessageToClient("Вы не авторизованы!");
        } else {
            List<SpaceMarine> spc = Collections.synchronizedList(new ArrayList<>(dataBaseManager.getObjects()));
            synchronized (spc) {
                List<SpaceMarine> temp = spc.stream().sorted((c1, c2) -> (int) ((c2.getHealth() - c1.getHealth()) * 100)).collect(Collectors.toCollection(ArrayList::new));
                return new MessageToClient("Команда была выполнена!", new ArrayDeque<>(temp));
            }
        }
    }
}
