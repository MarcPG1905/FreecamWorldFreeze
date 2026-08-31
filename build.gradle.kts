plugins {
    id("java")
}

extra["modId"] = "freecam_wf"
extra["modName"] = "Freecam World Freeze"
extra["modVersion"] = "1.0.0"
extra["modDescription"] = "Freezes your singleplayer world when using the freecam."
extra["modGitHubUrl"] = "https://github.com/MarcPG1905/FreecamWorldFreeze"
extra["minMinecraftVersion"] = "1.20.3"

allprojects {
    group = "com.marcpg"
    version = rootProject.extra["modVersion"] as String
}

tasks {
    build {
        dependsOn(jar)
    }

    jar {
        archiveBaseName = "FreecamWorldFreeze"

        dependsOn(
            ":common:classes",
            ":fabric:processResources",
            ":neoforge:processResources",
        )

        from(project(":common").sourceSets.main.get().output)
        from(project(":fabric").sourceSets.main.get().output)
        from(project(":neoforge").sourceSets.main.get().output)
    }
}

subprojects {
    @Suppress("AvoidApplyPluginMethod")
    apply(plugin = "java")

    repositories {
        mavenLocal()
        mavenCentral()

        exclusiveContent {
            forRepository { maven("https://api.modrinth.com/maven") }
            filter { includeGroup("maven.modrinth") }
        }
    }

    tasks.processResources {
        val properties = rootProject.extra.properties.mapValues { it.value.toString() }

        inputs.properties(properties)

        filesMatching(listOf(
            "fabric.mod.json",
            "META-INF/mods.toml",
            "META-INF/neoforge.mods.toml",
        )) {
            expand(properties)
        }
    }
}
