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

    public ArrayDeque<SpaceMarine> getSpc() {
        return spc;
    }

    public void setSpc(ArrayDeque<SpaceMarine> spc) {
        this.spc = spc;
    }

    public Date getDateInitialization() {
        return dateInitialization;
    }
}
