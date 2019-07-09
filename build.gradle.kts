import com.android.build.gradle.TestedExtension

buildscript {
    repositories {
        jcenter()
        google()
        mavenCentral()
        maven(uri("plugin/pluginRepo"))
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(BuildScriptPlugins.kotlin)
        classpath(BuildScriptPlugins.android)
        classpath(BuildScriptPlugins.dictionariesGenerator)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        mavenCentral()
    }
}

subprojects {
    afterEvaluate {
        extensions
            .findByType(TestedExtension::class.java)
            ?.apply {
                compileSdkVersion(28)
                buildToolsVersion("28.0.3")

                defaultConfig {
                    minSdkVersion(21)
                    targetSdkVersion(28)
                    versionCode = 1
                    versionName = Versions.projectVer
                }

                buildTypes {
                    maybeCreate("release").apply {
                        isDebuggable = false
                        isMinifyEnabled = true
                        signingConfig = signingConfigs.getByName("debug")

                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            File("proguard-rules.pro")
                        )
                    }
                    maybeCreate("debug").apply {
                        isDebuggable = true
                        isMinifyEnabled = false
                    }
                }
                sourceSets["main"].java.srcDir("src/main/kotlin")
            }
    }
}