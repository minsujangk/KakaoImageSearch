package nobleminsu.kakaoimagesearch.domain.image_search

import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDto
import nobleminsu.kakaoimagesearch.data.repositories.image_search.ImageSearchRepository
import nobleminsu.kakaoimagesearch.domain.BaseUseCase
import nobleminsu.kakaoimagesearch.domain.handle
import javax.inject.Inject

class GetImageSearchUseCase @Inject constructor(
    private val imageSearchRepository: ImageSearchRepository
) : BaseUseCase() {
    suspend operator fun invoke(
        query: String,
        sort: String? = null,
        page: Int? = null,
        size: Int? = null
    ): ImageSearchResponseDto? {
        return handle { imageSearchRepository.getImageSearch(query, sort, page, size) }
    }
}