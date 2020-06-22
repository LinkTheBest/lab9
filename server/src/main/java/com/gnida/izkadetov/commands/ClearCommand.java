package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ClearCommand extends FatherOfCommands {

    public ClearCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {

        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        if (!dataBaseManager.checkLogin(command.getUserLogin())) {
            return new MessageToClient("Вы не авторизованы!");
        } else {
            try {
                List<SpaceMarine> tempDeque = Collections.synchronizedList(new ArrayList<>(dataBaseManager.getObjects()));
                ResultSet resultSet = deleteElementFromDataBase(command);
                synchronized (tempDeque) {
                    while (resultSet.next()) {
                        Iterator<SpaceMarine> iterator = tempDeque.iterator();
                        while (iterator.hasNext()) {
                            if (iterator.next().getId() == resultSet.getInt("spcid")) {
                                iterator.remove();
                            }
                        }
                    }
                    dataBaseManager.setObjects(new ArrayDeque<>(tempDeque));
                }
                return new MessageToClient("Коллекция была очищена");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return new MessageToClient("Произошла ошибка удаления элемента из БД");
            }
        }
    }

    public ResultSet deleteElementFromDataBase(Command command) throws SQLException {
        PreparedStatement preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("delete from spaceMarines where userId = ? returning spcid");
        preparedStatement.setInt(1, dataBaseManager.getUserId(command.getUserLogin()));
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}
