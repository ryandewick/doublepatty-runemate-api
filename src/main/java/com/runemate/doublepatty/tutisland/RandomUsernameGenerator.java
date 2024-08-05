package com.runemate.doublepatty.tutisland;

import java.util.Random;

public class RandomUsernameGenerator {

    private static final String[] SUPERHERO_NAMES = {
            "SpiderMan", "IronMan", "Batman", "Superman", "WonderWoman", "Thor", "Hulk", "BlackWidow",
            "Aquaman", "GreenLantern", "Flash", "Cyborg", "GreenArrow", "BlackCanary", "DoctorFate", "MartianManhunter"
    };

    private static final String[] MARVEL_NAMES = {
            "CaptainAmerica", "DoctorStrange", "BlackPanther", "ScarletWitch", "Vision", "Hawkeye",
            "AntMan", "Wasp", "Groot", "StarLord", "Gamora", "Drax", "RocketRaccoon", "Loki", "NickFury", "Quicksilver"
    };

    private static final String[] DISNEY_NAMES = {
            "MickeyMouse", "DonaldDuck", "Goofy", "Pluto", "Simba", "Mufasa", "Nemo", "Elsa",
            "Anna", "Olaf", "Ariel", "Belle", "Beast", "Aladdin", "Jasmine", "Genie", "Pumbaa", "Timon", "Cinderella"
    };

    private static final String[] FOOTBALLERS_NAMES = {
            "Messi", "Ronaldo", "Neymar", "Mbappe", "Pele", "Maradona", "Zidane", "Beckham",
            "Ronaldinho", "Henry", "Rooney", "Ramos", "Lewandowski", "Modric", "Salah", "Kane", "Hazard", "Iniesta"
    };

    private static final String[] TV_SHOW_NAMES = {
            "WalterWhite", "JonSnow", "SherlockHolmes", "MichaelScott", "RachelGreen", "JoeyTribbiani",
            "HomerSimpson", "BartSimpson", "MargeSimpson", "LisaSimpson", "NedStark", "AryaStark", "TyrionLannister",
            "DarylDixon", "RickGrimes", "BuffySummers", "DexterMorgan", "Eleven", "JimHalpert", "DwightSchrute"
    };

    private static final String[] OSRS_NAMES = {
            "Zezima", "KingBlackDragon", "Elvarg", "WiseOldMan", "BobTheCat", "CountDraynor", "Duradel",
            "AbyssalSire", "Vorkath", "Konar", "Xeric", "Seren", "Verzik", "Nieve", "Nex", "Nezikchened",
            "KrilTsutsaroth", "KreeArra", "GeneralGraardor", "CommanderZilyana", "CorporealBeast", "Zalcano"
    };

    private static final String[][] ALL_NAMES = {
            SUPERHERO_NAMES, MARVEL_NAMES, DISNEY_NAMES, FOOTBALLERS_NAMES, TV_SHOW_NAMES, OSRS_NAMES
    };

    public static String generateRandomUsername() {
        Random random = new Random();
        int categoryIndex = random.nextInt(ALL_NAMES.length);
        String[] selectedCategory = ALL_NAMES[categoryIndex];
        int nameIndex = random.nextInt(selectedCategory.length);
        return selectedCategory[nameIndex];
    }
}