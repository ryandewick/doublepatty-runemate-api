import com.runemate.game.api.bot.data.Category
plugins {
    id("java")
    id("com.runemate") version "1.3.0"
}

group = "com.runemate.doublepatty"
version = "1.0.0"

runemate {
    devMode = true
    autoLogin = true

    manifests {
        create("Progressive Woodcutter") {
            mainClass = "com.runemate.doublepatty.progressivewoodcutter.Main"
            tagline = "Progressive Woodcutter"
            description = "Progressive Woodcutter from DoublePatty"
            version = "1.0.0"
            internalId = "doublepatty-woodcutter"
            categories(Category.WOODCUTTING)
        };
        create("Crating AIO") {
            mainClass = "com.runemate.doublepatty.craftingaio.Main"
            tagline = "Crafting Scripts all In one"
            description = "Crafting All In One, does everything"
            version = "0.1"
            internalId = "jd-01"
            categories(Category.CRAFTING)
        }
    }
}