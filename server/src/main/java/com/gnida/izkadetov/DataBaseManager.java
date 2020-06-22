package com.gnida.izkadetov;

import com.gnida.izkadetov.DataBase.DataBaseInitializer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DataBaseManager {
    private ArrayDeque<SpaceMarine> spc = new ArrayDeque<>();
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

    public void addMoreThanOneElementToCollection(List<SpaceMarine> spcList) {
        Iterator<SpaceMarine> iterator = spcList.iterator();
        while (iterator.hasNext()) {
            spc.add(iterator.next());
        }
    }

    public void addOneElementToCollection(SpaceMarine spaceMarine) {
        spc.add(spaceMarine);
    }

    public void removeElement(SpaceMarine spaceMarine) {
        int i = 0;
        ArrayList<SpaceMarine> spcc = new ArrayList<>(spc);
        while (i != spcc.size()) {
            if (spaceMarine.getId() == spcc.get(i).getId()) {
                spcc.remove(i);
                break;
            }
        }
    }

    public boolean checkLogin(String login) {
        if (login == null) {
            return false;
        } else {
            return true;
        }
    }

    public int getUserId(String login) {
        try {
            PreparedStatement statement = getDataBaseInitializer().getConnection().prepareStatement("select id from users where(username = ?)");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ;
        }
        return 0;
    }

    @Override
    public String toString() {
        return new String("Размер: " +
                spc.size() + "\n" + "Дата создания: " + this.getDateInitialization() + "\n" + "Дата последнего изменения:" + this.uptadeDateChange());
    }
}
