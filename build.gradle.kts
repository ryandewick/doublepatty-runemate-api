import com.runemate.game.api.bot.data.Category
import com.runemate.game.api.bot.data.FeatureType

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
        create("Efficient Ironman") {
            mainClass = "com.runemate.doublepatty.efficientironman.Main"
            tagline = "Automate Efficient Ironman Guide"
            description = "Completes 1.1 on ironman.guide"
            version = "1.0.0"
            internalId = "doublepatty-efficient-ironman"
            categories(Category.OTHER)

            features {
                required(FeatureType.DIRECT_INPUT)
            }
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
        }
    }
}