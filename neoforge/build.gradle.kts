plugins {
    alias(libs.plugins.neoforge)
}

dependencies {
    compileOnly(project(":common"))
    compileOnly(libs.neoforge)
}

sourceSets.main.configure {
    resources {
        srcDir("src/generated/resources")
        exclude("src/generated/**/.cache")
    }
}
