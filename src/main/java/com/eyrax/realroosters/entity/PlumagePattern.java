package com.eyrax.realroosters.entity;

import net.minecraft.util.RandomSource;

public enum PlumagePattern {
    SOLID("solid"),
    PINTO("pinto"),
    MANILO("manilo"),
    GIRO("giro"),
    CENIZO("cenizo"),
    JABAO("jabao");

    private static final PlumagePattern[] VALUES = values();
    private final String serializedName;

    PlumagePattern(String serializedName) {
        this.serializedName = serializedName;
    }

    public String serializedName() { return serializedName; }

    public static PlumagePattern byId(int id) {
        return VALUES[Math.floorMod(id, VALUES.length)];
    }

    public static PlumagePattern random(RandomSource random) {
        return VALUES[random.nextInt(VALUES.length)];
    }
}
