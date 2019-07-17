# Proguard & R8 Dictionaries Generator

Gradle Plugin that generates randomized dictionaries for proguard

**Tested on Android Gradle Plugin version 3.4.1.**

## How to add
Add to your **root project's** `build.gradle`:

For **Groovy**
```groovy
buildscript {
    repositories {
        maven { 
            url "https://plugins.gradle.org/m2/" 
        }
    }

    dependencies {
        classpath "gradle.plugin.ru.cleverpumpkin.proguard-dictionaries-generator:plugin:1.0.4"
    }
}
```

For **Kotlin DSL**
```kotlin
buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("gradle.plugin.ru.cleverpumpkin.proguard-dictionaries-generator:plugin:1.0.4")
    }
}
```

## Simple configuration
Add to your **app module's** `build.gradle`:

For **Groovy**
```groovy
apply plugin: "ru.cleverpumpkin.proguard-dictionaries-generator"

proguardDictionaries {
    dictionaryNames = [
        "build/class-dictionary",
        "build/package-dictionary",
        "build/obfuscation-dictionary"
    ]
}
```

For **Kotlin DSL**
```kotlin
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

This setup will generate files `class-dictionary.txt`, `package-dictionary.txt` 
and `obfuscation-dictionary.txt` in `build` subdirectory of the module to which the plugin 
is applied. You can specify any directory **relatively to your module's root**. 

These files will be generated from scratch on every build, so you'll have different dictionaries 
for each build.

### Advanced configuration

```groovy
proguardDictionaries {
    dictionaryNames = ["any", "files, "you", "want"]
    minLineLength 10 // Default value: 5
    maxLineLength 30 // Default value: 20
    linesCountInDictionary 100 // Default value: 1000
}
```

### How to use plugin output result

Add to your `proguard-rules.pro` file:

```
-obfuscationdictionary build/obfuscation-dictionary.txt
-classobfuscationdictionary build/class-dictionary.txt
-packageobfuscationdictionary build/package-dictionary.txt
```

### Proguard and R8 support

Plugin runs automatically when android plugin executes Proguard or R8 task:

- `transformClassesAndResourcesWithR8For{BuiltFlavor}{BuiltType}`
- `transformClassesAndResourcesWithProguardFor{BuiltFlavor}{BuiltType}`

You don't have to specify anything special to get it work.


### Dictionary alphabet

Whitespaces, punctuation characters, duplicate words, and comments after a `#` sign are ignored 
in dictionaries by Proguard. So generated file could contains any symbols except listed above.

https://www.guardsquare.com/en/products/proguard/manual/usage#obfuscationoptions

## Authors
Developed by Sergey Chuprin (<gregamer@gmail.com>)<br/>
Supported by Ruslan Arslanov (<arslanov.r.f@gmail.com>)