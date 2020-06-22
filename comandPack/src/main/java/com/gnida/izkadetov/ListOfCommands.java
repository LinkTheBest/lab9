package com.gnida.izkadetov;

import java.io.Serializable;

public enum ListOfCommands implements Serializable {
    HELP("help"),
    INFO("info"),
    ADD("add"),
    SHOW("show"),
    GET_USER_ID("get_user_id"),
    REMOVE_BY_ID("remove_by_id"),
    CLEAR("clear"),
    EXECUTE_SCRIPT("execute_script"),
    ADD_IF_MAX("add_if_max"),
    ADD_IF_MIN("add_if_min"),
    REGISTRATION("registration"),
    LOGIN("login"),
    REMOVE_LOWER("remove_lower"),
    SUM_OF_HEALTH("sum_of_health"),
    PRINT_DESCENDING("print_descending"),
    PRINT_DESCENDING_HEALTH("print_descending_health"),
    UPDATE("update"),
    SAVE("save"),
    LOGOUT("logout"),
    EXIT("exit");

    private ListOfCommands(String textVariant) {
        this.textVariant = textVariant;
    }

    private String textVariant;

}
