plugins {
    id("java")
}

extra["modId"] = "freecam_wf"
extra["modName"] = "Freecam World Freeze"
extra["modVersion"] = "1.1.0"
extra["modDescription"] = "Freezes/pauses or slows down your singleplayer world while in freecam."
extra["modGitHubUrl"] = "https://github.com/MarcPG1905/FreecamWorldFreeze"
extra["minMinecraftVersion"] = "1.20.3"

allprojects {
    group = "com.marcpg"
    version = rootProject.extra["modVersion"] as String
}

val childJars = configurations.create("childJars") {
    isCanBeConsumed = false
    isCanBeResolved = true
    isTransitive = false
}

dependencies {
    add(childJars.name, project(":common"))
    add(childJars.name, project(":fabric"))
    add(childJars.name, project(":neoforge"))
}

tasks {
    build {
        dependsOn(jar)
    }
    jar {
        archiveBaseName = "FreecamWorldFreeze"

        dependsOn(childJars)
        from(childJars.map(::zipTree))

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
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
        filesMatching(listOf("fabric.mod.json", "META-INF/neoforge.mods.toml")) {
            expand(properties)
        }
    }
}
