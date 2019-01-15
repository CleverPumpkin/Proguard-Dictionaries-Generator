object Versions {
    const val kotlin = "1.3.11"
    const val androidX = "1.0.0"
    const val gradlePlugin = "3.3.0"
    const val projectVersion = "1.0.0"
}

object BuildScriptPlugins {
    const val dictgen = "ru.cleverpumpkin.dictgen:plugin:${Versions.projectVersion}"
    const val android = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Plugins {
    const val dictgen = "ru.cleverpumpkin.dictgen"
}

object Libraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val androidX = "androidx.appcompat:appcompat:${Versions.androidX}"
}