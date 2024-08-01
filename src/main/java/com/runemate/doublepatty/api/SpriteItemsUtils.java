package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;

public class SpriteItemsUtils {
    // Interact with two items in the inventory
    public static void useItemOn(String itemName1, String itemName2) {
        SpriteItem item1 = Inventory.getItems(itemName1).first();
        SpriteItem item2 = Inventory.getItems(itemName2).first();

        if (item1 != null && item2 != null) {
            item1.click();
            Execution.delay(600, 1200);
            item2.click();
        }
    }

}
