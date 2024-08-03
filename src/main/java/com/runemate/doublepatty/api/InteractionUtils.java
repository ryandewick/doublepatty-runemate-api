package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.MakeAllInterface;
import com.runemate.game.api.script.Execution;
import com.runemate.ui.DefaultUI;

import static com.runemate.doublepatty.api.Utility.delay;

public class InteractionUtils {
    static Player player = Players.getLocal();

    public static void chopTreeInArea(Area area, String treeName, int proximityRange) {
        Player player = Players.getLocal();

        if (!MovementUtils.isAtLocationProximity(area, proximityRange)) {
            MovementUtils.walkTo(area);
            Execution.delayUntil(() -> MovementUtils.isAtLocationProximity(area, proximityRange), 2000, 4000);
            return;
        }

        DefaultUI.setStatus("Looking for " + treeName);

        GameObject tree = GameObjects.newQuery().names(treeName).results().nearest();
        if (tree != null && tree.isVisible()) {
            while (!InventoryUtils.isInventoryFull() && player.isIdle()) {
                DefaultUI.setStatus("Chopping " + treeName);
                tree.interact("Chop down");
                delay(600, 5000);
            }
        } else {
            DefaultUI.setStatus("No trees found, idling...");
            delay(600, 2000);
        }
    }
    public static void useItemOn(String itemName1, String itemName2) {
        SpriteItem item1 = Inventory.getItems(itemName1).first();
        SpriteItem item2 = Inventory.getItems(itemName2).first();
        Execution.delay(1000, 2000);

        if (item1 != null && item2 != null && player.getAnimationId() == -1) {
            item1.click();
            Execution.delay(1000, 1400);
            item2.click();
            Execution.delayUntil(MakeAllInterface::isOpen, 600, 1200);
        }
    }
    public static void makeAll(String action) {
        if (MakeAllInterface.isOpen()) {
            if (MakeAllInterface.getSelectedQuantity() != 0) {
                DefaultUI.setStatus("Setting quantity to ALL");
                MakeAllInterface.setSelectedQuantity(0);
                Execution.delay(600, 1200);
            }
            DefaultUI.setStatus("Making All");
            InterfaceComponent makeAllButton = Interfaces.newQuery().containers(270).actions(action).results().first();
            if (makeAllButton != null) {
                makeAllButton.click();
                Execution.delayUntil(() -> player.getAnimationId() == -1, 600, 1200);}
        }
    }
}
