package org.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.example.theme2.throttleFirst

fun main() {
    val customCoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    customCoroutineScope.launch {
        val testFlow = flow {
            val someValues = listOf(1, 2, 22, 11, 222, 7546)
            someValues.forEach { value ->
                emit(value)
                delay(1000)
            }
        }
            .throttleFirst(3000)
            .collect { value ->
                println("First value in period is $value")
            }
    }

    Thread.sleep(10000)
    customCoroutineScope.cancel()
}