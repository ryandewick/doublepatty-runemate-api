package com.runemate.doublepatty.craftingaio;

import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.LoopingBot;
import com.runemate.game.api.script.framework.listeners.EngineListener;
import com.runemate.game.api.script.framework.listeners.SettingsListener;
import com.runemate.game.api.script.framework.listeners.events.SettingChangedEvent;
import com.runemate.ui.setting.annotation.open.SettingsProvider;
import lombok.Getter;

public class Main extends LoopingBot implements SettingsListener, EngineListener {
    boolean started = false;
    State state;

    enum State {
        BANK, STAFFS
    }

    @Getter
    @SettingsProvider(updatable = true)
    private Config config;

    @Override
    public void onTickStart() {
        if (isStopped()) return;

        if (ChatDialog.isOpen() && ChatDialog.getContinue() != null) {
            if (ChatDialog.getContinue().select(true)) {
                Execution.delayUntil(() -> !ChatDialog.isOpen(), 2000);
            }

        }
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
