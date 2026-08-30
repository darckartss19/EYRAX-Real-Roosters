package com.eyrax.realroosters.entity;

public enum FowlSex {
    HEN("hen"),
    ROOSTER("rooster");

    private final String serializedName;

    FowlSex(String serializedName) {
        this.serializedName = serializedName;
    }

    public String serializedName() {
        return serializedName;
    }

    public static FowlSex byId(int id) {
        return id == ROOSTER.ordinal() ? ROOSTER : HEN;
    }
}
