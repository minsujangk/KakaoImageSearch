package nobleminsu.kakaoimagesearch.data.sources.image_search

import androidx.paging.DataSource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import nobleminsu.kakaoimagesearch.data.common.BasePositionalDataSource
import nobleminsu.kakaoimagesearch.data.common.DataResult
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDocumentDto
import nobleminsu.kakaoimagesearch.network.apiCall
import nobleminsu.kakaoimagesearch.network.interfaces.KakaoApiInterface

class ImageSearchRemotePagingDataSource @AssistedInject constructor(
    private val kakaoApiInterface: KakaoApiInterface,
    @Assisted private val query: String
) : BasePositionalDataSource<ImageSearchResponseDocumentDto>(PAGE_SIZE) {

    override suspend fun loadPage(page: Int): List<ImageSearchResponseDocumentDto>? {
        val response =
            apiCall {
                kakaoApiInterface.getImageSearch(
                    query,
                    page = page, // initial page is from 1
                    size = PAGE_SIZE
                )
            }

        return when (response) {
            is DataResult.Failure -> null
            is DataResult.Success -> response.value.documents
        }
    }


    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(query: String): ImageSearchRemotePagingDataSource
    }

    class Factory constructor(
        private val assistedFactory: AssistedFactory,
        private val query: String
    ) : DataSource.Factory<Int, ImageSearchResponseDocumentDto>() {
        override fun create(): DataSource<Int, ImageSearchResponseDocumentDto> {
            return assistedFactory.create(query)
        }
    }

    companion object {
        const val PAGE_SIZE = 16
    }
}