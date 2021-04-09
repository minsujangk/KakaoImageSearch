package nobleminsu.kakaoimagesearch.data.common

import java.lang.Exception

sealed class DataResult<out T> {
    class Success<out T>(val value: T) : DataResult<T>()
    class Failure(val cause: Exception) : DataResult<Nothing>()
}