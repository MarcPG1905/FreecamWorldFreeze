plugins {
    alias(libs.plugins.forge)
}

repositories {
    minecraft.mavenizer(this)
    maven(fg.forgeMaven)
    maven(fg.minecraftLibsMaven)
}

dependencies {
    compileOnly(project(":common"))

    compileOnly(minecraft.dependency(libs.forge))
    annotationProcessor(libs.forge.eventbusValidator)
}

sourceSets.main.configure {
    resources {
        srcDir("src/generated/resources")
    }
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["MixinConfigs"] = "freecam_wf.normal.mixins.json,freecam_wf.compat.mixins.json"
    }
}
