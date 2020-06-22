package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UpdateCommand extends FatherOfCommands {
    public UpdateCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {
        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        if (!dataBaseManager.checkLogin(command.getUserLogin())) {
            return new MessageToClient("Вы не авторизованы!");
        } else {
            try {
                updateElemenInDataBase(command);
                List<SpaceMarine> tempDeque = Collections.synchronizedList(new ArrayList<>());
                synchronized (tempDeque) {
                    SpaceMarine spc = command.getSpaceMarine();
                    spc.setUserId(dataBaseManager.getUserId(command.getUserLogin()));
                    tempDeque.add(spc);
                }
                dataBaseManager.removeElement(command.getSpaceMarine());
                dataBaseManager.addMoreThanOneElementToCollection(tempDeque);
                System.out.println("Элемент успешно добавлен!");
                dataBaseManager.uptadeDateChange();
                return new MessageToClient("Элемент успешно добавлен! \n" + command.getSpaceMarine().toString());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return new MessageToClient("Произошла ошибка при добавлении элемента в БД!");
            }
        }
    }

    public void updateElemenInDataBase(Command command) throws SQLException {
        PreparedStatement preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("" +
                " update spacemarines set spcname = ?, x= ?,y= ?,health= ?, category= ?, weaponType= ?, meleeWeapon= ?, chapter= ?" +
                "where userId= ?, spcid= ?");
        preparedStatement.setInt(10, command.getSpaceMarine().getId());
        preparedStatement.setString(1, command.getSpaceMarine().getName());
        preparedStatement.setDouble(2, command.getSpaceMarine().getCoordinates().getX());
        preparedStatement.setFloat(3, command.getSpaceMarine().getCoordinates().getY());
        preparedStatement.setInt(4, command.getSpaceMarine().getHealth());
        preparedStatement.setString(5, command.getSpaceMarine().getCategory());
        preparedStatement.setString(6, command.getSpaceMarine().getWeaponType());
        preparedStatement.setString(7, command.getSpaceMarine().getMeleeWeapon());
        preparedStatement.setString(8, command.getSpaceMarine().getChapter().getName());
        preparedStatement.setInt(9, dataBaseManager.getUserId(command.getUserLogin()));
        preparedStatement.execute();
    }
}
