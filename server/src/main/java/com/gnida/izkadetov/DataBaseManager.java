package com.gnida.izkadetov;

import com.gnida.izkadetov.DataBase.DataBaseInitializer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Date;

public class DataBaseManager {
    private ArrayDeque<SpaceMarine> spc;
    private Date dateInitialization = new Date();
    private DataBaseInitializer dataBaseInitializer;


    public DataBaseInitializer getDataBaseInitializer() {
        return dataBaseInitializer;
    }

    public void setDataBaseInitializer(DataBaseInitializer dataBaseInitializer) {
        this.dataBaseInitializer = dataBaseInitializer;
    }

    public ArrayDeque<SpaceMarine> getObjects() {
        return spc;
    }

    public void setObjects(ArrayDeque<SpaceMarine> spc) {
        this.spc = spc;
    }

    public Date getDateInitialization() {
        return dateInitialization;
    }

    public Date uptadeDateChange() {
        return dateInitialization = new Date();
    }

    public void clearCollection() {
        spc.clear();
    }

    public boolean checkLogin(String login) {
        if (login == null) {
            return false;
        } else {
            return true;
        }
    }
    public int getUserId(String login){
        try {
            PreparedStatement statement = getDataBaseInitializer().getConnection().prepareStatement("select id from users where(username = ?)");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return  resultSet.getInt("id");
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());;
        }
        return 0;
    }

    @Override
    public String toString() {
        return new String("Размер: " +
                spc.size() + "\n" + "Дата создания: " + this.getDateInitialization() + "\n" + "Дата последнего изменения:" + this.uptadeDateChange());
    }
}
