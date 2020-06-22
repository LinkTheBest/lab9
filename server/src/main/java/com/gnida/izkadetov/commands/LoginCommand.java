package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.Command;
import com.gnida.izkadetov.DataBaseManager;
import com.gnida.izkadetov.MessageToClient;
import com.gnida.izkadetov.TbI_PROSTO_SUPER;
import sun.applet.AppletResourceLoader;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
                System.out.println(e.getMessage());
                try {
                    preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("select password from users where username = ?");
                    preparedStatement.setString(1, command.getUserLogin());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        if (encryptPassword(command.getUserPassword()) == resultSet.getString("password")) {
                            return new MessageToClient("Вход выполнен!");
                        }
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
        try {
            int i = 0;
            PreparedStatement preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("select id from users where username = ?");
            preparedStatement.setString(1, command.getUserLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                i = resultSet.getInt("id");
            }
            return new MessageToClient("Вход выполнен", i);
        } catch (SQLException e) {
            return new MessageToClient("");
        }

    }

    private String encryptPassword(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] messageDigest = md.digest(pwd.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
