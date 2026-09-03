rootProject.name = "FreecamWorldFreeze"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases/")
    }
}

include(
    "common",
    "fabric",
    "forge",
    "neoforge",
)
