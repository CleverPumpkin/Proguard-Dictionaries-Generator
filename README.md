Gradle Plugin that generates randomized dictionaries for proguard

**Tested on latest (3.3) Android Gradle Plugin.**

# How to add
In your root project's `build.gradle`
```
buildscript {
    repositories {
    gradlePluginPortal()

    dependencies {
        classpath "gradle.plugin.ru.cleverpumpkin.proguard-dictionaries-generator:plugin:1.0.1"
    }
}
```
# Simple configuration
In your app module's `build.gradle`

For Groovy:
```
apply plugin: "ru.cleverpumpkin.proguard-dictionaries-generator"

proguardDictionaries {
    dictionaryNames = [
                       "build/class-dictionary",
                       "build/package-dictionary",
                       "build/obfuscation-dictionary"
                       ]
}
```
For Kotlin DSL:
```
plugins {
    id("ru.cleverpumpkin.proguard-dictionaries-generator")
}
proguardDictionaries {
    dictionaryNames = listOf(
         "build/class-dictionary",
         "build/package-dictionary",
         "build/obfuscation-dictionary"
      )
}
```
This simple setup will generate file `class-dictionary.txt`
in `build` folder of module on which plugin applied.
You can specify any directory relatively to your module's root.

These files are generated on each build
(so you'll get different dictionary on each build)

### Advanced configuration
```
proguardDictionaries {
    dictionaryNames = ["any", "files, "you", "want"]
    minLineLength 10 // Default value: 5
    maxLineLength 30 // Default value: 20
    linesCountInDictionary 100 // Default value: 1000
}
```

### How to use plugin output result
In your `proguard-rules.pro` file
```
-obfuscationdictionary build/obfuscation-dictionary.txt
-classobfuscationdictionary build/class-dictionary.txt
-packageobfuscationdictionary build/package-dictionary.txt
```

### Additional info
Plugin runs automatically when android plugin executes proguard task,
so you don't need anything special to get it work.

White space, punctuation characters, duplicate words,
and comments after a # sign are ignored in dictionaries by Proguard.
So generated file could contains any symbols except above.

https://www.guardsquare.com/en/products/proguard/manual/usage#obfuscationoptions

# Developed by 
Sergey Chuprin - <gregamer@gmail.com>