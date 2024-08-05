package com.runemate.doublepatty.tutisland;

import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.framework.task.Task;
import com.runemate.ui.DefaultUI;

import java.util.List;
import java.util.Random;

import static com.runemate.doublepatty.api.Utility.delay;

public class RandomiseChar extends Task {

    private static final int CHARACTER_CUSTOMISATION_INTERFACE_ID = 679;
    private static final int[] WIDGET_COMPONENT_IDS = {13, 17, 21, 24, 33, 43, 48, 52, 60};
    private static final int CONFIRM_BUTTON_COMPONENT_ID = 68;
    private static final int FEMALE_SELECTION_COMPONENT_ID = 66;
    private boolean randomiseComplete = false;

    private Random random = new Random();

    @Override
    public boolean validate() {
        InterfaceComponent customisationInterface = Interfaces.newQuery().containers(CHARACTER_CUSTOMISATION_INTERFACE_ID).results().first();
        return customisationInterface != null && customisationInterface.isVisible();
    }

    @Override
    public void execute() {
        DefaultUI.setStatus("Randomising character...");
        List<InterfaceComponent> interfaceComponents = Interfaces.newQuery()
                .containers(CHARACTER_CUSTOMISATION_INTERFACE_ID)
                .results().asList();

        InterfaceComponent femaleSelectionComponent = null;
        for (InterfaceComponent component : interfaceComponents) {
            if (component.getIndex() == FEMALE_SELECTION_COMPONENT_ID) {
                femaleSelectionComponent = component;
                break;
            }
        }

        if (femaleSelectionComponent != null && femaleSelectionComponent.isVisible()) {
            femaleSelectionComponent.click();
            delay(1000, 2000);
        }

        for (int componentId : WIDGET_COMPONENT_IDS) {
            InterfaceComponent widget = null;
            for (InterfaceComponent component : interfaceComponents) {
                if (component.getIndex() == componentId) {
                    widget = component;
                    break;
                }
            }

            if (widget != null && widget.isVisible()) {
                int randomClicks = random.nextInt(7) + 1;
                for (int i = 0; i < randomClicks; i++) {
                    widget.click();
                    delay(200, 400);
                }
            }
        }

        randomiseComplete = true;

        if (randomiseComplete) {
            InterfaceComponent confirmButton = null;
            for (InterfaceComponent component : interfaceComponents) {
                if (component.getIndex() == CONFIRM_BUTTON_COMPONENT_ID) {
                    confirmButton = component;
                    break;
                }
            }

            if (confirmButton != null && confirmButton.isVisible()) {
                confirmButton.click();
                delay(1000, 2000);
                DefaultUI.setStatus("Character customisation confirmed.");
            }
        }
    }
}