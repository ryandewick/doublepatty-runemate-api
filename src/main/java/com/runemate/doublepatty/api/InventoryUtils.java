package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;

public class InventoryUtils {
    public static boolean hasItemInInventory(String itemName) {
        return Inventory.contains(itemName);
    }

    public static boolean isInventoryFull() {
        return Inventory.isFull();
    }

    public static int getInventoryCount(String itemName) {
        return Inventory.getItems(itemName).size();
    }
}