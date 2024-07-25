package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.local.Skill;

public class SkillUtils {
    public static int getWoodcuttingLevel() {
        return Skill.WOODCUTTING.getCurrentLevel();
    }
}