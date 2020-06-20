package com.gnida.izkadetov;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class StartUpObjectLoader {
    private Connection connection;
    private ArrayDeque<SpaceMarine> spaceDeque = new ArrayDeque<>();

    public StartUpObjectLoader(Connection connection) {
        this.connection = connection;
    }

    public ArrayDeque<SpaceMarine> loader() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from spacemarines");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SpaceMarine spaceMarine = new SpaceMarine();
                spaceMarine.setId(resultSet.getInt("spcid"));
                spaceMarine.setName(resultSet.getString("spcname"));
                spaceMarine.setXCoordinate(resultSet.getDouble("x"));
                spaceMarine.setYCoordinate(resultSet.getFloat("y"));
                spaceMarine.setHealth(resultSet.getInt("health"));
                spaceMarine.setCategory(resultSet.getString("category"));
                spaceMarine.setWeaponType(resultSet.getString("weapontype"));
                spaceMarine.setMeleeWeapon(resultSet.getString("meleeweapon"));
                spaceMarine.setChapter(resultSet.getString("chapter"));
                spaceMarine.setUserId(resultSet.getInt("userid"));
                spaceDeque.add(spaceMarine);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return spaceDeque;
    }

    public ArrayDeque<SpaceMarine> getSpaceDeque() {
        return spaceDeque;
    }
}