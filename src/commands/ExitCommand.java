package commands;

import client.Colors;
import commandsRealization.Command;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;

public class ExitCommand extends FatherOfCommands {
    public ExitCommand(Collection collection, TbI_PROSTO_SUPER kryto) {
        super(collection, kryto);
    }
    @Override
    public MessageToClient executeCommand(Command command) {
        System.out.println(Colors.RED_BOLD);
        System.out.println("ВЫХОД ИЗ ПРОГРАММЫ!");
        System.exit(0);
        return null;
    }
}
