package org.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object AppLevelScope: CoroutineScope {
    override val coroutineContext = Dispatchers.Default + SupervisorJob()
}