package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.Command;
import com.gnida.izkadetov.DataBaseManager;
import com.gnida.izkadetov.MessageToClient;
import com.gnida.izkadetov.TbI_PROSTO_SUPER;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCommand extends FatherOfCommands {
    public LoginCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {
        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        try {
            PreparedStatement preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("insert into users (username) values (?)");
            preparedStatement.setString(1, command.getUserLogin());
            try {
                preparedStatement.execute();
                preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("delete from users where username = ?");
                preparedStatement.setString(1, command.getUserLogin());
                preparedStatement.execute();
                return new MessageToClient("Такой пользователь не существует! Зарегистрируйтесь !");
            } catch (SQLException e) {
                preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("select password from users where(username = ?)");
                preparedStatement.setString(1, command.getUserLogin());
                try {
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        if (command.getUserPassword() == resultSet.getString("password")) ;
                        return new MessageToClient("Вход выполнен!");
                    } else {
                        return new MessageToClient("Произошла ошибка");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Ошибка входа");
        }
        return null;
    }
}
