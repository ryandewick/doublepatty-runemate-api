package com.runemate.doublepatty.craftingaio.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Hides {

    GREEN_DRAGON_LEATHER("Green dragon leather", "Green dragonhide", "Needle"),
    BLUE_DRAGON_LEATHER("Blue dragon leather", "Blue dragonhide", "Needle"),
    RED_DRAGON_LEATHER("Red dragon leather", "Red dragonhide", "Needle"),
    BLACK_DRAGON_LEATHER("Black dragon leather", "Black dragonhide", "Needle");


    private final String gameName, firstItem, secondItem;

}
