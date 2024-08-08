package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;

public class InventoryUtils {
    public static boolean hasItemInInventory(String itemName) {
        return Inventory.contains(itemName);
    }

    public static boolean hasItemsInInventory(String[] itemNames) {
        for (String itemName : itemNames) {
            if (!Inventory.contains(itemName)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isInventoryFull() {
        return Inventory.isFull();
    }

    public static int getInventoryCount(String itemName) {
        return Inventory.getItems(itemName).size();
    }
}