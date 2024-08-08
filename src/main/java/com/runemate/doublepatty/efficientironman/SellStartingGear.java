package com.runemate.doublepatty.efficientironman;

import com.runemate.doublepatty.api.InventoryUtils;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.runemate.ui.DefaultUI;

import static com.runemate.doublepatty.api.InteractionUtils.buyItemFromShop;
import static com.runemate.doublepatty.api.InteractionUtils.sellItemToShop;
import static com.runemate.doublepatty.api.MovementUtils.*;
import static com.runemate.doublepatty.api.SkillUtils.getTotalLevel;

public class SellStartingGear extends Task {

    private static final String SHOP_ASSISTANT = "Shop assistant";
    private static final int SHOP_INVENTORY = 300;
    String[] itemsToSell = {"Bronze dagger", "Bronze sword", "Bronze axe", "Wooden shield", "Shortbow"};

    @Override
    public boolean validate() {
        boolean hasItemsToSell = InventoryUtils.hasItemsInInventory(itemsToSell);
        boolean hasSpade = InventoryUtils.hasItemInInventory("Spade");
        int totalLevel = getTotalLevel();

        return hasItemsToSell || !hasSpade && totalLevel == 34;
    }

    @Override
    public void execute() {
        DefaultUI.setStatus("Selling starting gear...");
        Coordinate GENERAL_STORE = new Coordinate(3213, 3244, 0);

        if (!isAtLocationProximity(GENERAL_STORE, 4)) {
            walkTo(GENERAL_STORE);
            Execution.delayUntil(() -> isAtLocation(GENERAL_STORE), 600, 2000);
        }

        if (isAtLocationProximity(GENERAL_STORE, 4) && InventoryUtils.hasItemsInInventory(itemsToSell)) {
            sellItems();
        }

        if (!InventoryUtils.hasItemsInInventory(itemsToSell) && !InventoryUtils.hasItemInInventory("Spade")) {
            buySpade();
        }
    }

    private void sellItems() {
        Npc shopAssistant = Npcs.newQuery().names(SHOP_ASSISTANT).results().first();
        InterfaceComponent shopInventory = Interfaces.newQuery()
                .containers(SHOP_INVENTORY)
                .results().first();

        if (shopAssistant != null && shopAssistant.isVisible()) {
            shopAssistant.interact("Trade");
            Execution.delayUntil(() -> shopInventory != null && shopInventory.isVisible(), 600, 2000);
        }

        if (shopInventory != null && shopInventory.isVisible()) {
            for (String item : itemsToSell) {
                sellItemToShop(item, "1");
            }
        }
    }

    private void buySpade() {
        Npc shopAssistant = Npcs.newQuery().names(SHOP_ASSISTANT).results().first();
        InterfaceComponent shopInventory = Interfaces.newQuery()
                .containers(SHOP_INVENTORY)
                .results().first();

        if (shopAssistant != null && shopAssistant.isVisible() && (shopInventory == null || !shopInventory.isVisible())) {
            shopAssistant.interact("Trade");
            Execution.delayUntil(() -> shopInventory != null && shopInventory.isVisible(), 600, 2000);
        }

        if (shopInventory != null && shopInventory.isVisible()) {
            buyItemFromShop("Spade", "1");
        }
    }
}