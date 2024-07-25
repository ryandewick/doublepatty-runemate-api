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
    }
}