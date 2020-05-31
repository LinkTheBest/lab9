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
                deleteElementFromDataBase(command);
                List<SpaceMarine> spc = Collections.synchronizedList(new ArrayList<>(dataBaseManager.getObjects()));
                synchronized (spc) {
                    int startSize = spc.size();
                    if (spc.size() > 0) {
                        int id = command.getId();
                        spc.removeAll((spc.stream().filter(lil -> lil.getId() == id)
                                .collect(Collectors.toCollection(ArrayDeque::new))));
                        if (startSize == spc.size()) {
                            return new MessageToClient("Элемент с id " + id + " не существует.");
                        }
                        dataBaseManager.uptadeDateChange();
                        dataBaseManager.setObjects(new ArrayDeque<>(spc));
                        return new MessageToClient("Элемент коллекции успешно удалён.");
                    } else return new MessageToClient("Коллекция пуста.");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return new MessageToClient("Ошибка при удалении из БД, у вас недостаточно прав!");
            }
        }
    }

    public void deleteElementFromDataBase(Command command) throws SQLException {
        PreparedStatement preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("delete from spaceMarines where userId = ? and spcid = ?");
        preparedStatement.setInt(1, dataBaseManager.getUserId(command.getUserLogin()));
        preparedStatement.setInt(2, command.getId());
        preparedStatement.executeUpdate();
    }
}

