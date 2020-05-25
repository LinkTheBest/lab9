package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;

public class ExitCommand extends FatherOfCommands {
    public ExitCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {
        super(dataBaseManager, kryto);
    }
    @Override
    public MessageToClient executeCommand(Command command) {
        System.out.println(Colors.RED_BOLD);
        System.out.println("ВЫХОД ИЗ ПРОГРАММЫ!");
        System.exit(0);
        return null;
    }
}
