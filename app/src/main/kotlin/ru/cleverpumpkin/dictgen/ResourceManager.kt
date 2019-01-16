package ru.cleverpumpkin.dictgen

import android.content.Context
import androidx.core.content.ContextCompat

/**
 * @author Sergey Chuprin
 */
class ResourceManager(
    private val context: Context
) {

    fun getAccentColor(): Int = ContextCompat.getColor(context, R.color.colorAccent)

    fun getPrimaryColor(): Int = ContextCompat.getColor(context, R.color.colorPrimary)

    fun getString(stringRes: Int): String = context.getString(stringRes)

    fun getString(stringResId: Int, vararg args: Any): String {
        return context.getString(stringResId, *args)
    }

}