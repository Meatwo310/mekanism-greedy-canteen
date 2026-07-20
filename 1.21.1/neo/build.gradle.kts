plugins {
    id("neoforge-mod-conventions")
    id("neoforge-config-conventions")
}

val mekanismVersion = project.property("mekanismVersion").toString()

// Mod Dependencies
dependencies {
    implementation("mekanism:Mekanism:$mekanismVersion")
    ciRuntimeMods("mekanism:Mekanism:$mekanismVersion")
}
