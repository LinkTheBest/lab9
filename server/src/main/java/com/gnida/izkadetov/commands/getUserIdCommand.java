package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.Command;
import com.gnida.izkadetov.DataBaseManager;
import com.gnida.izkadetov.MessageToClient;
import com.gnida.izkadetov.TbI_PROSTO_SUPER;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getUserIdCommand extends FatherOfCommands {
    public getUserIdCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {
        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        int id = 0;
        try {
            PreparedStatement preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("select id from users where username = ?");
            preparedStatement.setString(1, command.getUserLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            System.out.println(id);
            return new MessageToClient("", id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new MessageToClient("");
        }
    }
}
