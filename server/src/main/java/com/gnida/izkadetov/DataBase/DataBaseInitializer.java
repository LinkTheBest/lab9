package com.gnida.izkadetov.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseInitializer {
    private Connection connection;

    public boolean ifDataBaseConnected(String host, int port, String baseName, String user, String pwd) {
        String databaseUrl = "jdbc:postgresql://" + host + ":" + port + "/" + baseName;
        try {
            connection = DriverManager.getConnection(databaseUrl, user, pwd);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean ifTablesCreated() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("create table  if not exists users(id serial primary key not null, username text unique, password character varying)");
            statement.execute("create table if not exists spaceMarines(spcid int, spcname text, x double precision, y float, health int, category text, weaponType text, meleeWeapon text, chapter text, userId int, foreign key(userId) references users(id)on delete cascade)");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
