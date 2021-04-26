package nobleminsu.kakaoimagesearch.data.sources.image_search

import nobleminsu.kakaoimagesearch.data.common.DataResult
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDto
import nobleminsu.kakaoimagesearch.network.apiCall
import nobleminsu.kakaoimagesearch.network.interfaces.KakaoApiInterface
import javax.inject.Inject

class ImageSearchRemoteDataSourceImpl @Inject constructor(private val kakaoApiInterface: KakaoApiInterface) :
    ImageSearchRemoteDataSource {
    override suspend fun getImageSearch(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): DataResult<ImageSearchResponseDto> {
        return apiCall { kakaoApiInterface.getImageSearch(query, sort, page, size) }
    }
}