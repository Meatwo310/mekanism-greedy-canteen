import net.meatwo310.mdk.build.req

plugins {
    id("lexforge-legacy-mod-conventions")
    id("lexforge-legacy-config-conventions")
}

val configuredVersion = project.property("configuredVersion").toString()
val mekanismVersion = project.property("mekanismVersion").toString()

// Mod Dependencies
dependencies {
    modImplementation("mekanism:Mekanism:$mekanismVersion")
    ciRuntimeMods("mekanism:Mekanism:$mekanismVersion")
    modRuntimeOnly(libs.configured, req(configuredVersion))
}
