package commands;

import ComandPack.Command;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;
import spaceMarineProperties.SpaceMarine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrintFieldDescendingHealth extends FatherOfCommands {
    public PrintFieldDescendingHealth(Collection collection, TbI_PROSTO_SUPER kryto) {

        super(collection, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        ArrayDeque<SpaceMarine> spc = collection.getObjects();
        List<SpaceMarine> temp = spc.stream().sorted((c1, c2) -> (int) ((c2.getHealth() - c1.getHealth()) * 100)).collect(Collectors.toCollection(ArrayList::new));
        return new MessageToClient("Команда была выполнена!", new ArrayDeque<>(temp));
    }
}
