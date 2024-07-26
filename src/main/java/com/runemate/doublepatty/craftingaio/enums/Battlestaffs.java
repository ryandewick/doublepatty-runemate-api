package com.runemate.doublepatty.craftingaio.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Battlestaffs {

    AIR_BATTLESTAFF("Air battlestaff", "Battlestaff", "Air orb"),
    WATER_BATTLESTAFF("Water battlestaff", "Battlestaff", "Water orb"),
    EARTH_BATTLESTAFF("Earth battlestaff", "Battlestaff", "Earth orb"),
    FIRE_BATTLESTAFF("Fire battlestaff", "Battlestaff", "Fire orb");


    private final String gameName, firstItem, secondItem;
}
