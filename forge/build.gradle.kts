plugins {
    alias(libs.plugins.forge)
}

repositories {
    minecraft.mavenizer(this)
    maven(fg.forgeMaven)
    maven(fg.minecraftLibsMaven)
}

dependencies {
    implementation(project(":common"))

    implementation(minecraft.dependency(libs.forge))
    annotationProcessor(libs.forge.eventbusValidator)
}

sourceSets.main.configure {
    resources {
        srcDir("src/generated/resources")
    }
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["MixinConfigs"] = "freecam_wf.mixins.json"
    }
}
