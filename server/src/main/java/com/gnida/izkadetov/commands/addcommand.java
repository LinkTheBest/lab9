package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class addcommand extends FatherOfCommands {

    public addcommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {

        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        if (!dataBaseManager.checkLogin(command.getUserLogin())) {
            return new MessageToClient("Вы не авторизованы!");
        } else {
            try {
                addElementToDataBase(command);
                List<SpaceMarine> tempDeque = Collections.synchronizedList(new ArrayList<>());
                synchronized (tempDeque) {
                    tempDeque.add(command.getSpaceMarine());
                }
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

    public void addElementToDataBase(Command command) throws SQLException {
        PreparedStatement preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("" +
                "insert into spacemarines(spcid, spcname, x,y,health, category, weaponType, meleeWeapon, chapter, userId)" +
                "values (?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, command.getSpaceMarine().getId());
        preparedStatement.setString(2, command.getSpaceMarine().getName());
        preparedStatement.setDouble(3, command.getSpaceMarine().getCoordinates().getX());
        preparedStatement.setFloat(4, command.getSpaceMarine().getCoordinates().getY());
        preparedStatement.setInt(5, command.getSpaceMarine().getHealth());
        preparedStatement.setString(6, command.getSpaceMarine().getCategory());
        preparedStatement.setString(7, command.getSpaceMarine().getWeaponType());
        preparedStatement.setString(8, command.getSpaceMarine().getMeleeWeapon());
        preparedStatement.setString(9, command.getSpaceMarine().getChapter().getName());
        preparedStatement.setInt(10, dataBaseManager.getUserId(command.getUserLogin()));
        preparedStatement.execute();

    }
}
