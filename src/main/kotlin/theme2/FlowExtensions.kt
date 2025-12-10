package org.example.theme2

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.throttleFirst(interval: Long = 0L): Flow<T> = flow {
    var lastEmitTime: Long? = null

    collect { value ->
        val currentTime = System.currentTimeMillis()
        if (lastEmitTime == null || currentTime - lastEmitTime!! >= interval) {
            lastEmitTime = currentTime
            emit(value)
        }
    }
}

fun <T> Flow<T>.throttleLatest(interval: Long = 0L): Flow<T> = channelFlow {
    var timer: Job? = null
    var lastValue: T? = null

    collect { value ->
        lastValue = value

        if (timer == null) {
            timer = launch {
                delay(interval)
                lastValue?.let {
                    send(it)
                    lastValue = null
                }
            }
            timer = null
        }
    }
}