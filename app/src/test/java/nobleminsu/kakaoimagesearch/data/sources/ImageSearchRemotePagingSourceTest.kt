package nobleminsu.kakaoimagesearch.data.sources

import androidx.paging.PagingSource
import kotlinx.coroutines.runBlocking
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDocumentDto
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDto
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseMeta
import nobleminsu.kakaoimagesearch.data.sources.image_search.ImageSearchRemotePagingSource
import nobleminsu.kakaoimagesearch.network.interfaces.KakaoApiInterface
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.runners.MockitoJUnitRunner
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
                        size = ImageSearchRemotePagingSource.PAGE_SIZE
                    )
                }.doReturn(
                    ImageSearchResponseDto(
                        ImageSearchResponseMeta(mockDocuments.count(), 1, false),
                        mockDocuments
                    )
                )
            }

            val pagingSource = ImageSearchRemotePagingSource(mockApi, query)

            val loaded = pagingSource.load(
                PagingSource.LoadParams.Append(
                    1,
                    ImageSearchRemotePagingSource.PAGE_SIZE,
                    false
                )
            )


            Assert.assertTrue(loaded is PagingSource.LoadResult.Page)
            Assert.assertTrue((loaded as PagingSource.LoadResult.Page).data.mapIndexed { index, imageSearchResponseDocumentDto ->
                index to imageSearchResponseDocumentDto
            }.all { mockDocuments[it.first] == it.second })
        }
    }
}