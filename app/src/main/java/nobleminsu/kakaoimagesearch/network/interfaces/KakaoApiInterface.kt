package nobleminsu.kakaoimagesearch.network.interfaces

import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoApiInterface {
    @GET("/v2/search/image")
    suspend fun getImageSearch(
        @Query("query") query: String,
        @Query("sort") sort: String?,
        @Query("page") page: Int?,
        @Query("size") size: Int?
    ): ImageSearchResponseDto
}