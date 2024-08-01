package com.runemate.doublepatty.craftingaio;

import com.runemate.doublepatty.craftingaio.enums.Battlestaffs;
import com.runemate.doublepatty.craftingaio.enums.Gems;
import com.runemate.doublepatty.craftingaio.enums.Types;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.osrs.local.hud.interfaces.MakeAllInterface;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.LoopingBot;
import com.runemate.game.api.script.framework.listeners.SettingsListener;
import com.runemate.game.api.script.framework.listeners.events.SettingChangedEvent;
import com.runemate.ui.DefaultUI;
import com.runemate.ui.setting.annotation.open.SettingsProvider;

import static com.runemate.doublepatty.api.SpriteItemsUtils.useItemOn;

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

    public void makeAll(String action) {
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

    private void performBattlestaffCrafting(Battlestaffs battlestaff) {
        System.out.println("Crafting " + battlestaff.getName() + "...");

        switch (battlestaff) {
            case AIR_BATTLESTAFF:
                // Crafting logic for Air Battlestaff
                break;
            case WATER_BATTLESTAFF:
                if (!Bank.isOpen()) {
                    DefaultUI.setStatus("Opening bank");
                    Bank.open();
                    if (Bank.contains("Water orb") && Bank.contains("Battlestaff")) {
                        DefaultUI.setStatus("Withdrawing items from bank");
                        Bank.withdraw("Water orb", 14);
                        Bank.withdraw("Battlestaff", 14);
                        Execution.delayUntil(() -> Bank.contains("Water orb") && Bank.contains("Battlestaff"), 600, 1200);
                        Bank.close();
                    } else {
                        if (!Bank.contains("Water orb") || !Bank.contains("Battlestaff")) {
                            DefaultUI.setStatus("Missing items in bank");
                            return;

                        }
                    }
                    if (Inventory.contains("Water orb") && Inventory.contains("Battlestaff")) {
                        DefaultUI.setStatus("Using items on each other");
                        useItemOn("Water orb", "Battlestaff");
                        Execution.delayUntil(() -> !Inventory.contains("Water orb") && !Inventory.contains("Battlestaff"), 600, 1200);
                        if (MakeAllInterface.isOpen()) {
                            DefaultUI.setStatus("Crafting Water battlestaff");
                            makeAll("Make");
                        }
                        Execution.delay(20000);
                        Bank.depositInventory();
                        Execution.delay(2000);
                        return;
                    }

                    if (Inventory.contains("Water battlestaff")) {
                        DefaultUI.setStatus("Depositing Water battlestaffs");
                        Bank.open();
                        Bank.depositInventory();
                    }
                    return;
                }


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
