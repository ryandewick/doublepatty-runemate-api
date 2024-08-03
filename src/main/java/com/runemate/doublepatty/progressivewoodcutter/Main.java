package com.runemate.doublepatty.progressivewoodcutter;

import com.runemate.doublepatty.api.Constants;
import com.runemate.doublepatty.api.Utility;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.LoopingBot;
import com.runemate.pathfinder.Pathfinder;
import com.runemate.ui.DefaultUI;

import static com.runemate.doublepatty.api.BankUtils.*;
import static com.runemate.doublepatty.api.InteractionUtils.chopTreeInArea;
import static com.runemate.doublepatty.api.MovementUtils.init;
import static com.runemate.doublepatty.api.MovementUtils.walkTo;
import static com.runemate.doublepatty.api.SkillUtils.getBestAvailableAxe;
import static com.runemate.doublepatty.api.SkillUtils.getWoodcuttingLevel;
import static com.runemate.doublepatty.api.Utility.delay;

public class Main extends LoopingBot {
    private Player player;
    private long lastBreakTime = System.currentTimeMillis();
//    private static int MAX_PLAY_TIME = Utility.random(300000, 3600000);
    private static int MAX_PLAY_TIME = Utility.random(60000, 800000);
    private static final int MIN_BREAK_TIME = 60000;
    private static final int MAX_BREAK_TIME = 500000;
    private Pathfinder pathfinder;



    @Override
    public void onStart(String... args) {
        DefaultUI.setStatus("Playtime duration: " + MAX_PLAY_TIME / 60000 + " minutes.");
        init(this);
    }

    @Override
    public void onStop() {
        DefaultUI.setStatus("Stopping bot.");
    }

    @Override
    public void onLoop() {
        player = Players.getLocal();

        if (player == null || !player.isIdle()) {
            return;
        }
        // Handle breaks
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBreakTime > MAX_PLAY_TIME) {
            takeBreak();
            return;
        }

        performMainTask();
    }

    private void takeBreak() {
        int breakDuration = Utility.random(MIN_BREAK_TIME, MAX_BREAK_TIME);
//        gui.setAction("Taking a break for " + breakDuration / 60000 + " minutes.");
        DefaultUI.setStatus("Taking a break for " + breakDuration / 60000 + " minutes.");
        delay(breakDuration);
//        gui.setAction("Break over. Resuming bot.");
        DefaultUI.setStatus("Break over. Resuming bot.");
        lastBreakTime = System.currentTimeMillis();
        MAX_PLAY_TIME = Utility.random(60000, 420000);
        System.out.println("Will now play for: " + MAX_PLAY_TIME / 60000 + " minutes.");
    }

    private void performMainTask() {
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
            delay(Utility.random(600, 1200));
            if (bankContains(bestAxe)) {
                withdrawItem(bestAxe, 1);
                delay(Utility.random(600, 1200));
            }
        } else if (bestAxe != null) {
            depositAllExcept(bestAxe);
            delay(Utility.random(600, 2000));
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