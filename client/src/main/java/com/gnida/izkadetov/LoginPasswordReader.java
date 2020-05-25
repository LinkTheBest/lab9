package com.gnida.izkadetov;

import java.util.Scanner;

public class LoginPasswordReader {
    private Scanner scn = new Scanner(System.in);
    private String login;
    private String password;

    public void readLogin() {
        if (!scn.hasNextLine()) {
            System.out.println("Зря");
        } else {
            login = scn.nextLine();
        }
    }

    public void readPassword() {
        if (!scn.hasNextLine()) {
            System.out.println("Зря");
        } else {
            password = scn.nextLine();
        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
