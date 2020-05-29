package com.gnida.izkadetov;

import java.io.Serializable;

public enum AstartesCategory implements Serializable {
    AGGRESSOR("agressor"),
    INCEPTOR("inceptor"),
    TACTICAL("tactical"),
    TERMINATOR("terminator");

    private AstartesCategory(String name){
        categoryName = name;
    }
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }
}