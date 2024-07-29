package com.runemate.doublepatty.craftingaio.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CraftHides {
    GREEN_DRAGON_BODY("Green dragon body", "Green dragon leather", "Needle"),
    GREEN_DRAGON_CHAPS("Green dragon chaps", "Green dragon leather", "Needle"),
    GREEN_DRAGON_VAMBRACES("Green dragon vambraces", "Green dragon leather", "Needle"),
    BLUE_DRAGON_BODY("Blue dragon body", "Blue dragon leather", "Needle"),
    BLUE_DRAGON_CHAPS("Blue dragon chaps", "Blue dragon leather", "Needle"),
    BLUE_DRAGON_VAMBRACES("Blue dragon vambraces", "Blue dragon leather", "Needle"),
    RED_DRAGON_BODY("Red dragon body", "Red dragon leather", "Needle"),
    RED_DRAGON_CHAPS("Red dragon chaps", "Red dragon leather", "Needle"),
    RED_DRAGON_VAMBRACES("Red dragon vambraces", "Red dragon leather", "Needle"),
    BLACK_DRAGON_BODY("Black dragon body", "Black dragon leather", "Needle"),
    BLACK_DRAGON_CHAPS("Black dragon chaps", "Black dragon leather", "Needle"),
    BLACK_DRAGON_VAMBRACES("Black dragon vambraces", "Black dragon leather", "Needle");

    private final String gameName, firstItem, secondItem;
}
