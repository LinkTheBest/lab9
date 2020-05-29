package com.gnida.izkadetov;

import java.io.Serializable;

public enum Weapon implements Serializable {
    MELTAGUN("melagun"),
    BOLT_PISTOL("boltPistol"),
    COMBI_FLAMER("combiFlamer"),
    COMBI_PLASMA_GUN("combiPlasmaGun"),
    MISSILE_LAUNCHER("missileLauncher");

    private Weapon(String name) {
        weaponName = name;
    }

    private String weaponName;

    public String getWeaponName() {
        return weaponName;
    }
}