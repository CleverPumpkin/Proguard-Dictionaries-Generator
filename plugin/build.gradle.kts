plugins {
    id("com.gradle.plugin-publish") version "0.10.1"
    `kotlin-dsl`
    maven
}

repositories {
    jcenter()
    google()
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation(BuildScriptPlugins.android)
}

group = Plugins.dictionariesGenerator

// Upload archive to rootProject/plugin/badgeRepo folder to test plugin locale.
// Use "uploadArchives" task.
tasks.named<Upload>("uploadArchives") {
    repositories.withGroovyBuilder {
        "mavenDeployer" {
            "repository"("url" to "file://pluginRepo")
        }
    }
}

// Add info for publication to plugin portal.
pluginBundle {
    vcsUrl = "https://github.com/CleverPumpkin/Proguard-Dictionaries-Generator"
    website = "https://github.com/CleverPumpkin/Proguard-Dictionaries-Generator"

    description = "This is an Android gradle plugin that allows you to generate randomized " +
            "dictionaries for Proguard and R8"

    tags = listOf(
        "android",
        "proguard",
        "R8",
        "generator",
        "dictionary",
        "obfuscation",
        "shrinking",
        "minification"
    )
}

// Create plugin itself.
gradlePlugin {
    plugins {
        create("plugin") {
            version = PluginVersions.UPLOAD
            id = Plugins.dictionariesGenerator
            displayName = "Proguard R8 Dictionaries Generator Plugin"
            implementationClass = "ru.cleverpumpkin.plugin.ProguardR8DictionaryGeneratorPlugin"
        }
    }
}

tasks.withType(Javadoc::class.java) {
    enabled = false
}