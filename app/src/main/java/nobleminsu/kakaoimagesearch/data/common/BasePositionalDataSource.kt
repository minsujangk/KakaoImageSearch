package nobleminsu.kakaoimagesearch.data.common

import androidx.paging.PositionalDataSource
import kotlinx.coroutines.runBlocking

abstract class BasePositionalDataSource<T : Any>(var pageSize: Int) : PositionalDataSource<T>() {
    final override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        val posStart = params.startPosition
        val posEnd = params.startPosition + params.loadSize

        runBlocking {
            val outList = loadData(posStart, posEnd) ?: return@runBlocking

            callback.onResult(
                outList.drop(posStart % pageSize)
            )
        }
    }

    final override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        val posStart = params.requestedStartPosition
        val posEnd = params.requestedStartPosition + params.requestedLoadSize

        runBlocking {
            val outList = loadData(posStart, posEnd) ?: return@runBlocking

            callback.onResult(
                outList.drop(posStart % pageSize),
                posStart
            )
        }
    }

    protected open suspend fun loadData(posStart: Int, posEnd: Int): List<T>? {
        val pageStart = posStart / pageSize
        val pageEnd = posEnd / pageSize

        return (pageStart..pageEnd).mapNotNull { loadPage(it) }.flatten()
            .run { if (isNullOrEmpty()) null else this } // if there are no data, return null
    }

    abstract suspend fun loadPage(page: Int): List<T>?
}