package com.runemate.doublepatty.efficient_ironman;

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
    private final String[] itemsToSell = {"Bronze dagger", "Bronze sword", "Bronze axe", "Wooden shield", "Shortbow"};
    private final Coordinate GENERAL_STORE = new Coordinate(3213, 3244, 0);

    @Override
    public boolean validate() {
        boolean hasItemsToSell = InventoryUtils.hasItemsInInventory(itemsToSell);
        boolean hasSpade = InventoryUtils.hasItemInInventory("Spade");
        int totalLevel = getTotalLevel();

        return hasItemsToSell || (!hasSpade && totalLevel == 34);
    }

    @Override
    public void execute() {
        DefaultUI.setStatus("Selling starting gear...");

        if (!isAtLocationProximity(GENERAL_STORE, 7)) {
            walkTo(GENERAL_STORE);
            Execution.delayUntil(() -> isAtLocationProximity(GENERAL_STORE, 7), 600, 2000);
        }

        if (isAtLocationProximity(GENERAL_STORE, 7)) {
            if (InventoryUtils.hasItemsInInventory(itemsToSell)) {
                sellItems();
            } else if (!InventoryUtils.hasItemInInventory("Spade")) {
                buySpade();
            }
        }
    }

    private void sellItems() {
        Npc shopAssistant = Npcs.newQuery().names(SHOP_ASSISTANT).results().first();
        if (shopAssistant != null && shopAssistant.isVisible()) {
            shopAssistant.interact("Trade");
            Execution.delayUntil(() -> Interfaces.newQuery().containers(SHOP_INVENTORY).results().first() != null, 600, 2000);
        }

        InterfaceComponent shopInventory = Interfaces.newQuery().containers(SHOP_INVENTORY).results().first();
        if (shopInventory != null && shopInventory.isVisible()) {
            for (String item : itemsToSell) {
                sellItemToShop(item, "1");
                Execution.delay(200, 600);
            }
        }
    }

    private void buySpade() {
        Npc shopAssistant = Npcs.newQuery().names(SHOP_ASSISTANT).results().first();
        if (shopAssistant != null && shopAssistant.isVisible()) {
            shopAssistant.interact("Trade");
            Execution.delayUntil(() -> Interfaces.newQuery().containers(SHOP_INVENTORY).results().first() != null, 600, 2000);
        }

        InterfaceComponent shopInventory = Interfaces.newQuery().containers(SHOP_INVENTORY).results().first();
        if (shopInventory != null && shopInventory.isVisible()) {
            buyItemFromShop("Spade", "1");
            Execution.delay(200, 600);
        }
    }
}
