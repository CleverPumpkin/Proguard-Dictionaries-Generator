plugins {
    id("com.android.application")
    id("kotlin-android")
    id(Plugins.dictgen)
}

android {
    defaultConfig {
        applicationId = "ru.cleverpumpkin.dictgen.app"
    }
}

proguardDictionaries {
    dictionaryNames = listOf("class-dictionary", "method-dictionary")
}

dependencies {
    implementation(Libraries.kotlin)
    implementation(Libraries.androidX)
}