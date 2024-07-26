package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;

public class InteractionUtils {
    public static void chopTreeInArea(Coordinate area, String treeName, int proximityRange) {
        Player player = Players.getLocal();
        if (!MovementUtils.isAtLocationProximity(area, proximityRange)) {
            MovementUtils.walkTo(area);
        } else {
            GameObject tree = GameObjects.newQuery().names(treeName).results().nearest();
            if (tree != null && tree.isVisible()) {
                while (!InventoryUtils.isInventoryFull() && player != null && player.isIdle()) {
                    tree.interact("Chop down");
                    if(player != null && player.isIdle()) {
                        Utility.delay(600, 5000);
                    }
                }
            } else {
                Utility.delay(600, 5000);
            }
        }
    }
}