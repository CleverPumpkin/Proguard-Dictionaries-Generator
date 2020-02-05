
import LibrariesVersions.ANDROID_X_VERSION
import LibrariesVersions.GRADLE_PLUGIN_VERSION
import LibrariesVersions.KOTLIN_VERSION
import PluginVersions.SAMPLE
import PluginVersions.UPLOAD

object LibrariesVersions {
    const val KOTLIN_VERSION = "1.3.61"
    const val ANDROID_X_VERSION = "1.0.0"
    const val GRADLE_PLUGIN_VERSION = "3.6.0-rc02"
}

object PluginVersions {
    /**
     * The constant used to specify the version of a plugin which will be uploaded to
     * the Gradle Plugin portal when "publishPlugins" task runs.
     */
    const val UPLOAD = "1.0.7"

    /**
     * The constant used to specify the version of a plugin which is applied to the Sample app.
     * Usually it equals [UPLOAD] constant and differs only when a new plugin version is about
     * to be uploaded.
     */
    const val SAMPLE = "1.0.7"
}

object BuildScriptPlugins {
    const val android = "com.android.tools.build:gradle:$GRADLE_PLUGIN_VERSION"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
    const val dictionariesGenerator =
        "gradle.plugin.ru.cleverpumpkin.proguard-dictionaries-generator:plugin:$SAMPLE"
}

object Plugins {
    const val dictionariesGenerator = "ru.cleverpumpkin.proguard-dictionaries-generator"
}

object Libraries {
    const val androidX = "androidx.appcompat:appcompat:$ANDROID_X_VERSION"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION"
}