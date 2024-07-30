package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;

public class InteractionUtils {
    static BotGUI gui = BotGUI.getInstance();

    public static void chopTreeInArea(Area area, String treeName, int proximityRange) {
        Player player = Players.getLocal();

        if (!MovementUtils.isAtLocationProximity(area, proximityRange)) {
            MovementUtils.walkTo(area);
            Execution.delayUntil(() -> MovementUtils.isAtLocationProximity(area, proximityRange), 2000, 4000);
        }

        gui.setAction("Looking for " + treeName);

        GameObject tree = GameObjects.newQuery().names(treeName).results().nearest();
        if (tree != null && tree.isVisible()) {
            while (!InventoryUtils.isInventoryFull() && player.isIdle()) {
                gui.setAction("Chopping " + treeName + "...");
                tree.interact("Chop down");
                Utility.delay(600, 5000);
            }
        } else {
            gui.setAction("No trees found, idling...");
            Utility.delay(600, 2000); // Shorter delay if no tree is visible
        }
    }
}
