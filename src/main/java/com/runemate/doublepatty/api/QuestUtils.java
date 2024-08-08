package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.local.Quest;
import com.runemate.game.api.hybrid.local.Quests;

public class QuestUtils {
    public static Quest.Status getQuestStatus(String questName) {
        return Quests.get(questName).getStatus();
    }
}
