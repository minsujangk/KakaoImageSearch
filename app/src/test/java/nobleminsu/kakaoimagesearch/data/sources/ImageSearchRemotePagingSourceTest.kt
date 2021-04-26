package nobleminsu.kakaoimagesearch.data.sources

import kotlinx.coroutines.runBlocking
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDocumentDto
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDto
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseMeta
import nobleminsu.kakaoimagesearch.data.sources.image_search.ImageSearchRemotePagingDataSource
import nobleminsu.kakaoimagesearch.network.interfaces.KakaoApiInterface
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ImageSearchRemotePagingSourceTest {
    @Test
    fun pagingSource() {
        runBlocking {
            val query = "kakao"
            val mockDocuments = (1..15).map {
                ImageSearchResponseDocumentDto(
                    "collection$it",
                    "https://thumbnail.$it",
                    "https://image.$it",
                    30,
                    40,
                    "site$it",
                    "https://doc.$it",
                    Date()
                )
            }
            val mockApi = mock<KakaoApiInterface> {
                onBlocking {
                    getImageSearch(
                        query,
                        page = 1,
                        size = ImageSearchRemotePagingDataSource.PAGE_SIZE
                    )
                }.doReturn(
                    ImageSearchResponseDto(
                        ImageSearchResponseMeta(mockDocuments.count(), 1, false),
                        mockDocuments
                    )
                )
            }

            val pagingSource = ImageSearchRemotePagingDataSource(mockApi, query)

            val loaded = pagingSource.loadPage(0)

            Assert.assertTrue(loaded!!.mapIndexed { index, imageSearchResponseDocumentDto ->
                index to imageSearchResponseDocumentDto
            }.all { mockDocuments[it.first] == it.second })
        }
    }
}