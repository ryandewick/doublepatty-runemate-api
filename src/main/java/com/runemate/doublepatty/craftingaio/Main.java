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
    }

    @Override
    public void onLoop() {
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
