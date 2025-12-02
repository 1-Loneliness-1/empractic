package org.example

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import org.example.utils.TimeConverter

class AppStartTimeDelegate : ReadOnlyProperty<Any?, Long> {
    private var cachedTime: Long? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): Long {
        return cachedTime ?: System.currentTimeMillis().also { currentTime ->
            cachedTime = currentTime
            startLogging()
        }
    }

    private fun startLogging() {
        GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                delay(TIME_OF_DELAY_FOR_APP_START_TIME_PRINT)
                val currentTime = System.currentTimeMillis()
                println("Time from start: ${TimeConverter.durationToHhmmssFormat(currentTime - (cachedTime ?: 0))}")
            }
        }
    }

    companion object {
        private const val TIME_OF_DELAY_FOR_APP_START_TIME_PRINT = 3000L
    }
}