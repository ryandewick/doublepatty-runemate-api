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
        create("Test SCRIPT RYAN") {
            mainClass = "com.runemate.doublepatty.TestBot"
            tagline = "Just testing shizzle!"
            description = "Just a simple test to see if this works"
            version = "1.0.0"
            internalId = "example-test"
        }
    manifests {
        create("Crating AIO") {
            mainClass = "com.runemate.doublepatty.craftingaio.Main"
            tagline = "Crafting Scripts all In one"
            description = "Crafting All In One, does everything"
            version = "0.1"
            internalId = "jd-01"
        }

    }
    }
}