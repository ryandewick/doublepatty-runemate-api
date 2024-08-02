package com.runemate.doublepatty.craftingaio;

import com.runemate.doublepatty.craftingaio.enums.Battlestaffs;
import com.runemate.doublepatty.craftingaio.enums.Gems;
import com.runemate.doublepatty.craftingaio.enums.Types;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.LoopingBot;
import com.runemate.game.api.script.framework.listeners.SettingsListener;
import com.runemate.game.api.script.framework.listeners.events.SettingChangedEvent;
import com.runemate.ui.DefaultUI;
import com.runemate.ui.setting.annotation.open.SettingsProvider;

import static com.runemate.doublepatty.api.BankUtils.*;
import static com.runemate.doublepatty.api.InteractionUtils.makeAll;
import static com.runemate.doublepatty.api.InteractionUtils.useItemOn;

public class Main extends LoopingBot implements SettingsListener {

    @SettingsProvider(updatable = true)
    private Config settings;

    private boolean settingsConfirmed;

    Player player = Players.getLocal();

    @Override
    public void onStart(String... args) {
        getEventDispatcher().addListener(this);
        System.out.println("Bot started. Waiting for settings con   firmation.");
    }

    @Override
    public void onLoop() {

        if (settings == null) { return; }

        // Main operational logic based on user selections
        Types selectedType = settings.getType();
        switch (selectedType) {
            case BATTLESTAFFS:
                performBattlestaffCrafting(settings.getBattlestaff());
                break;
            case GEMS:
                performGemCrafting(settings.getGem());
                break;
            default:
                System.out.println("No valid crafting type selected.");
                break;
        }
    }

    private void checkInventory(String item1, String item2) {
        if (!Inventory.contains(item1) || !Inventory.contains(item2)) {
            DefaultUI.setStatus("Banking current Items & Checking for required items");
            openBank();
            depositAll();
            checkQuantity("Water orb", 14);
            checkQuantity("Battlestaff", 14);
            withdrawItem(item1, 14);
            withdrawItem(item2, 14);
            closeBank();
        }
    }

    private void performBattlestaffCrafting(Battlestaffs battlestaff) {
        System.out.println("Crafting " + battlestaff.getName() + "...");

        switch (battlestaff) {
            case AIR_BATTLESTAFF:
                checkInventory("Air orb", "Battlestaff");
                if (Inventory.contains("Air orb") && Inventory.contains("Battlestaff")) {
                    useItemOn("Air orb", "Battlestaff");
                    makeAll("Make");
                }
                break;

            case WATER_BATTLESTAFF:
                checkInventory("Water orb", "Battlestaff");
                if (Inventory.contains("Water orb") && Inventory.contains("Battlestaff")) {
                    useItemOn("Water orb", "Battlestaff");
                    makeAll("Make");
                }
                break;

            case EARTH_BATTLESTAFF:
                checkInventory("Earth orb", "Battlestaff");
                if (Inventory.contains("Earth orb") && Inventory.contains("Battlestaff")) {
                    useItemOn("Earth orb", "Battlestaff");
                    makeAll("Make");
                }
                break;

            case FIRE_BATTLESTAFF:
                checkInventory("Fire orb", "Battlestaff");
                if (Inventory.contains("Fire orb") && Inventory.contains("Battlestaff")) {
                    useItemOn("Fire orb", "Battlestaff");
                    makeAll("Make");
                }
                break;
        }
    }

    private void performGemCrafting(Gems gem) {
        System.out.println("Crafting with " + gem.getName() + "...");
        // Add specific crafting logic for each gem type
        switch (gem) {
            case OPAL:
                // Crafting logic for Opal
                break;
            case SAPPHIRE:
                // Crafting logic for Sapphire
                break;
            case EMERALD:
                // Crafting logic for Emerald
                break;
            case RUBY:
                // Crafting logic for Ruby
                break;
            case DIAMOND:
                // Crafting logic for Diamond
                break;
        }
    }

    @Override
    public void onSettingChanged(SettingChangedEvent settingChangedEvent) {
        String settingKey = settingChangedEvent.getKey();
        switch (settingKey) {
            case "type":
                Types selectedType = settings.getType();
                System.out.println("Type changed: " + selectedType);
                break;
            case "battlestaff":
                Battlestaffs selectedBattlestaff = settings.getBattlestaff();
                System.out.println("Battlestaff changed: " + selectedBattlestaff.getName());
                break;
            case "gem":
                Gems selectedGem = settings.getGem();
                System.out.println("Gem changed: " + selectedGem.getName());
                break;
            default:
                System.out.println("Setting changed: " + settingKey);
                break;
        }
    }

    @Override
    public void onSettingsConfirmed() {
        settingsConfirmed = true;
        System.out.println("Settings have been confirmed.");
    }
}
