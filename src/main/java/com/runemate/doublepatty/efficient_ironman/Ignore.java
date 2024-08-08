package com.runemate.doublepatty.efficient_ironman;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.direct.DirectInput;
import com.runemate.game.api.hybrid.input.direct.MenuAction;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;
import com.runemate.ui.DefaultUI;

import java.util.List;

import static com.runemate.doublepatty.api.MovementUtils.isAtLocationProximity;
import static com.runemate.doublepatty.api.MovementUtils.walkTo;
import static com.runemate.doublepatty.api.Utility.delay;

public class Ignore extends Task {

    private static final String GUIDE_NAME = "Gielinor Guide";
    private static final int CONTINUE_DIALOGUE_COMPONENT_ID = 231;
    private static final int CONTINUE_DIALOGUE_COMPONENT_ID_2 = 229;
    private static final int SELECT_ANSWER_COMPONENT_ID = 219;
    private static final int SPANNER_COMPONENT_ID = 164;
    private static final int MOVING_AROUND_TEXT_CONTAINER = 263;
    private static final Coordinate STARTING_AREA = new Coordinate(3104, 3096, 0);

    @Override
    public boolean validate() {
        Player player = Players.getLocal();
        Npc guide = Npcs.newQuery().names(GUIDE_NAME).results().first();
        return player != null && guide != null && isAtLocationProximity(guide.getPosition(), 7);
    }

    @Override
    public void execute() {
        DefaultUI.setStatus("Talking to the starting guide...");
        Npc guide = Npcs.newQuery().names(GUIDE_NAME).results().first();

        if(checkForSpanner() && checkForMovingAroundText()) {
            walkTo(STARTING_AREA);
        }

        if(checkForSpanner() & !checkForMovingAroundText()) {
            System.out.println("The spanner method is getting called");
            clickSpanner();
            delay(600, 1000);
            guide.interact("Talk-to");
            delay(1000, 1400);
            continueDialogue(2, CONTINUE_DIALOGUE_COMPONENT_ID, 5);
            delay(600, 800);
        }

        if(!checkForSpanner() && guide != null) {
            System.out.println("The first method is getting called");
            guide.interact("Talk-to");
            delay(1000, 2000);
            continueDialogue(4, CONTINUE_DIALOGUE_COMPONENT_ID, 5);
            delay(600, 1200);
            continueDialogue(1, CONTINUE_DIALOGUE_COMPONENT_ID_2, 2);
            delay(1000, 1500);
            selectAnswer();
            delay(2000, 4000);
            continueDialogue(4, CONTINUE_DIALOGUE_COMPONENT_ID, 5);
        }
    }

    private void continueDialogue(int clicks, int containerId, int childIndex) {
        List<InterfaceComponent> interfaceComponents = Interfaces.newQuery()
                .containers(containerId)
                .results().asList();

        for (int i = 0; i < clicks; i++) {
            for (InterfaceComponent component : interfaceComponents) {
                if (component.getIndex() == childIndex) {
                    DirectInput.send(MenuAction.forInterfaceComponent(component, "Continue"));
                    System.out.println("Clicked continue dialogue:" + i);
                    delay(600, 1200);
                }
            }
        }
    }

    private void selectAnswer() {
        List<InterfaceComponent> interfaceComponents = Interfaces.newQuery()
                .containers(SELECT_ANSWER_COMPONENT_ID)
                .results().asList();

        for (InterfaceComponent component : interfaceComponents) {
            if ("I am an experienced player.".equals(component.getText())) {
                component.click();
                delay(1000, 2000);
            }
        }
    }

    private Boolean checkForMovingAroundText() {
        List<InterfaceComponent> interfaceComponents = Interfaces.newQuery()
                .containers(MOVING_AROUND_TEXT_CONTAINER)
                .results().asList();

        for (InterfaceComponent component : interfaceComponents) {
            if (component.getIndex() == 1) {
                if(component.isVisible()) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean checkForSpanner() {
        List<InterfaceComponent> interfaceComponents = Interfaces.newQuery()
                .containers(SPANNER_COMPONENT_ID)
                .results().asList();

        for (InterfaceComponent component : interfaceComponents) {
            if (component.getIndex() == 41 && component.isVisible()) {
                return true;
            }
        }
        return false;
    }

    private void clickSpanner() {
        List<InterfaceComponent> interfaceComponents = Interfaces.newQuery()
                .containers(SPANNER_COMPONENT_ID)
                .results().asList();

        for (InterfaceComponent component : interfaceComponents) {
            if (component.getIndex() == 41) {
                component.click();
                delay(1000, 2000);
            }
        }
    }
}