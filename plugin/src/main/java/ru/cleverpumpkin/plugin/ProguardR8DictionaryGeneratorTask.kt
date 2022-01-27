package ru.cleverpumpkin.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import javax.inject.Inject

/**
 * Created by Sergey Chuprin on 16/01/2019.
 */
open class ProguardR8DictionaryGeneratorTask @Inject constructor(
    private val dictionaryNames: List<String>,
    private val linesCountInDictionary: Int,
    private val minLineLength: Int,
    private val maxLineLength: Int
) : DefaultTask() {

    companion object {
        const val NAME = "generateProguardDictionaries"
    }

    // All available symbols.
    // Whitespace, punctuation characters, duplicate words,
    // and comments after a # sign are ignored.
    private val alphabet = (('a'..'z') + ('A'..'Z')).joinToString("")

    @TaskAction
    fun run() = dictionaryNames.forEach(::generate)

    private fun generate(dictionaryName: String) {
        val random = ThreadLocalRandom.current()

        val dictionarySet = (0 until linesCountInDictionary)
            .fold(mutableSetOf<String>()) { set, _ ->
                set.apply {
                    val lineLength = random.nextInt(minLineLength, maxLineLength)
                    add(randomizeString(lineLength, alphabet, random))
                }
            }

        File(project.projectDir, "$dictionaryName.txt").run {
            parentFile.mkdirs()
            writeText(dictionarySet.joinToString("\n"))
        }
    }

    private fun randomizeString(lineLength: Int, alphabet: String, random: Random): String {
        return (1..lineLength)
            .map { alphabet[random.nextInt(alphabet.length)] }
            .joinToString("")
    }

}