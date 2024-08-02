package com.runemate.doublepatty.accountbuild;

import com.runemate.game.api.script.framework.LoopingBot;
import com.runemate.game.api.script.framework.listeners.SettingsListener;
import com.runemate.game.api.script.framework.listeners.events.SettingChangedEvent;

public class Main extends LoopingBot implements SettingsListener {

    @Override
    public void onStart(String... args) {
        getEventDispatcher().addListener(this);
        System.out.println("Bot started. Waiting for settings confirmation.");
    }

    @Override
    public void onLoop() {

    }

    @Override
    public void onSettingChanged(SettingChangedEvent settingChangedEvent) {

    }

    @Override
    public void onSettingsConfirmed() {

    }
}