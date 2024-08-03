package com.runemate.doublepatty.api;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.Execution;
import com.runemate.ui.DefaultUI;

public class BankUtils {

    public static void openBank() {
        DefaultUI.setStatus("Opening bank");
        if (!Bank.isOpen()) {
            Bank.open();
            Execution.delayUntil(Bank::isOpen, 1000, 4000);
        }
    }

    public static void closeBank() {
        DefaultUI.setStatus("Closing bank");
        if (Bank.isOpen()) {
            Bank.close();
            Execution.delayUntil(() -> !Bank.isOpen(), 1000, 3000);
        }
    }

    public static boolean bankContains(String itemName) {
        DefaultUI.setStatus("Checking bank for " + itemName);
        if (Bank.isOpen()) {
            return Bank.contains(itemName);
        }
        return false;
    }


    public static void depositAll() {
        DefaultUI.setStatus("Depositing all items");
        if (Bank.isOpen()) {
            Bank.depositInventory();
        }
    }

    public static void depositAllExcept(String itemName) {
        DefaultUI.setStatus("Depositing all items except " + itemName);
        if (Bank.isOpen()) {
            Bank.depositAllExcept(itemName);
        }
    }

    public static void withdrawItem(String itemName, int amount) {
        DefaultUI.setStatus("Withdrawing " + amount + " " + itemName);
        if (Bank.isOpen()) {
            Bank.withdraw(itemName, amount);
            Utility.delay(1000, 3000);
        }
    }

    public static boolean checkQuantity(String itemName, int amount) {
        if (Bank.isOpen()) {
            DefaultUI.setStatus("Checking quantity of " + itemName);
            int itemQuantity = Bank.getQuantity(itemName);
            if (itemQuantity >= amount) {
                return true;
            } else {
                DefaultUI.setStatus("Bank doesn't contain enough " + itemName + ", stopping script...");
                return false;
            }
        }
        return false;
    }
        }
