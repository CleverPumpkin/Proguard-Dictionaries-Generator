Gradle Plugin that generates randomized dictionaries for proguard

**Tested on latest (3.3) Android Gradle Plugin.**

# How to add
In your root project's `build.gradle`
```
buildscript {
    repositories {
    gradlePluginPortal()

    dependencies {
        // TODO: Set valid plugin id.
        classpath "gradle.plugin.ru.cleverpumpkin.proguard-dictionaries-generator:plugin:1.0.0"
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
                       "class-dictionary",
                       "package-dictionary",
                       "obfuscation-dictionary"
                       ]
}
```
For Kotlin DSL:
```
plugins {
    id("ru.cleverpumpkin.proguard-dictionaries-generator")
}
proguardDictionaries {
    dictionaryNames =  dictionaryNames = listOf(
         "class-dictionary",
         "package-dictionary",
         "obfuscation-dictionary"
      )
}
```
This simple setup will generate file `class-dictionary.txt`
in `build` folder of module on which plugin applied.
This file generated on each build
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