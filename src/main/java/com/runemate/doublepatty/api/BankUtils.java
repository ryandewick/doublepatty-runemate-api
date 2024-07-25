package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.Execution;

public class BankUtils {
    public static void openBank() {
        if (!Bank.isOpen()) {
            Bank.open();
            Execution.delayUntil(Bank::isOpen, Utility.randomNumber(1000, 4000));
        }
    }

    public static void closeBank() {
        if (Bank.isOpen()) {
            Bank.close();
            Execution.delayUntil(() -> !Bank.isOpen(), Utility.randomNumber(2000, 3000));
        }
    }

    public static boolean bankContains(String itemName) {
        return Bank.isOpen() && Bank.contains(itemName);
    }

    public static void depositAllExcept(String itemName) {
        if (Bank.isOpen()) {
            Bank.depositAllExcept(itemName);
        }
    }

    public static void withdrawItem(String itemName, int amount) {
        if (Bank.isOpen()) {
            Bank.withdraw(itemName, amount);
            Execution.delay(Utility.randomNumber(2000, 4000));
        }
    }
}