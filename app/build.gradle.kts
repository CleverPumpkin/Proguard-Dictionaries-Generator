plugins {
    id("com.android.application")
    id("kotlin-android")
    id(Plugins.proguardDictionaries)
}

android {
    defaultConfig {
        applicationId = "ru.cleverpumpkin.dictgen.app"
    }
}

proguardDictionaries {
    dictionaryNames = listOf(
        "build/class-dictionary",
        "build/package-dictionary",
        "build/obfuscation-dictionary"
    )
}

dependencies {
    implementation(Libraries.kotlin)
    implementation(Libraries.androidX)
}