package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;

public class InteractionUtils {

    public static void chopTreeInArea(Coordinate area, String treeName, int proximityRange) {
        Player player = Players.getLocal();

        if (player == null) return; // Ensure player is not null

        // Move to the designated area if not within the proximity range
        if (!MovementUtils.isAtLocationProximity(area, proximityRange)) {
            MovementUtils.walkTo(area);
            Utility.delay(600, 2000); // Short delay to avoid excessive walking checks
            return;
        }

        // Check for trees in the area and interact if possible
        GameObject tree = GameObjects.newQuery().names(treeName).results().nearest();
        if (tree != null && tree.isVisible()) {
            while (!InventoryUtils.isInventoryFull() && player.isIdle()) {
                tree.interact("Chop down");
                System.out.println("Chopping tree");
                Utility.delay(600, 5000);

                // Re-check if the tree is still available and the player is idle
                tree = GameObjects.newQuery().names(treeName).results().nearest();
                if (tree == null || !tree.isVisible() || !player.isIdle()) {
                    break;
                }
            }
        } else {
            Utility.delay(600, 2000); // Shorter delay if no tree is visible
        }
    }
}