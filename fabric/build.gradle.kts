plugins {
    id("net.fabricmc.fabric-loom") version "1.17-SNAPSHOT"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(25)

repositories {
    maven("https://maven.terraformersmc.com/")
}

dependencies {
    implementation(project(":common"))

    minecraft("com.mojang:minecraft:26.2")
    implementation("net.fabricmc:fabric-loader:0.19.3")
    implementation("net.fabricmc.fabric-api:fabric-api:0.158.0+26.2")

    implementation("com.terraformersmc:modmenu:20.0.1")
}
