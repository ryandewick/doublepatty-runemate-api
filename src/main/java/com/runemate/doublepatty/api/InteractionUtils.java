package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.direct.DirectInput;
import com.runemate.game.api.hybrid.input.direct.MenuAction;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.MakeAllInterface;
import com.runemate.game.api.script.Execution;
import com.runemate.ui.DefaultUI;

import java.util.List;

import static com.runemate.doublepatty.api.InventoryUtils.hasItemInInventory;
import static com.runemate.doublepatty.api.Utility.delay;

public class InteractionUtils {
    static Player player = Players.getLocal();

    public static void chopTreeInArea(Coordinate area, String treeName, int proximityRange) {
        Player player = Players.getLocal();

        if (!MovementUtils.isAtLocationProximity(area, proximityRange)) {
            MovementUtils.walkTo(area);
        }

        DefaultUI.setStatus("Looking for " + treeName);

        GameObject tree = GameObjects.newQuery().names(treeName).results().nearest();
        if (tree != null && tree.isVisible()) {
            while (!InventoryUtils.isInventoryFull() && player.isIdle()) {
                DefaultUI.setStatus("Chopping " + treeName);
                tree.interact("Chop down");
                delay(600, 5000);
            }
        } else {
            DefaultUI.setStatus("No trees found, idling...");
            delay(600, 2000);
        }
    }
    public static void useItemOn(String itemName1, String itemName2) {
        SpriteItem item1 = Inventory.getItems(itemName1).first();
        SpriteItem item2 = Inventory.getItems(itemName2).first();
        Execution.delay(1000, 2000);

        if (item1 != null && item2 != null && player.getAnimationId() == -1) {
            item1.click();
            Execution.delay(1000, 1400);
            item2.click();
            Execution.delayUntil(MakeAllInterface::isOpen, 600, 1200);
        }
    }
    public static void makeAll(String action) {
        if (MakeAllInterface.isOpen()) {
            if (MakeAllInterface.getSelectedQuantity() != 0) {
                DefaultUI.setStatus("Setting quantity to ALL");
                MakeAllInterface.setSelectedQuantity(0);
                Execution.delay(600, 1200);
            }
            DefaultUI.setStatus("Making All");
            InterfaceComponent makeAllButton = Interfaces.newQuery().containers(270).actions(action).results().first();
            if (makeAllButton != null) {
                makeAllButton.click();
                Execution.delayUntil(() -> player.getAnimationId() == -1, 600, 1200);}
        }
    }

    public static void dropItem(String itemName) {
        if(Inventory.getItems(itemName).first() != null) {
            Inventory.getItems(itemName).first().interact("Drop");
            Execution.delay(600, 1200);
        }
    }

    public static void dropItems(String[] itemNames) {
        for (String itemName : itemNames) {
            if(Inventory.getItems(itemName).first() != null) {
                Inventory.getItems(itemName).first().interact("Drop");
                Execution.delay(600, 1200);
            }
        }
    }

    public static void pickUpItem(String itemName) {
        GroundItem item = GroundItems.newQuery().names(itemName).results().first();

        if (item != null && item.isVisible()) {
            item.interact("Take");
            Execution.delayUntil(() -> hasItemInInventory(itemName));
        }
    }


    public static void sellItemToShop(String itemName, String quantity) {
        SpriteItem item = Inventory.newQuery().names(itemName).results().first();

        if (item != null) {
            DefaultUI.setStatus("Selling " + quantity + " " + itemName);
            item.interact("Sell" + " " + quantity);
            Execution.delay(600, 1200);
        }
    }

    public static void buyItemFromShop(String itemName, String quantity) {
        List<InterfaceComponent> interfaceComponents = Interfaces.newQuery()
                .containers(300)
                .results().asList();

        if (interfaceComponents == null || interfaceComponents.isEmpty()) {
            return;
        }

        for (InterfaceComponent component : interfaceComponents) {
            if (component != null && component.getName() != null && component.getName().contains(itemName)) {
                DefaultUI.setStatus("Buying " + quantity + " " + itemName);
                DirectInput.send(MenuAction.forInterfaceComponent(component, "Buy " + quantity));
                Execution.delay(600, 1200);
                return;
            }
        }
    }

    public static void continueDialogue(int containerId, int childIndex) {
        List<InterfaceComponent> interfaceComponents = Interfaces.newQuery()
                .containers(containerId)
                .results().asList();

        for (InterfaceComponent component : interfaceComponents) {
            if (component.getIndex() == childIndex) {
                DefaultUI.setStatus("Continuing dialogue...");
                DirectInput.send(MenuAction.forInterfaceComponent(component, "Continue"));
                delay(600, 1200);
            }
        }
    }

    public static void selectAnswer(int containerId, String text) {
        List<InterfaceComponent> interfaceComponents = Interfaces.newQuery()
                .containers(containerId)
                .results().asList();

        for (InterfaceComponent component : interfaceComponents) {
            if (text.equals(component.getText())) {
                DefaultUI.setStatus("Selecting " + text);
                DirectInput.send(MenuAction.forInterfaceComponent(component, "Continue"));
                delay(600, 1200);
                break;
            }
        }
    }

    public static void selectAnswer(int containerId, int childIndex) {
        List<InterfaceComponent> interfaceComponents = Interfaces.newQuery()
                .containers(containerId)
                .results().asList();

        for (InterfaceComponent component : interfaceComponents) {
            if (component.getIndex() == childIndex) {
                DefaultUI.setStatus("Selecting " + component.getText());
                DirectInput.send(MenuAction.forInterfaceComponent(component, "Continue"));
                delay(600, 1200);
                break;
            }
        }
    }
}
