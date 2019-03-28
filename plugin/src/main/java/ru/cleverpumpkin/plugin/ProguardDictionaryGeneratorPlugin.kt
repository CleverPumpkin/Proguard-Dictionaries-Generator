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
        // Tasks on which we depends on.
        const val TARGET_R8_TASK = "transformClassesAndResourcesWithR8For"
        const val TARGET_PROGUARD_TASK = "transformClassesAndResourcesWithProguardFor"

        const val LOG_TAG = "ProguardDictionaryGenerator"

        val PROP_R8 = listOf("android.enableR8", "android.enableR8.fullMode")
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

        val targetTaskName = getTargetTaskName()
        val proguardTask = findTransformTask(targetTaskName)
        if (proguardTask == null) {
            logger.lifecycle(
                "$LOG_TAG: proguard task ($targetTaskName) not found"
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

    private fun Project.getTargetTaskName(): String {
        return if (isR8Enabled()) TARGET_R8_TASK else TARGET_PROGUARD_TASK
    }

    private fun Project.isR8Enabled(): Boolean {
        return properties.any { (key) -> key in PROP_R8 }
    }

    private fun Project.findTransformTask(taskName: String): Task? {
        return tasks.find { task -> task.name.startsWith(taskName) }
    }

    private fun Project.findPluginExtension(): ProguardDictionaryPluginExtension {
        return extensions.getByType(ProguardDictionaryPluginExtension::class.java)
    }

}