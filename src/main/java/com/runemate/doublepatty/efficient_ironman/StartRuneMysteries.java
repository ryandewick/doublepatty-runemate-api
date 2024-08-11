package com.runemate.doublepatty.efficient_ironman;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Quest;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

import java.awt.event.KeyEvent;

import static com.runemate.doublepatty.api.InteractionUtils.continueDialogue;
import static com.runemate.doublepatty.api.InteractionUtils.selectAnswer;
import static com.runemate.doublepatty.api.MovementUtils.isAtLocationProximity;
import static com.runemate.doublepatty.api.MovementUtils.walkTo;
import static com.runemate.doublepatty.api.QuestUtils.getQuestStatus;
import static com.runemate.doublepatty.api.Utility.delay;

public class StartRuneMysteries extends Task {
    private static final String QUEST_NAME = "Rune Mysteries";
    private static final String QUEST_STARTER = "Duke Horacio";
    private static final Coordinate QUEST_STARTER_LOCATION = new Coordinate(3210, 3223, 1);

    @Override
    public boolean validate() {
        return getQuestStatus("X Marks the Spot") == Quest.Status.IN_PROGRESS && getQuestStatus(QUEST_NAME) == Quest.Status.NOT_STARTED;
    }

    @Override
    public void execute() {
        if(!isAtLocationProximity(QUEST_STARTER_LOCATION,4)) {
            walkTo(QUEST_STARTER_LOCATION);
            Execution.delayUntil(() -> isAtLocationProximity(QUEST_STARTER_LOCATION, 4), 600, 1200);
        }
        if(isAtLocationProximity(QUEST_STARTER_LOCATION, 4)) {
            Npc dukeHoracio = Npcs.newQuery().names(QUEST_STARTER).results().first();
            if(dukeHoracio != null && dukeHoracio.isVisible()) {
                dukeHoracio.interact("Talk-to");
                Execution.delayUntil(() -> Interfaces.newQuery().containers(231).results().first() != null, 600, 2000);
            }
        }

        handleDialogues();
    }

    private void handleDialogues() {
        continueDialogue();
        selectAnswer("Have you any quests for me?");
        continueDialogue();
        continueDialogue();
        continueDialogue();
        continueDialogue();
        Keyboard.pressKey(KeyEvent.VK_SPACE);
        Keyboard.releaseKey(KeyEvent.VK_SPACE);
        delay(1000, 2000);
        continueDialogue();
        continueDialogue();
        selectAnswer("Yes.");
        continueDialogue();
    }
}
