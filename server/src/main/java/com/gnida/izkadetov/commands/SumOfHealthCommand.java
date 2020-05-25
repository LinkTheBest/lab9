package com.gnida.izkadetov.commands;


import com.gnida.izkadetov.*;

import java.util.ArrayDeque;

public class SumOfHealthCommand extends FatherOfCommands {
    public SumOfHealthCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {
        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        ArrayDeque<SpaceMarine> spaceDeque = dataBaseManager.getObjects();
        int sum = 0;
        for (SpaceMarine spc : spaceDeque) {
            sum = sum + spc.getHealth();
        }
        return new MessageToClient("Сумма полей всех объектов: " + sum);
    }
}
