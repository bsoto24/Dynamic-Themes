package com.openlab.dinamicthemes

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    fun getPreferences(context: Context): SharedPreferences = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    operator fun SharedPreferences.set(key: String, value: Any?) = when (value) {
        is String? -> edit({ it.putString(key, value) })
        is Int -> edit({ it.putInt(key, value) })
        is Boolean -> edit({ it.putBoolean(key, value) })
        is Float -> edit({ it.putFloat(key, value) })
        is Long -> edit({ it.putLong(key, value) })
        else -> throw UnsupportedOperationException()
    }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? =
            when (T::class) {
                String::class -> getString(key, defaultValue as? String ?: "") as T?
                Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
                Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
                Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
                Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
                else -> throw UnsupportedOperationException()
            }
}