package org.example

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.theme2.throttleFirst
import org.example.theme2.throttleLatest

fun main() {
    val customCoroutineScope = CoroutineScope(Dispatchers.Default +
            SupervisorJob() +
            CoroutineExceptionHandler { _, throwable ->
        println("Coroutine fell because of ${throwable.message}")
    })

    val jobWithFlows = customCoroutineScope.launch {
        flow {
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

        flow {
            val someValues = listOf(1, 2, 22, 11, 222, 7546)
            someValues.forEach { value ->
                emit(value)
                delay(1000)
            }
        }
            .throttleLatest(3000)
            .collect { value ->
                println("Last value in period is $value")
            }
    }

    runBlocking {
        jobWithFlows.join()
    }

    customCoroutineScope.cancel()
}