package com.runemate.doublepatty.craftingaio.enums;

public enum Battlestaffs {
    AIR_BATTLESTAFF("Air Battlestaff"),
    WATER_BATTLESTAFF("Water Battlestaff"),
    EARTH_BATTLESTAFF("Earth Battlestaff"),
    FIRE_BATTLESTAFF("Fire Battlestaff");

    private final String name;

    Battlestaffs(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
