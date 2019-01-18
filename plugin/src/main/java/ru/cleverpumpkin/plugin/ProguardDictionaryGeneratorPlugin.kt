package ru.cleverpumpkin.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

/**
 * @author Sergey Chuprin
 */
@Suppress("unused")
class ProguardDictionaryGeneratorPlugin : Plugin<Project> {

    private companion object {
        // Task on which we depends on.
        const val TARGET_PROGUARD_TASK = "transformClassesAndResourcesWithProguardFor"

        const val LOG_TAG = "ProguardDictionaryGenerator"
    }

    override fun apply(project: Project) {
        with(project) {
            extensions.add(
                ProguardDictionaryPluginExtension.NAME,
                ProguardDictionaryPluginExtension::class.java
            )

            afterEvaluate {
                setupPlugin()
            }
        }
    }

    private fun Project.setupPlugin() {
        val pluginExtension = findPluginExtension()

        val proguardTask = findProguardTransformTask()
        if (proguardTask == null) {
            logger.lifecycle(
                "$LOG_TAG: proguard task ($TARGET_PROGUARD_TASK) not found"
            )
            return
        }

        if (pluginExtension.dictionaryNames.isEmpty()) {
            logger.lifecycle(
                "$LOG_TAG: you've applied plugin, but didn't specified dictionary names"
            )
            return
        }
        val createGeneratorTask = tasks.create(
            ProguardDictionaryGeneratorTask.NAME,
            ProguardDictionaryGeneratorTask::class.java,
            pluginExtension.dictionaryNames,
            pluginExtension.linesCountInDictionary,
            pluginExtension.minLineLength.coerceAtLeast(1),
            pluginExtension.maxLineLength.coerceAtLeast(1)
        )

        proguardTask.dependsOn(createGeneratorTask)
    }

    private fun Project.findProguardTransformTask(): Task? {
        return tasks.find { task -> task.name.startsWith(TARGET_PROGUARD_TASK) }
    }

    private fun Project.findPluginExtension(): ProguardDictionaryPluginExtension {
        return extensions.getByType(ProguardDictionaryPluginExtension::class.java)
    }

}