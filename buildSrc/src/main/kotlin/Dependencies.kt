import Versions.androidXVer
import Versions.gradlePluginVer
import Versions.kotlinVer
import Versions.projectVer

object Versions {
    const val kotlinVer = "1.3.11"
    const val projectVer = "1.0.1"
    const val androidXVer = "1.0.0"
    const val gradlePluginVer = "3.3.0"
}

object BuildScriptPlugins {
    const val android = "com.android.tools.build:gradle:$gradlePluginVer"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVer"
    const val proguardDictionaries =
        "ru.cleverpumpkin.proguard-dictionaries-generator:plugin:$projectVer"
}

object Plugins {
    const val proguardDictionaries = "ru.cleverpumpkin.proguard-dictionaries-generator"
}

object Libraries {
    const val androidX = "androidx.appcompat:appcompat:$androidXVer"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVer"
}