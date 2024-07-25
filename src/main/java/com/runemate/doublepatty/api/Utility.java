package com.runemate.doublepatty.api;

import com.runemate.game.api.script.Execution;

public class Utility {
    public static int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static void delay(int min, int max) {
        Execution.delay(randomNumber(min, max));
    }

    public static void delay(int ms) {
        Execution.delay(ms);
    }
}