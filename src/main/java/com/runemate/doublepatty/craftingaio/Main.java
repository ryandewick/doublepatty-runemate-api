package com.runemate.doublepatty.craftingaio;

import com.runemate.doublepatty.craftingaio.enums.Battlestaffs;
import com.runemate.doublepatty.craftingaio.enums.Gems;
import com.runemate.doublepatty.craftingaio.enums.Types;
import com.runemate.game.api.script.framework.LoopingBot;
import com.runemate.game.api.script.framework.listeners.SettingsListener;
import com.runemate.game.api.script.framework.listeners.events.SettingChangedEvent;
import com.runemate.ui.setting.annotation.open.SettingsProvider;

public class Main extends LoopingBot implements SettingsListener {

    @SettingsProvider(updatable = true)
    private Config settings;

    private boolean settingsConfirmed;

    @Override
    public void onStart(String... args) {
        getEventDispatcher().addListener(this);
        System.out.println("Bot started. Waiting for settings confirmation.");
    }

    @Override
    public void onLoop() {

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

    private void performBattlestaffCrafting(Battlestaffs battlestaff) {
        System.out.println("Crafting " + battlestaff.getName() + "...");
        // Add specific crafting logic for each battlestaff type
        switch (battlestaff) {
            case AIR_BATTLESTAFF:
                // Crafting logic for Air Battlestaff
                break;
            case WATER_BATTLESTAFF:
                // Crafting logic for Water Battlestaff
                break;
            case EARTH_BATTLESTAFF:
                // Crafting logic for Earth Battlestaff
                break;
            case FIRE_BATTLESTAFF:
                // Crafting logic for Fire Battlestaff
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
