plugins {
    alias(libs.plugins.neoforge)
}

dependencies {
    implementation(project(":common"))

    implementation(libs.neoforge)
}

sourceSets.main.configure {
    resources {
        srcDir("src/generated/resources")
        exclude("src/generated/**/.cache")
    }
}
