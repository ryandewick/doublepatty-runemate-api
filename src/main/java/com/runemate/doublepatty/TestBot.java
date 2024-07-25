package com.runemate.doublepatty;
import static com.runemate.doublepatty.api.Utility.*;
import static com.runemate.doublepatty.api.InventoryUtils.*;
import static com.runemate.doublepatty.api.LoggerUtils.*;
import static com.runemate.doublepatty.api.SkillUtils.*;
import static com.runemate.doublepatty.api.BankUtils.*;
import static com.runemate.doublepatty.api.MovementUtils.*;
import static com.runemate.doublepatty.api.InteractionUtils.*;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.LoopingBot;

public class TestBot extends LoopingBot {

    private final Coordinate DRAYNOR_BANK = new Coordinate(3092, 3243, 0);
    private final Coordinate TREE_AREA = new Coordinate(3080, 3269, 0);
    private final Coordinate OAK_TREE_AREA = new Coordinate(3048, 3267, 0);
    private final Coordinate WILLOW_TREE_AREA = new Coordinate(3060, 3253, 0);
    private final String TREE_NAME = "Tree";
    private final String OAK_TREE_NAME = "Oak tree";
    private final String WILLOW_TREE_NAME = "Willow tree";

    private final String AXE_NAME = "Bronze axe";

    private final String BRONZE_AXE = "Bronze axe";
    private final String STEEL_AXE = "Steel axe";
    private final String BLACK_AXE = "Black axe";
    private final String MITHRIL_AXE = "Mithril axe";
    private final String ADAMANT_AXE = "Adamant axe";
    private final String RUNE_AXE = "Rune axe";
    private final String DRAGON_AXE = "Dragon axe";

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

        if(!player.isIdle()) {
            return;
        }

        if (player.isMoving()) {
            return;
        }

        if (!Inventory.contains(AXE_NAME) || Inventory.isFull()) {
            bankAndWithdrawAxe();
        } else {
            chopTree();
        }
    }

    private void bankAndWithdrawAxe() {
        walkTo(DRAYNOR_BANK);
        openBank();
        depositAllExcept(AXE_NAME);
        delay(1000,3000);
    }

    private void chopTree() {
        final int PROXIMITY_RANGE = 8;
        int woodcuttingLevel = getWoodcuttingLevel();

        if (woodcuttingLevel < 15) {
            chopTreeInArea(TREE_AREA, TREE_NAME, PROXIMITY_RANGE);
        } else if (woodcuttingLevel < 30) {
            chopTreeInArea(OAK_TREE_AREA, OAK_TREE_NAME, PROXIMITY_RANGE);
        } else {
            chopTreeInArea(WILLOW_TREE_AREA, WILLOW_TREE_NAME, PROXIMITY_RANGE);
        }
    }
}