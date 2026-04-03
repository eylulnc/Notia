package com.github.eylulnc.notia.util

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class FlowAdapter<T>(
    private val flow: Flow<T>
) {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun subscribe(
        onEach: (T) -> Unit,
        onComplete: () -> Unit,
        onThrow: (Throwable) -> Unit
    ): Cancellable {
        val job = scope.launch {
            try {
                flow.collect { onEach(it) }
                onComplete()
            } catch (t: Throwable) {
                onThrow(t)
            }
        }
        return Cancellable { job.cancel() }
    }
}

fun interface Cancellable {
    fun cancel()
}

// Non-extension helper for easier Swift access
class FlowHelper {
    fun <T> wrap(flow: Flow<T>): FlowAdapter<T> = FlowAdapter(flow)
}
