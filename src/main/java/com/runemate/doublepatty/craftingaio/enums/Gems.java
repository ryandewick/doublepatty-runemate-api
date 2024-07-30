package com.runemate.doublepatty.craftingaio.enums;

public enum Gems {
    OPAL("Opal"),
    SAPPHIRE("Sapphire"),
    EMERALD("Emerald"),
    RUBY("Ruby"),
    DIAMOND("Diamond");

    private final String name;

    Gems(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
