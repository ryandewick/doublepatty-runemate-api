package com.runemate.doublepatty.craftingaio.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gems {

    OPAL("Opal", "Uncut opal", "Chisel"),
    JADE("Jade", "Uncut jade", "Chisel"),
    RED_TOPAZ("Red topaz", "Uncut red topaz", "Chisel"),
    SAPPHIRE("Sapphire", "Uncut sapphire", "Chisel"),
    EMERALD("Emerald", "Uncut emerald", "Chisel"),
    RUBY("Ruby", "Uncut ruby", "Chisel"),
    DIAMOND("Diamond", "Uncut diamond", "Chisel"),
    DRAGONSTONE("Dragonstone", "Uncut dragonstone", "Chisel"),
    ONYX("Onyx", "Uncut onyx", "Chisel");


    private final String gameName, firstItem, secondItem;
}
