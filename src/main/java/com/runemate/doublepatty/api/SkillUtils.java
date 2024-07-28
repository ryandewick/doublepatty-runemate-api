package com.runemate.doublepatty.api;

import com.runemate.doublepatty.Constants;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;

public class SkillUtils {
    private static final String[] axePriority = new String[] {
            Constants.DRAGON_AXE,
            Constants.RUNE_AXE,
            Constants.ADAMANT_AXE,
            Constants.MITHRIL_AXE,
            Constants.STEEL_AXE,
            Constants.BRONZE_AXE
    };

    public static int getWoodcuttingLevel() {
        return Skill.WOODCUTTING.getCurrentLevel();
    }

    public static String getBestAvailableAxe(int woodcuttingLevel) {
        for (String axe : axePriority) {
            if (isAxeUsable(axe, woodcuttingLevel) && (Inventory.contains(axe) || BankUtils.bankContains(axe))) {
                return axe;
            }
        }
        return null;
    }

    // Check if the axe can be used by the player based on their level
    private static boolean isAxeUsable(String axe, int woodcuttingLevel) {
        return switch (axe) {
            case Constants.DRAGON_AXE -> woodcuttingLevel >= 61;
            case Constants.RUNE_AXE -> woodcuttingLevel >= 41;
            case Constants.ADAMANT_AXE -> woodcuttingLevel >= 31;
            case Constants.MITHRIL_AXE -> woodcuttingLevel >= 21;
            case Constants.STEEL_AXE -> woodcuttingLevel >= 6;
            case Constants.BRONZE_AXE -> woodcuttingLevel >= 1;
            default -> false;
        };
    }
}