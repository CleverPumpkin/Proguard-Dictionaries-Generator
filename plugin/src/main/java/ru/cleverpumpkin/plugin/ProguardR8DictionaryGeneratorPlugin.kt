package ru.cleverpumpkin.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

/**
 * Created by Sergey Chuprin on 16/01/2019.
 */
@Suppress("unused")
class ProguardR8DictionaryGeneratorPlugin : Plugin<Project> {

    private companion object {
        const val LOG_TAG = "ProguardR8DictionaryGenerator"
        val TARGET_TASKS = listOf(
            "minifyReleaseWithR8",
            "transformClassesAndResourcesWithR8For",
            "transformClassesAndResourcesWithProguardFor"
        )
    }

    override fun apply(project: Project) {
        with(project) {
            extensions.add(
                ProguardR8DictionaryPluginExtension.NAME,
                ProguardR8DictionaryPluginExtension::class.java
            )
            afterEvaluate {
                setupPlugin()
            }
        }
    }

    private fun Project.setupPlugin() {
        val pluginExtension = findPluginExtension()
        val obfuscationTasks = findObfuscationTasks()

        if (obfuscationTasks.isEmpty()) {
            logger.lifecycle(
                "$LOG_TAG: neither proguard tasks, nor R8 tasks were not found"
            )
            return
        }
        if (pluginExtension.dictionaryNames.isEmpty()) {
            logger.lifecycle(
                "$LOG_TAG: you've applied plugin, but didn't specified dictionary names"
            )
            return
        }

        val taskProvider = tasks.register(
            ProguardR8DictionaryGeneratorTask.NAME,
            ProguardR8DictionaryGeneratorTask::class.java,
            pluginExtension.dictionaryNames,
            pluginExtension.linesCountInDictionary,
            pluginExtension.minLineLength.coerceAtLeast(1),
            pluginExtension.maxLineLength.coerceAtLeast(1)
        )

        obfuscationTasks.forEach { task ->
            logger.lifecycle(
                "$LOG_TAG: applying dictionaries dependency to task ${task.name}"
            )
            task.dependsOn(taskProvider.get())
        }
    }

    private fun Project.findObfuscationTasks(): List<Task> {
        return TARGET_TASKS.flatMap { taskName ->
            tasks.filter { task -> task.name.startsWith(taskName) }
        }
    }

    private fun Project.findPluginExtension(): ProguardR8DictionaryPluginExtension {
        return extensions.getByType(ProguardR8DictionaryPluginExtension::class.java)
    }

}