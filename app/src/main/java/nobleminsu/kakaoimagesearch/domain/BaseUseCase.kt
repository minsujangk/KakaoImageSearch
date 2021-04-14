package nobleminsu.kakaoimagesearch.domain


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import nobleminsu.kakaoimagesearch.data.common.DataResult
import kotlin.coroutines.coroutineContext

interface UseCase {
    val errorHandler: ((Exception) -> Unit)?

    fun onError(e: Exception) {
        errorHandler?.invoke(e)
    }
}

abstract class BaseUseCase : UseCase {
    override var errorHandler: ((Exception) -> Unit)? = null
}

suspend fun <T> UseCase.handleDataResultAsync(caller: suspend () -> DataResult<T>): Deferred<T?> {
    return CoroutineScope(coroutineContext).async {
        caller.invoke().let {
            when (it) {
                is DataResult.Success -> it.value
                is DataResult.Failure -> {
                    onError(it.cause)
                    null
                }
            }
        }
    }
}

suspend fun <T> UseCase.handle(caller: suspend () -> DataResult<T>): T? {
    return handleDataResultAsync(caller).await()
}

suspend fun <T> UseCase.handle(vararg callers: (suspend () -> DataResult<T>)?): List<T?> {
    return callers.map { if (it != null) handleDataResultAsync(it) else it }.map { it?.await() }
}

suspend fun <T1, T2> UseCase.handle(
    caller1: suspend () -> DataResult<T1>,
    caller2: suspend () -> DataResult<T2>
): Pair<T1?, T2?> {
    return (handleDataResultAsync(caller1).await() to
            handleDataResultAsync(caller2).await())
}

suspend fun <T1, T2, T3> UseCase.handle(
    caller1: suspend () -> DataResult<T1>,
    caller2: suspend () -> DataResult<T2>,
    caller3: suspend () -> DataResult<T3>
): Triple<T1?, T2?, T3?> {
    val v1 = handleDataResultAsync(caller1)
    val v2 = handleDataResultAsync(caller2)
    val v3 = handleDataResultAsync(caller3)
    return Triple(v1.await(), v2.await(), v3.await())
}