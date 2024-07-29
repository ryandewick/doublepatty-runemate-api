package com.runemate.doublepatty.craftingaio;

import com.runemate.doublepatty.craftingaio.enums.Gems;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.script.framework.LoopingBot;
import com.runemate.game.api.script.framework.listeners.EngineListener;
import com.runemate.game.api.script.framework.listeners.SettingsListener;
import com.runemate.game.api.script.framework.listeners.events.SettingChangedEvent;
import com.runemate.ui.setting.annotation.open.SettingsProvider;
import lombok.Getter;

public class Main extends LoopingBot implements SettingsListener, EngineListener {
    boolean started = false;

    private Player player;

    @Override
    public void onStart(String... args) {
        System.out.println("Bot started!");
    }

    @Override
    public void onStop() {
        System.out.println("Bot stopped!");
    }

    @Getter
    @SettingsProvider(updatable = true)
    private Config config;


    @Override
    public void onLoop() {
        if (config.gems() == Gems.RUBY) {
            System.out.println("Ruby");
        } else if (config.gems() == Gems.SAPPHIRE) {
            System.out.println("Sapphire");
        } else if (config.gems() == Gems.EMERALD) {
            System.out.println("Emerald");
        } else if (config.gems() == Gems.DIAMOND) {
            System.out.println("Diamond");
        } else if (config.gems() == Gems.DRAGONSTONE) {
            System.out.println("Dragonstone");
        } else if (config.gems() == Gems.ONYX) {
            System.out.println("Onyx");

        }
    }

    @Override
    public void onSettingChanged(SettingChangedEvent settingChangedEvent) {

    }

    @Override
    public void onSettingsConfirmed() {
        if (!started) {
            started = true;
        }

    }
}