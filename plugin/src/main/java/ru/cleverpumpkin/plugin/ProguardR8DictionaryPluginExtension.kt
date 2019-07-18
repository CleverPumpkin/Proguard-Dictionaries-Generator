package ru.cleverpumpkin.plugin

/**
 * Created by Sergey Chuprin on 16/01/2019.
 */
open class ProguardR8DictionaryPluginExtension {

    companion object {
        internal const val NAME = "proguardDictionaries"

        const val DEFAULT_LINES_COUNT = 30000
        const val DEFAULT_MIN_LINE_LENGTH = 10
        const val DEFAULT_MAX_LINE_LENGTH = 30
    }

    var dictionaryNames = listOf<String>()
    var minLineLength: Int = DEFAULT_MIN_LINE_LENGTH
    var maxLineLength: Int = DEFAULT_MAX_LINE_LENGTH
    var linesCountInDictionary: Int = DEFAULT_LINES_COUNT

}