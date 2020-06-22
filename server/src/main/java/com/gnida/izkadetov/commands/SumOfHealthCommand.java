package com.gnida.izkadetov.commands;


import com.gnida.izkadetov.*;

import java.util.*;

public class SumOfHealthCommand extends FatherOfCommands {
    public SumOfHealthCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {
        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        if (!dataBaseManager.checkLogin(command.getUserLogin())) {
            return new MessageToClient("Вы не авторизованы!");
        } else {
            List<SpaceMarine> spc = Collections.synchronizedList(new ArrayList<>(dataBaseManager.getObjects()));
            synchronized (spc) {
                long sum = 0;
                Iterator<SpaceMarine> iterator = spc.iterator();
                while (iterator.hasNext()) {
                    sum = sum + iterator.next().getHealth();
                }
                return new MessageToClient("Сумма полей всех объектов: " + sum);
            }
        }
    }
}
