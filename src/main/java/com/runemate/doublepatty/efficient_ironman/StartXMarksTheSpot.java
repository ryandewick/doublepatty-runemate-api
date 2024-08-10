package com.runemate.doublepatty.efficient_ironman;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Quest;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;
import com.runemate.ui.DefaultUI;
import lombok.extern.log4j.Log4j2;

import static com.runemate.doublepatty.api.InteractionUtils.continueDialogue;
import static com.runemate.doublepatty.api.InteractionUtils.selectAnswer;
import static com.runemate.doublepatty.api.MovementUtils.isAtLocationProximity;
import static com.runemate.doublepatty.api.MovementUtils.walkTo;
import static com.runemate.doublepatty.api.QuestUtils.getQuestStatus;

@Log4j2
public class StartXMarksTheSpot extends Task {

    private static final String X_MARKS_THE_SPOT = "X Marks the Spot";
    private static final String NPC_NAME = "Veos";
    private static final Coordinate X_MARKS_THE_SPOT_NPC_LOCATION = new Coordinate(3228, 3241, 0);

    @Override
    public boolean validate() {
        return getQuestStatus(X_MARKS_THE_SPOT) == Quest.Status.NOT_STARTED;
    }

    @Override
    public void execute() {
        if (!isAtLocationProximity(X_MARKS_THE_SPOT_NPC_LOCATION, 4)) {
            DefaultUI.setStatus("Starting X Marks the Spot...");
            walkTo(X_MARKS_THE_SPOT_NPC_LOCATION);
            Execution.delayUntil(() -> isAtLocationProximity(X_MARKS_THE_SPOT_NPC_LOCATION, 4), 600, 1200);
        }

        if (isAtLocationProximity(X_MARKS_THE_SPOT_NPC_LOCATION, 7)) {
            Npc veos = Npcs.newQuery().names(NPC_NAME).results().first();
            if (veos != null && veos.isVisible()) {
                veos.interact("Talk-to");
                Execution.delayUntil(() -> Interfaces.newQuery().containers(231).results().first() != null, 600, 2000);
            }
        }

        handleDialogues();
    }

    private void handleDialogues() {
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
