package com.runemate.doublepatty.tutisland;

import com.runemate.game.api.script.framework.task.TaskBot;

public class Main extends TaskBot {
    @Override
    public void onStart(String... strings) {
        super.onStart(strings);
        System.out.println("Bot started.");
        add(new ChooseUsername(), new RandomiseChar());
    }
}