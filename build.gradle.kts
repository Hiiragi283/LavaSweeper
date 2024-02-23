plugins {
    alias(libs.plugins.fabric.loom)
}

group = "io.github.hiiragi283"
version = "1.0.0+fabric1182"

repositories {
    mavenCentral()
    maven(url = "https://cursemaven.com") {
        content { includeGroup("curse.maven") }
    }
    maven(url = "https://api.modrinth.com/maven") {
        content { includeGroup("maven.modrinth") }
    }
    // AE2
    maven(url = "https://modmaven.dev/") {
        content { includeGroup("appeng") }
    }
    maven(url = "https://mod-buildcraft.com/maven") {
        content { includeGroup("alexiil.mc.lib") }
    }
    maven(url = "https://raw.githubusercontent.com/Technici4n/Technici4n-maven/master/") {
        content {
            includeGroup("dev.technici4n")
            includeGroup("net.fabricmc.fabric-api")
        }
    }
    maven(url = "https://dvs1.progwml6.com/files/maven") // JEI
    maven(url = "https://maven.architectury.dev")
    maven(url = "https://maven.shedaniel.me") // REI
    maven(url = "https://maven.terraformersmc.com/releases")
    maven(url = "https://thedarkcolour.github.io/KotlinForForge") // KfF
}

dependencies {
    minecraft(libs.minecraft)
    mappings("net.fabricmc:yarn:${libs.versions.fabric.yarn.get()}:v2")
    modApi(libs.bundles.mods.fabric) {
        exclude(module = "fabric-api")
        exclude(module = "fabric-loader")
    }
}

loom {
    runs {
        getByName("client") {
            programArg("--username=Developer")
            vmArg("-Dmixin.debug.export=true")
        }
        getByName("server") {
            runDir = "server"
        }
    }
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    test {
        useJUnitPlatform()
    }
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }
    jar {
        from("LICENSE") {
            rename { "${it}_${project.base.archivesName.get()}" }
        }
    }
}