package commands;

import ComandPack.Command;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;
import spaceMarineProperties.SpaceMarine;

import java.util.ArrayDeque;

public class SumOfHealthCommand extends FatherOfCommands {
    public SumOfHealthCommand(Collection collection, TbI_PROSTO_SUPER kryto) {
        super(collection, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        ArrayDeque<SpaceMarine> spaceDeque = collection.getObjects();
        int sum = 0;
        for (SpaceMarine spc : spaceDeque) {
            sum = sum + spc.getHealth();
        }
        return new MessageToClient("Сумма полей всех объектов: " + sum);
    }
}
