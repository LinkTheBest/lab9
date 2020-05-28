package com.gnida.izkadetov;

import com.gnida.izkadetov.DataBase.DataBaseInitializer;

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

    @Override
    public String toString() {
        return new String("Размер: " +
                spc.size() + "\n" + "Дата создания: " + this.getDateInitialization() + "\n" + "Дата последнего изменения:" + this.uptadeDateChange());
    }
}
