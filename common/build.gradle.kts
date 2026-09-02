plugins {
    id("java-library")
    alias(libs.plugins.loom)
}

java.toolchain.languageVersion = JavaLanguageVersion.of(25)

repositories {
    maven("https://repo.spongepowered.org/maven/")
}

dependencies {
    minecraft(libs.minecraft)
    compileOnly(libs.mixin)

    // Fabric version - doesn't really matter, just need the base API.
    compileOnlyApi("maven.modrinth:XeEZ3fK2:OqDcTeQ8")
}
