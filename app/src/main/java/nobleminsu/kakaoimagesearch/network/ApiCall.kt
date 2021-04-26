package nobleminsu.kakaoimagesearch.network

import java.lang.Exception
import nobleminsu.kakaoimagesearch.data.common.DataResult

suspend fun <T> apiCall(call: suspend () -> T): DataResult<T> {
    return try {
        DataResult.Success(call())
    } catch (e: Exception) {
        DataResult.Failure(e)
    }
}
