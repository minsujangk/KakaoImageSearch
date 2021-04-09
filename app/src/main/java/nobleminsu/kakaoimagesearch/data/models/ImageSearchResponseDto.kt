package nobleminsu.kakaoimagesearch.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ImageSearchResponseDto(
    val meta: ImageSearchResponseMeta,
    val documents: List<ImageSearchResponseDocumentDto>
)

data class ImageSearchResponseMeta(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("is_end") val isEnd: Boolean
)

data class ImageSearchResponseDocumentDto(
    val collection: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("image_url") val imageUrl: String,
    val width: Int,
    val height: Int,
    @SerializedName("display_sitename") val displaySiteName: String,
    @SerializedName("doc_url") val docUrl: String,
    val datetime: Date
)