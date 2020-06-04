package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.*;
import com.gnida.izkadetov.commandsRealization.FindMaxElement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddIfMaxCommand extends FatherOfCommands {
    public AddIfMaxCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {

        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        if (!dataBaseManager.checkLogin(command.getUserLogin())) {
            return new MessageToClient("Вы не авторизованы!");
        } else {
            FindMaxElement findMaxElement = new FindMaxElement();
            dataBaseManager.addOneElementToCollection(command.getSpaceMarine());
            if (findMaxElement.makeDecision(dataBaseManager.getObjects())) {
                try {
                    PreparedStatement preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("" +
                            "insert into spacemarines(spcid, spcname, x,y,health, category, weaponType, meleeWeapon, chapter, userId)" +
                            "values (?,?,?,?,?,?,?,?,?,?)");
                    preparedStatement.setInt(1, findMaxElement.getNewElement().getId());
                    preparedStatement.setString(2, findMaxElement.getNewElement().getName());
                    preparedStatement.setDouble(3, findMaxElement.getNewElement().getCoordinates().getX());
                    preparedStatement.setFloat(4, findMaxElement.getNewElement().getCoordinates().getY());
                    preparedStatement.setInt(5, findMaxElement.getNewElement().getHealth());
                    preparedStatement.setString(6, findMaxElement.getNewElement().getCategory());
                    preparedStatement.setString(7, findMaxElement.getNewElement().getWeaponType());
                    preparedStatement.setString(8, findMaxElement.getNewElement().getMeleeWeapon());
                    preparedStatement.setString(9, findMaxElement.getNewElement().getChapter().getName());
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
