package com.runemate.doublepatty.api;

import com.runemate.game.api.script.Execution;

public class Utility {

    public static void delay(int min, int max) {
        Execution.delay(min, max);
    }

    public static void delay(int ms) {
        Execution.delay(ms);
    }
}