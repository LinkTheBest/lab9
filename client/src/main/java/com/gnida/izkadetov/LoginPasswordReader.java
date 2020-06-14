package com.gnida.izkadetov;

import java.util.Scanner;

public class LoginPasswordReader {
    private Scanner scn = new Scanner(System.in);
    private String login;
    private String password;

    public String getLogin() {
        System.out.println("Введите логин:");
        if (!scn.hasNextLine()) {
            System.out.println("Зря");
        } else {
            login = scn.nextLine();
        }
        return login;
    }

    public String getPassword() {
        System.out.println("Введите пароль:");
        if (!scn.hasNextLine()) {
            System.out.println("Зря");
        } else {
            password = scn.nextLine();
        }
        return password;
    }
}
