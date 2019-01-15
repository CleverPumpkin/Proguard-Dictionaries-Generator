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

group = Plugins.dictgen

// Upload archive to rootProject/plugin/badgeRepo folder to test plugin locale.
tasks.named<Upload>("uploadArchives") {
    repositories.withGroovyBuilder {
        "mavenDeployer" {
            "repository"("url" to "file://pluginRepo")
        }
    }
}

// Add info for publication to plugin portal.
pluginBundle {
    // TODO
    vcsUrl = "TODO"
    website = "TODO"
    description = "This is an Android gradle plugin that allows you to generate " +
            "dictionary files for proguard"
    tags = listOf("android", "proguard", "generator", "dictionary", "obfuscation", "minification")
}

// Create plugin itself.
gradlePlugin {
    plugins {
        create("dictgenPlugin") {
            id = Plugins.dictgen
            version = Versions.projectVersion
            displayName = "Proguard Dictionaries Generator "
            implementationClass = "ru.cleverpumpkin.dictgen.ProguardDictionaryGeneratorPlugin"
        }
    }
}

tasks.withType(Javadoc::class.java) {
    enabled = false
}