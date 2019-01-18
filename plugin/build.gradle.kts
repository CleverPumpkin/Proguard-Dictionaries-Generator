plugins {
    id("com.gradle.plugin-publish") version "0.10.0"
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

group = Plugins.proguardDictionaries

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
    description = "This is an Android gradle plugin that allows you to generate " +
            "randomized dictionaries for proguard"
    tags = listOf("android", "proguard", "generator", "dictionary", "obfuscation", "minification")
}

// Create plugin itself.
gradlePlugin {
    plugins {
        create("plugin") {
            version = Versions.projectVer
            id = Plugins.proguardDictionaries
            displayName = "Proguard Dictionaries Generator Plugin"
            implementationClass = "ru.cleverpumpkin.plugin.ProguardDictionaryGeneratorPlugin"
        }
    }
}

tasks.withType(Javadoc::class.java) {
    enabled = false
}