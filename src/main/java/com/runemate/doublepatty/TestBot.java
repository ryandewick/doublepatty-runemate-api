package com.runemate.doublepatty;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.LoopingBot;

import static com.runemate.doublepatty.api.BankUtils.*;
import static com.runemate.doublepatty.api.InteractionUtils.*;
import static com.runemate.doublepatty.api.MovementUtils.*;
import static com.runemate.doublepatty.api.SkillUtils.*;
import static com.runemate.doublepatty.api.Utility.*;

public class TestBot extends LoopingBot {
    private Player player;

    @Override
    public void onStart(String... args) {
        System.out.println("Bot started!");
    }

    @Override
    public void onStop() {
        System.out.println("Bot stopped!");
    }

    @Override
    public void onLoop() {
        if(player == null) {
            player = Players.getLocal();
        }

        if(player != null && !player.isIdle()) {
            return;
        }

        String bestAxe = getBestAvailableAxe(getWoodcuttingLevel());

        if (bestAxe == null || !Inventory.contains(bestAxe) || Inventory.isFull()) {
            bankAndWithdrawAxe();
        } else {
            chopTree();
        }
    }

    private void bankAndWithdrawAxe() {
        walkTo(Constants.DRAYNOR_BANK);
        openBank();
        String bestAxe = getBestAvailableAxe(getWoodcuttingLevel());

        if (bestAxe != null && !Inventory.contains(bestAxe)) {
            depositAll();
            delay(600, 1200);
            if (bankContains(bestAxe)) {
                withdrawItem(bestAxe, 1);
                delay(600, 1200);
            }
        } else if (bestAxe != null) {
            depositAllExcept(bestAxe);
            delay(600, 2000);
        }
    }

    private void chopTree() {
        final int PROXIMITY_RANGE = 12;
        int woodcuttingLevel = getWoodcuttingLevel();

        // Set the appropriate axe and tree area based on the woodcutting level
        if (woodcuttingLevel >= 30) {
            chopTreeInArea(Constants.WILLOW_TREE_AREA, Constants.WILLOW_TREE_NAME, PROXIMITY_RANGE);
        } else if (woodcuttingLevel >= 15) {
            chopTreeInArea(Constants.OAK_TREE_AREA, Constants.OAK_TREE_NAME, PROXIMITY_RANGE);
        } else {
            chopTreeInArea(Constants.TREE_AREA, Constants.TREE_NAME, PROXIMITY_RANGE);
        }
    }
}