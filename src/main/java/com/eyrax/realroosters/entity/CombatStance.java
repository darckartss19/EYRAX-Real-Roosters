package com.eyrax.realroosters.entity;

public enum CombatStance {
    PASSIVE("passive"),
    GUARD("guard"),
    DUEL("duel");

    private static final CombatStance[] VALUES = values();
    private final String serializedName;

    CombatStance(String serializedName) {
        this.serializedName = serializedName;
    }

    public String serializedName() { return serializedName; }

    public CombatStance next() {
        return VALUES[(ordinal() + 1) % VALUES.length];
    }

    public static CombatStance byId(int id) {
        return VALUES[Math.floorMod(id, VALUES.length)];
    }
}
