package com.gnida.izkadetov.commands;

import com.gnida.izkadetov.Command;
import com.gnida.izkadetov.DataBaseManager;
import com.gnida.izkadetov.MessageToClient;
import com.gnida.izkadetov.TbI_PROSTO_SUPER;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationCommand extends FatherOfCommands {
    public RegistrationCommand(DataBaseManager dataBaseManager, TbI_PROSTO_SUPER kryto) {
        super(dataBaseManager, kryto);
    }

    @Override
    public MessageToClient executeCommand(Command command) {
        try {
            PreparedStatement preparedStatement = dataBaseManager.getDataBaseInitializer().getConnection().prepareStatement("insert into users (username, password) values (?, ?) ");
            preparedStatement.setString(1, command.getUserLogin());
            preparedStatement.setString(2, encryptPassword(command.getUserPassword()));
            try {
                preparedStatement.execute();
                return new MessageToClient("Регистрация прошла успешно!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return new MessageToClient("Такой логин уже существует! Попробуйте другой");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new MessageToClient("Ошибка регистрации");
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
