package com.runemate.doublepatty.efficient_ironman;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Quest;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.runemate.ui.DefaultUI;

import static com.runemate.doublepatty.api.InteractionUtils.continueDialogue;
import static com.runemate.doublepatty.api.InteractionUtils.selectAnswer;
import static com.runemate.doublepatty.api.MovementUtils.isAtLocationProximity;
import static com.runemate.doublepatty.api.MovementUtils.walkTo;
import static com.runemate.doublepatty.api.QuestUtils.getQuestStatus;
import static com.runemate.doublepatty.api.Utility.delay;

public class StartXMarksTheSpot extends Task {
    private static final String X_MARKS_THE_SPOT = "X Marks the Spot";
    private static final String NPC_NAME = "Veos";
    private static final Coordinate X_MARKS_THE_SPOT_NPC_LOCATION = new Coordinate(3228, 3240, 0);

    @Override
    public boolean validate() {
        return getQuestStatus(X_MARKS_THE_SPOT) == Quest.Status.NOT_STARTED;
    }

    @Override
    public void execute() {
        System.out.println("Starting X Marks the Spot...");

        if (getQuestStatus(X_MARKS_THE_SPOT) == Quest.Status.NOT_STARTED) {
            startQuest();
        }
    }

    private void startQuest() {
        DefaultUI.setStatus("Starting X Marks the Spot...");
        walkTo(X_MARKS_THE_SPOT_NPC_LOCATION);
        Execution.delayUntil(() -> isAtLocationProximity(X_MARKS_THE_SPOT_NPC_LOCATION, 4), 600, 1200);

        if (isAtLocationProximity(X_MARKS_THE_SPOT_NPC_LOCATION, 7)) {
            Npc veos = Npcs.newQuery().names(NPC_NAME).results().first();
            if (veos != null && veos.isVisible()) {
                System.out.println("Interacting with Veos...");
                DefaultUI.setStatus("Interacting with Veos...");
                veos.interact("Talk-to");
                Execution.delayUntil(() -> Interfaces.newQuery().containers(231).results().first() != null, 600, 2000);
                delay(1000, 2000);
            }
        }

        // Handle dialogues in specified order
        continueDialogue(231, 5);
        selectAnswer(219, "I'm looking for a quest.");
        continueDialogue(217, 5);
        continueDialogue(231, 5);
        continueDialogue(217, 5);
        continueDialogue(231, 5);
        continueDialogue(217, 5);
        continueDialogue(231, 5);
        continueDialogue(231, 5);
        selectAnswer(219, 1);
        continueDialogue(217, 5);
        continueDialogue(231, 5);
    }
}