package com.runemate.doublepatty.dev_debug_tool;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.LoopingBot;
import com.runemate.game.api.script.framework.listeners.SettingsListener;
import com.runemate.game.api.script.framework.listeners.events.SettingChangedEvent;

public class Main extends LoopingBot implements SettingsListener {
    @Override
    public void onLoop() {
        Player player = Players.getLocal();
        if (player !=null) {
            int animationID = player.getAnimationId();
            System.out.println("Player animation ID: " + animationID);
        } else {
            System.out.println("Player is null");
        }
        for (InterfaceComponent component : Interfaces.getLoaded()) {
            System.out.println("Component: " + component.getId());
        }

    }

    @Override
    public void onSettingChanged(SettingChangedEvent settingChangedEvent) {

    }

    @Override
    public void onSettingsConfirmed() {

    }
}
