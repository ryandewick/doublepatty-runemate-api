package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;

public class Constants {

    // Coordinates
    public static final Area DRAYNOR_BANK_AREA = new Area.Rectangular(new Coordinate(3090, 3245, 0), new Coordinate(3094, 3241, 0));

    public static final Coordinate DRAYNOR_BANK =  new Coordinate(3092, 3243, 0);
    public static final Area TREE_AREA = new Area.Rectangular(new Coordinate(3069, 3257, 0), new Coordinate(3073, 3253, 0));
    public static final Area OAK_TREE_AREA = new Area.Rectangular(new Coordinate(3044, 3262, 0), new Coordinate(3050, 3266, 0));
    public static final Area WILLOW_TREE_AREA = new Area.Rectangular(new Coordinate(3056, 3251, 0), new Coordinate(3064, 3256, 0));

    // Tree names
    public static final String TREE_NAME = "Tree";
    public static final String OAK_TREE_NAME = "Oak tree";
    public static final String WILLOW_TREE_NAME = "Willow tree";

    // Axe names
    public static final String BRONZE_AXE = "Bronze axe";
    public static final String STEEL_AXE = "Steel axe";
    public static final String BLACK_AXE = "Black axe";
    public static final String MITHRIL_AXE = "Mithril axe";
    public static final String ADAMANT_AXE = "Adamant axe";
    public static final String RUNE_AXE = "Rune axe";
    public static final String DRAGON_AXE = "Dragon axe";

    // Default axe
    public static String AXE_NAME = BRONZE_AXE;
}