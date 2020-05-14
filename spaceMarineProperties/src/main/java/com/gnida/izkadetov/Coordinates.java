package com.gnida.izkadetov;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Double x = null; //Поле не может быть null
    private Float y = null; //Поле не может быть null

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }
}