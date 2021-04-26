package nobleminsu.kakaoimagesearch.data.repositories.image_search

import nobleminsu.kakaoimagesearch.data.common.DataResult
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDto

interface ImageSearchRepository {
    suspend fun getImageSearch(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): DataResult<ImageSearchResponseDto>
}