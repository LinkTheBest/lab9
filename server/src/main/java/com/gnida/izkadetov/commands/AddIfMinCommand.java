package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;
import com.gnida.izkadetov.commandsRealization.FindMinElement;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddIfMinCommand extends FatherOfCommands {
    public AddIfMinCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {

        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        if (!dataBaseManager.checkLogin(command.getUserLogin())) {
            return new MessageToClient("Вы не авторизованы!");
        } else {
            FindMinElement finMin = new FindMinElement();
            dataBaseManager.addOneElementToCollection(command.getSpaceMarine());
            if (finMin.makeDecision(dataBaseManager.getObjects())) {
                try {
                    PreparedStatement preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("" +
                            "insert into spacemarines(spcid, spcname, x,y,health, category, weaponType, meleeWeapon, chapter, userId)" +
                            "values (?,?,?,?,?,?,?,?,?,?)");
                    preparedStatement.setInt(1, finMin.getNewElement().getId());
                    preparedStatement.setString(2, finMin.getNewElement().getName());
                    preparedStatement.setDouble(3, finMin.getNewElement().getCoordinates().getX());
                    preparedStatement.setFloat(4, finMin.getNewElement().getCoordinates().getY());
                    preparedStatement.setInt(5, finMin.getNewElement().getHealth());
                    preparedStatement.setString(6, finMin.getNewElement().getCategory());
                    preparedStatement.setString(7, finMin.getNewElement().getWeaponType());
                    preparedStatement.setString(8, finMin.getNewElement().getMeleeWeapon());
                    preparedStatement.setString(9, finMin.getNewElement().getChapter().getName());
                    preparedStatement.setInt(10, dataBaseManager.getUserId(command.getUserLogin()));
                    preparedStatement.execute();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                dataBaseManager.getObjects().removeLast();
            }
            return new MessageToClient("Команда была выполнена");
        }
    }
}
