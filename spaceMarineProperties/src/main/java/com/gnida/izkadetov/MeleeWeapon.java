package com.gnida.izkadetov;

import java.io.Serializable;

public enum MeleeWeapon implements Serializable {
    CHAIN_SWORD("chainSword"),
    MANREAPER("manreaper"),
    POWER_BLADE("powerBlade");

    private MeleeWeapon(String name) {
        meleeWeaponName = name;
    }

    public String getMeleeWeaponName() {
        return meleeWeaponName;
    }

    private String meleeWeaponName;
}