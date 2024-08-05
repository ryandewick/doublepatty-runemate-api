package com.runemate.doublepatty.tutisland;

import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.framework.task.Task;
import com.runemate.ui.DefaultUI;

import java.util.List;

import static com.runemate.doublepatty.api.Utility.delay;

public class ChooseUsername extends Task {

    private static final int USERNAME_INTERFACE_ID = 558;
    private static final int USERNAME_INPUT_COMPONENT_ID = 12;
    private static final int USERNAME_SUBMIT_COMPONENT_ID = 18;
    private static final int RANDOM_USERNAME_ID = 16;

    @Override
    public boolean validate() {
        InterfaceComponent usernameInput = null;
        List<InterfaceComponent> interfaceComponents = Interfaces.newQuery()
                .containers(558)
                .results().asList();
        for (InterfaceComponent component : interfaceComponents) {
            int componentId = component.getIndex();
            if(componentId == USERNAME_INPUT_COMPONENT_ID) {
                usernameInput = component;
            }
        }

        return usernameInput != null;
    }

    @Override
    public void execute() {
        DefaultUI.setStatus("Choosing a username...");

        InterfaceComponent usernameInput = null;
        InterfaceComponent submitButton = null;
        InterfaceComponent randomUsernameWidget = null;

        List<InterfaceComponent> interfaceComponents = Interfaces.newQuery()
                .containers(558)
                .results().asList();
        for (InterfaceComponent component : interfaceComponents) {
            if(component.getIndex() == USERNAME_INPUT_COMPONENT_ID) {
                usernameInput = component;
            }
            if(component.getIndex() == USERNAME_SUBMIT_COMPONENT_ID) {
                System.out.println("Found submit button: " + component);
                submitButton = component;
            }
            if(component.getIndex() == RANDOM_USERNAME_ID) {
                randomUsernameWidget = component;
            }

        }

        // Generate a random username
        String randomUsername = RandomUsernameGenerator.generateRandomUsername();
        System.out.println("Generated username: " + randomUsername);

        // Find the username input field and enter the username
        if (usernameInput != null && usernameInput.isVisible()) {
            System.out.println("Found username input field.");
            usernameInput.click();
            delay(600, 1200);
            Keyboard.type(randomUsername, false);
            System.out.println("Typed username: " + randomUsername);

            // Find the submit button and click it
            if (submitButton != null && submitButton.isVisible()) {
                submitButton.click();
                delay(2000, 4000);
                DefaultUI.setStatus("Submitted username: " + randomUsername);
            } else {
                DefaultUI.setStatus("Failed to find the submit button.");
            }
        } else {
            DefaultUI.setStatus("Failed to find the username input field.");
        }

        if(randomUsernameWidget != null && randomUsernameWidget.isVisible()) {
            randomUsernameWidget.click();
            delay(2000, 4000);
        } else {
            submitButton.click();
            delay(2000, 4000);
        }
    }
}