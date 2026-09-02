plugins {
    alias(libs.plugins.loom)
}

repositories {
    maven("https://maven.terraformersmc.com/")
}

dependencies {
    implementation(project(":common"))

    minecraft(libs.minecraft)
    implementation(libs.fabric.loader)
    implementation(libs.fabric.api)
    implementation(libs.modmenu)
}
