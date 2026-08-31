plugins {
    id("net.neoforged.gradle.userdev") version "7.1.38"
}

java.toolchain.languageVersion = JavaLanguageVersion.of(25)

dependencies {
    implementation(project(":common"))

    implementation("net.neoforged:neoforge:26.2.0.75")
}

sourceSets.main.configure {
    resources {
        srcDir("src/generated/resources")
        exclude("src/generated/**/.cache")
    }
}
