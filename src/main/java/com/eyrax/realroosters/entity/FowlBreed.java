package com.eyrax.realroosters.entity;

import net.minecraft.util.RandomSource;

public enum FowlBreed {
    CRIOLLO_DOMINICANO("criollo_dominicano", 1.00, 1.05, 1.10, 1.00),
    RHODE_ISLAND_RED("rhode_island_red", 1.08, 0.96, 1.05, 1.08),
    LEGHORN("leghorn", 0.90, 1.15, 0.92, 1.15),
    PLYMOUTH_ROCK("plymouth_rock", 1.10, 0.92, 1.02, 1.02),
    BRAHMA("brahma", 1.22, 0.82, 1.12, 0.92),
    SILKIE("silkie", 0.92, 0.90, 0.82, 1.18),
    AYAM_CEMANI("ayam_cemani", 1.02, 1.08, 1.08, 0.96),
    SUSSEX("sussex", 1.08, 1.00, 0.98, 1.08);

    private static final FowlBreed[] VALUES = values();

    private final String serializedName;
    private final double vitalityScale;
    private final double agilityScale;
    private final double powerScale;
    private final double fertilityScale;

    FowlBreed(String serializedName, double vitalityScale, double agilityScale, double powerScale, double fertilityScale) {
        this.serializedName = serializedName;
        this.vitalityScale = vitalityScale;
        this.agilityScale = agilityScale;
        this.powerScale = powerScale;
        this.fertilityScale = fertilityScale;
    }

    public String serializedName() { return serializedName; }
    public double vitalityScale() { return vitalityScale; }
    public double agilityScale() { return agilityScale; }
    public double powerScale() { return powerScale; }
    public double fertilityScale() { return fertilityScale; }

    public static FowlBreed byId(int id) {
        return VALUES[Math.floorMod(id, VALUES.length)];
    }

    public static FowlBreed random(RandomSource random) {
        return VALUES[random.nextInt(VALUES.length)];
    }
}
