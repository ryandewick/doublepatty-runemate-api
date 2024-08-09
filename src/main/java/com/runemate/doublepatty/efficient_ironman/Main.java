package com.runemate.doublepatty.efficient_ironman;

import com.runemate.game.api.script.framework.task.TaskBot;

import static com.runemate.doublepatty.api.MovementUtils.init;

public class Main extends TaskBot {
    @Override
    public void onStart(String... strings) {
        super.onStart(strings);
        System.out.println("Bot started.");
        init(this);
        add(new SellStartingGear(), new StartXMarksTheSpot(), new GetRunesFromMagicTutor());
    }
}