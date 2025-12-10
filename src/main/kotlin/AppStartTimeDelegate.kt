package org.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import org.example.utils.TimeConverter

class AppStartTimeDelegate(
    private val coroutineScope: CoroutineScope
) : ReadOnlyProperty<Any?, String> {
    private var cachedTime: String? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return cachedTime ?: TimeConverter.timestampToHhmmss(System.currentTimeMillis()).also { currentTime ->
            cachedTime = currentTime
            startLogging()
        }
    }

    private fun startLogging() {
        coroutineScope.launch {
            while (true) {
                delay(TIME_OF_DELAY_FOR_APP_START_TIME_PRINT_IN_MILLIS)
                println("Cached program startup time: ${cachedTime ?: 0}")
            }
        }
    }

    companion object {
        private const val TIME_OF_DELAY_FOR_APP_START_TIME_PRINT_IN_MILLIS = 3000L
    }
}