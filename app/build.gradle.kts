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
        "class-dictionary",
        "package-dictionary",
        "obfuscation-dictionary"
    )
}

dependencies {
    implementation(Libraries.kotlin)
    implementation(Libraries.androidX)
}