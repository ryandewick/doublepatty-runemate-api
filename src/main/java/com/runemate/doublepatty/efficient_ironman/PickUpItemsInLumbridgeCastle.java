package com.runemate.doublepatty.efficient_ironman;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Quest;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

import static com.runemate.doublepatty.api.InteractionUtils.pickUpItem;
import static com.runemate.doublepatty.api.InventoryUtils.hasItemInInventory;
import static com.runemate.doublepatty.api.InventoryUtils.hasItemsInInventory;
import static com.runemate.doublepatty.api.MovementUtils.isAtLocationProximity;
import static com.runemate.doublepatty.api.MovementUtils.walkTo;
import static com.runemate.doublepatty.api.QuestUtils.getQuestStatus;
import static com.runemate.doublepatty.api.Utility.delay;

public class PickUpItemsInLumbridgeCastle extends Task {
    private final String[] inventory = {"Spade", "Bronze pickaxe", "Tinderbox", "Small fishing net", "Shrimps", "Treasure scroll", "Bucket", "Pot", "Bread"};
    private final String[] itemsToPickUp = {"Jug of water", "Cabbage", "Knife", "Bowl"};

    private final Coordinate LUMBRIDGE_KITCHEN = new Coordinate(3209, 3213, 0);
    private final Coordinate BASEMENT = new Coordinate(3216, 9623, 0);

    @Override
    public boolean validate() {
        return (hasItemsInInventory(inventory) && getQuestStatus("X Marks the Spot") == Quest.Status.IN_PROGRESS) || !hasItemsInInventory(itemsToPickUp);
    }

    @Override
    public void execute() {
        if (!isAtLocationProximity(LUMBRIDGE_KITCHEN, 5)) {
            walkTo(LUMBRIDGE_KITCHEN);
            Execution.delayUntil(() -> isAtLocationProximity(LUMBRIDGE_KITCHEN, 5), 600, 1200);
        }

        if (isAtLocationProximity(LUMBRIDGE_KITCHEN, 2)) {
            pickUpItemIfNotInInventory("Jug");
            pickUpItemIfNotInInventory("Bowl");
        }

        if (hasItemInInventory("Jug") && hasItemInInventory("Bowl") && !isAtLocationProximity(BASEMENT, 2)) {
            walkTo(BASEMENT);
            Execution.delayUntil(() -> isAtLocationProximity(BASEMENT, 2), 600, 1200);
        }

        if (isAtLocationProximity(BASEMENT, 6)) {
            pickUpItemIfNotInInventory("Cabbage");
            pickUpItemIfNotInInventory("Knife");
        }

        if (hasItemInInventory("Cabbage") && hasItemInInventory("Knife") && isAtLocationProximity(BASEMENT, 3)) {
            useJugOnSink();
        }
    }

    private void pickUpItemIfNotInInventory(String itemName) {
        if (!hasItemInInventory(itemName)) {
            pickUpItem(itemName);
            Execution.delayUntil(() -> hasItemInInventory(itemName), 600, 1800);
        }
    }

    private void useJugOnSink() {
        SpriteItem jug = Inventory.newQuery().names("Jug").results().first();
        GameObject sink = GameObjects.newQuery().names("Sink").results().first();
        if (jug != null && sink != null) {
            jug.interact("Use");
            delay(600, 1200);
            sink.interact("Use");
            delay(2000, 3000);}
    }
}
