package com.runemate.doublepatty.efficient_ironman;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Quest;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

import static com.runemate.doublepatty.api.InteractionUtils.dropItems;
import static com.runemate.doublepatty.api.InteractionUtils.pickUpItem;
import static com.runemate.doublepatty.api.InventoryUtils.hasItemInInventory;
import static com.runemate.doublepatty.api.InventoryUtils.hasItemsInInventory;
import static com.runemate.doublepatty.api.MovementUtils.isAtLocationProximity;
import static com.runemate.doublepatty.api.MovementUtils.walkTo;
import static com.runemate.doublepatty.api.QuestUtils.getQuestStatus;

public class GetRunesFromMagicTutor extends Task {
    String[] runes = {"Air rune", "Mind rune", "Water rune", "Earth rune", "Body rune"};
    private static final String NPC_NAME = "Magic combat tutor";
    private static final Coordinate MAGIC_TUTOR_LOCATION = new Coordinate(3215, 3240, 0);

    @Override
    public boolean validate() {
        if (getQuestStatus("X Marks the Spot") != Quest.Status.IN_PROGRESS) {
            return false;
        }

        boolean hasExactRunes = Inventory.getQuantity("Air rune") <= 25 &&
                Inventory.getQuantity("Mind rune") <= 25 &&
                Inventory.getQuantity("Water rune") <= 6 &&
                Inventory.getQuantity("Earth rune") <= 4 &&
                Inventory.getQuantity("Body rune") <= 2;

        return hasExactRunes;
    }

    @Override
    public void execute() {
        if (!isAtLocationProximity(MAGIC_TUTOR_LOCATION, 4)) {
            walkTo(MAGIC_TUTOR_LOCATION);
            Execution.delayUntil(() -> isAtLocationProximity(MAGIC_TUTOR_LOCATION, 4), 600, 1200);
        }

        if (isAtLocationProximity(MAGIC_TUTOR_LOCATION, 4) && hasItemsInInventory(runes)) {
            dropRunes();
        }

        if (!hasItemsInInventory(runes) && isAtLocationProximity(MAGIC_TUTOR_LOCATION, 4)) {
            claimRunesFromTutor();
        }

        if (Inventory.getQuantity("Air rune") == 30 && Inventory.getQuantity("Mind rune") == 30) {
            pickUpRunes();
        }
    }

    private void dropRunes() {
        dropItems(runes);
        Execution.delayUntil(() -> !hasItemsInInventory(runes), 600, 1800);
    }

    private void claimRunesFromTutor() {
        Npc magicTutor = Npcs.newQuery().names(NPC_NAME).results().first();
        if (magicTutor != null && magicTutor.isVisible()) {
            magicTutor.interact("Claim");
            Execution.delayUntil(() -> Inventory.getQuantity("Air rune") == 30 && Inventory.getQuantity("Mind rune") == 30, 1200, 2400);
        }
    }

    private void pickUpRunes() {
        pickUpItem("Air rune");
        Execution.delayUntil(() -> Inventory.getQuantity("Air rune") >= 55, 600, 1200);

        pickUpItem("Mind rune");
        Execution.delayUntil(() -> Inventory.getQuantity("Mind rune") >= 45, 600, 1200);

        for (String rune : runes) {
            if (!rune.equals("Air rune") && !rune.equals("Mind rune")) {
                pickUpItem(rune);
                Execution.delayUntil(() -> hasItemInInventory(rune), 600, 1200);
            }
        }
    }
}
