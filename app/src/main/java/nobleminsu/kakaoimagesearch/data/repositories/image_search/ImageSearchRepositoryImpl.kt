package nobleminsu.kakaoimagesearch.data.repositories.image_search

import nobleminsu.kakaoimagesearch.data.common.DataResult
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDto
import nobleminsu.kakaoimagesearch.data.sources.image_search.ImageSearchRemoteDataSource
import javax.inject.Inject

class ImageSearchRepositoryImpl @Inject constructor(
    private val imageSearchRemoteDataSource: ImageSearchRemoteDataSource
) : ImageSearchRepository {
    override suspend fun getImageSearch(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): DataResult<ImageSearchResponseDto> {
        // TODO: consider local datasource
        return imageSearchRemoteDataSource.getImageSearch(query, sort, page, size)
    }
}