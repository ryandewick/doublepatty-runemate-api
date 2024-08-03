import com.runemate.game.api.bot.data.Category

plugins {
    id("java")
    id("com.runemate") version "1.3.0"
}

repositories {
    maven(url = "https://gitlab.com/api/v4/projects/60393439/packages/maven")
}

dependencies {
    compileOnly("com.runemate:runemate-pathfinder-api:1.0.7")
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
            version = "0.3"
            internalId = "jd-01"
            categories(Category.CRAFTING)
        };
        create("Account Builder") {
            mainClass = "com.runemate.doublepatty.accountbuild.Main"
            tagline = "Account Builder"
            description = "All in one account builder, for Baby pures, Zerkers, BH pures, etc. "
            version = "1.0.0"
            internalId = "jd-02"
            categories(Category.COMBAT)
        };
        create("Debugger") {
            mainClass = "com.runemate.doublepatty.dev_debug_tool.Main"
            tagline = "Debugger"
            description = "Debugging tool, animation & Interface ids, etc."
            categories(Category.OTHER)
        }
    }
}