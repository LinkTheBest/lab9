package server;

import spaceMarineProperties.SpaceMarine;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Date;

public class Collection implements Serializable {
    private ArrayDeque<SpaceMarine> spc;
    private Date dateInitialization = new Date();
    private Date dateSave = new Date();
    private Date dateChange = new Date();


    public Collection(){}

    public Date getInitializationDate() {
        return dateInitialization;
    }

    public Date getChangeDate() {
        return dateChange;
    }

    public Date getSaveDate() {
        return dateSave;
    }

    public void setObjects(ArrayDeque<SpaceMarine> spc) {
        this.spc = new ArrayDeque<>(spc);
    }

    public ArrayDeque<SpaceMarine> getObjects() {
        return spc;
    }

    public void clearCollection(){spc.clear();}

    public void uptadeDateChange() {
        this.dateChange = new Date();
    }

    public void updateInitializationDate() {
        this.dateInitialization = new Date();
    }

    public void updateSaveDate() {
        this.dateSave = new Date();
    }

    public String toString(){
        return new String("Размер: " +
                spc.size()+ "\n"+ "Дата создания: " + this.getInitializationDate() );
    }


}
