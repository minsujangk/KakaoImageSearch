package nobleminsu.kakaoimagesearch.data.sources.image_search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import nobleminsu.kakaoimagesearch.data.common.DataResult
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDocumentDto
import nobleminsu.kakaoimagesearch.network.apiCall
import nobleminsu.kakaoimagesearch.network.interfaces.KakaoApiInterface
import javax.inject.Inject

// TODO: AssistedInject
class ImageSearchRemotePagingSource @Inject constructor(
    private val kakaoApiInterface: KakaoApiInterface,
) : PagingSource<Int, ImageSearchResponseDocumentDto>() {
    var query: String = "kakao"

    override fun getRefreshKey(state: PagingState<Int, ImageSearchResponseDocumentDto>): Int? {
        return state.anchorPosition
    }

    // TODO: total count도 받아오기

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageSearchResponseDocumentDto> {
        val pageToLoad = params.key ?: 1

        return when (val response =
            apiCall {
                kakaoApiInterface.getImageSearch(
                    query,
                    page = pageToLoad,
                    size = PAGE_SIZE
                )
            }) {
            is DataResult.Success -> LoadResult.Page(
                response.value.documents,
                prevKey = if (pageToLoad == 1) null else pageToLoad - 1,
                nextKey = if (response.value.meta.isEnd) null else pageToLoad + 1
            )
            is DataResult.Failure -> LoadResult.Error(response.cause)
        }
    }

    companion object {
        const val PAGE_SIZE = 16
    }
}