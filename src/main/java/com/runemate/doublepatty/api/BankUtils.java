package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.Execution;
import com.runemate.ui.DefaultUI;

public class BankUtils {
    static BotGUI gui = BotGUI.getInstance();

    public static void openBank() {
        if (!Bank.isOpen()) {
            gui.setAction("Opening bank");
            Bank.open();
            Execution.delayUntil(Bank::isOpen, 1000, 4000);
        }
    }

    public static void closeBank() {
        if (Bank.isOpen()) {
            gui.setAction("Closing bank");
            Bank.close();
            Execution.delayUntil(() -> !Bank.isOpen(), 1000, 3000);
        }
    }

    public static boolean bankContains(String itemName) {
        if (Bank.isOpen()) {
            gui.setAction("Checking bank for " + itemName);
            return Bank.contains(itemName);
        }
        return false;
    }


    public static void depositAll() {
        if (Bank.isOpen()) {
            gui.setAction("Depositing all items");
            Bank.depositInventory();
        }
    }

    public static void depositAllExcept(String itemName) {
        if (Bank.isOpen()) {
            gui.setAction("Depositing all items except " + itemName);
            Bank.depositAllExcept(itemName);
        }
    }

    public static void withdrawItem(String itemName, int amount) {
        if (Bank.isOpen()) {
            gui.setAction("Withdrawing " + amount + " " + itemName);
            Bank.withdraw(itemName, amount);
            Utility.delay(1000, 3000);
        }
    }

    public static void checkQuantity(String itemName, int amount) {
        if (Bank.isOpen()) {
            DefaultUI.setStatus("Checking quantity of " + itemName);
            if (Bank.contains(itemName)) {
                if (Bank.getQuantity(itemName) < amount) {
                    DefaultUI.setStatus("Bank doesn't contain enough " + itemName + ", stopping script...");
                }
            }
        }
    }
}