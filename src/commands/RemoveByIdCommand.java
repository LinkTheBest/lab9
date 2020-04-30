package commands;

import ComandPack.Command;
import server.Collection;
import server.MessageToClient;
import server.TbI_PROSTO_SUPER;
import spaceMarineProperties.SpaceMarine;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class RemoveByIdCommand extends FatherOfCommands {
    public RemoveByIdCommand(Collection collection, TbI_PROSTO_SUPER kryto) {

        super(collection, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        ArrayDeque<SpaceMarine> spc = collection.getObjects();
        int startSize = spc.size();
        if (spc.size() > 0) {
            int id = command.getId();
            spc.removeAll((spc.stream().filter(lil -> lil.getId() == id)
                    .collect(Collectors.toCollection(ArrayDeque::new))));
            if (startSize == spc.size()) {
                return new MessageToClient("Элемент с id " + id + " не существует.");
            }
            collection.uptadeDateChange();
            return new MessageToClient("Элемент коллекции успешно удалён.");
        } else return new MessageToClient("Коллекция пуста.");
    }

    }

