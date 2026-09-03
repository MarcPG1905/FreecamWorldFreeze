plugins {
    id("java-library")
    alias(libs.plugins.loom)
}

java.toolchain.languageVersion = JavaLanguageVersion.of(25)

repositories {
    maven("https://repo.spongepowered.org/maven/")
}

dependencies {
    minecraft(libs.minecraft)
    compileOnly(libs.mixin)
    compileOnly(libs.asm.tree)

    // All the different compatible mods.              // [PREFIX] - [PACKAGE] - [LINK]
    compileOnly("curse.maven:project-1455610:8521863") // Wurst* - net.wimods - https://www.curseforge.com/minecraft/mc-mods/wi-freecam
    compileOnly("curse.maven:project-266734:8604113")  // Kapiteon* - com.kapiteon - https://www.curseforge.com/minecraft/mc-mods/freecam
    compileOnly("curse.maven:project-618947:8272599")  // Zergatul* - com.zergatul - https://www.curseforge.com/minecraft/mc-mods/freecam-by-zergatul
    compileOnly("maven.modrinth:CHn2cb7p:gG6J64nB")    // EasyFreecam* - dev.elpu7.easyFreecam - https://modrinth.com/mod/easy-freecam
    compileOnly("maven.modrinth:EiMzaPzk:9AmPyZhW")    // FreeCamMc* - com.jasonzli - https://modrinth.com/mod/cameratweaks
    compileOnly("maven.modrinth:T1E4i1qj:id6o0lrn")    // CamTweaks* - cameratweaks - https://modrinth.com/mod/cameratweaks
    compileOnly("maven.modrinth:XeEZ3fK2:OqDcTeQ8")    // Xolt* - net.xolt - https://modrinth.com/mod/freecam
    compileOnly("maven.modrinth:iPcmjKj7:ICsOm6bd")    // CamEnhance* - me.syflog.camenh - https://modrinth.com/mod/camenh
}
