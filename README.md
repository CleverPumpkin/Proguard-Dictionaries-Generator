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
        classpath "gradle.plugin.ru.cleverpumpkin.proguard-dictionaries-generator:plugin:1.0.5"
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
        classpath("gradle.plugin.ru.cleverpumpkin.proguard-dictionaries-generator:plugin:1.0.5")
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
    linesCountInDictionary 50000 // Default value: 30000
}
```

### How to use plugin output result

Add to your `proguard-rules.pro` file:

```
-obfuscationdictionary build/obfuscation-dictionary.txt
-classobfuscationdictionary build/class-dictionary.txt
-packageobfuscationdictionary build/package-dictionary.txt
```

## Program classes number issue

**Important:** if the final number of classes in `*.apk` after code shrinking by Proguard / R8 
**exceeds the `linesCountInDictionary` value**, then all remaining classes **will be named by 
default** – starting with first alphabet letters.

Thus, the `linesCountInDictionary` value must be greater than a number of program classes after 
the code shrinking. 

The default size of the dictionary is `30000` lines.

You can find the number of classes in your `*.apk` by following next steps:

1. While performing build in Android Studio, find the last **Optimizing** step in the console,
e. g. "Optimizing (Step 5/5)".
2. Check the value in line "Final number of program classes".

Or:

1. Drag-n-drop an `*.apk` file into Android Studio (or open it via menu **Build** -> 
**Analyze APK...**).
3. In APK Analyzer window select `classes.dex` file. Check the value in the line 
"This dex file defines X classes..."
4. If more than one `*.dex` files present in APK, select each of them and sum up all classes numbers.

Finally, set the value of `linesCountInDictionary` slightly greater than the resulting number 
of program classes to keep an extra space for application grow (e. g. if program contains 
9802 classes, you can set the value 12000).


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