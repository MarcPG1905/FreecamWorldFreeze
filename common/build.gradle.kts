plugins {
    id("java-library")
    id("net.fabricmc.fabric-loom") version "1.17-SNAPSHOT"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(25)

repositories {
    maven("https://repo.spongepowered.org/maven/")
}

dependencies {
    minecraft("com.mojang:minecraft:26.2")
    compileOnly("org.spongepowered:mixin:0.8.7")

    // Fabric version - doesn't really matter, just need the base API.
    compileOnlyApi("maven.modrinth:XeEZ3fK2:OqDcTeQ8")
}
