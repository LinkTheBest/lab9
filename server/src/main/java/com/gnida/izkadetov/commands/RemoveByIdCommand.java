package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
            try {
                PreparedStatement preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("delete from spaceMarines where spcid = ? and userid = ? returning spcid");
                preparedStatement.setInt(1, command.getId());
                preparedStatement.setInt(2, dataBaseManager.getUserId(command.getUserLogin()));
                ResultSet resultSet = preparedStatement.executeQuery();
                List<SpaceMarine> spc = Collections.synchronizedList(new ArrayList<>(dataBaseManager.getObjects()));
                synchronized (spc) {
                    if (spc.size() > 0) {
                        if (resultSet.next()) {
                            int id = command.getId();
                            spc.removeAll((spc.stream().filter(lil -> lil.getId() == id)
                                    .collect(Collectors.toCollection(ArrayDeque::new))));
                        }
                        dataBaseManager.uptadeDateChange();
                        dataBaseManager.setObjects(new ArrayDeque<>(spc));
                        return new MessageToClient("Элемент коллекции успешно удалён.");
                    } else return new MessageToClient("Коллекция пуста.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return new MessageToClient("Произошла ошибка удаления из БД!");
            }
        }
    }
}

