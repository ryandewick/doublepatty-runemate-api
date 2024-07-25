package com.runemate.doublepatty.api;

public class LoggerUtils {
    public static void log(String message) {
        System.out.println("[INFO] " + message);
    }

    public static void logError(String message) {
        System.err.println("[ERROR] " + message);
    }
}