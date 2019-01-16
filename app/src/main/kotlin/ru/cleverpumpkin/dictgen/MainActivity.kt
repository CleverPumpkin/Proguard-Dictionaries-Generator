package ru.cleverpumpkin.dictgen

import android.app.Activity
import android.os.Bundle
import android.util.Log

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val resourceManager = ResourceManager(this)
        val string = resourceManager.getString(R.string.app_name)
        if (string.isNotEmpty()) {
            Log.w("MainActivity", string)
        } else {
            Thread.sleep(10)
        }
    }

}