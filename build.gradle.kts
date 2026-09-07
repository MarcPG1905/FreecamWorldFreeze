plugins {
    id("java")
}

extra["modId"] = "freecam_wf"
extra["modName"] = "Freecam World Freeze"
extra["modVersion"] = "1.2.0"
extra["modDescription"] = "Adds world freezing/pausing or slow-motion to almost any Freecam mod in singleplayer."
extra["modGitHubUrl"] = "https://github.com/MarcPG1905/FreecamWorldFreeze"
extra["minMinecraftVersion"] = "1.20.3"

allprojects {
    group = "com.marcpg"
    version = rootProject.extra["modVersion"] as String

    repositories {
        mavenLocal()
        mavenCentral()

        // So all dependencies can be located by both Gradle and the version catalogue updater.
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases/")
        maven("https://maven.terraformersmc.com/releases/")
        maven("https://repo.spongepowered.org/maven/")

        exclusiveContent {
            forRepository { maven("https://api.modrinth.com/maven") }
            filter { includeGroup("maven.modrinth") }
        }

        exclusiveContent {
            forRepository { maven("https://cursemaven.com") }
            filter { includeGroup("curse.maven") }
        }
    }
}

val childJars = configurations.create("childJars") {
    isCanBeConsumed = false
    isCanBeResolved = true
    isTransitive = false
}

dependencies {
    add(childJars.name, project(":common"))
    add(childJars.name, project(":fabric"))
    add(childJars.name, project(":forge"))
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

        manifest { // For Forge
            attributes["MixinConfigs"] = "freecam_wf.normal.mixins.json,freecam_wf.compat.mixins.json"
        }
    }
}

subprojects {
    @Suppress("AvoidApplyPluginMethod")
    apply(plugin = "java")

    java.toolchain.languageVersion = JavaLanguageVersion.of(25)

    tasks.processResources {
        val properties = rootProject.extra.properties.mapValues { it.value.toString() }
        inputs.properties(properties)
        filesMatching(listOf("fabric.mod.json", "META-INF/mods.toml", "META-INF/neoforge.mods.toml")) {
            expand(properties)
        }
    }
}
