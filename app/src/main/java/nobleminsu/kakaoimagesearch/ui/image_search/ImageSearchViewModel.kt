package nobleminsu.kakaoimagesearch.ui.image_search

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import nobleminsu.kakaoimagesearch.data.sources.image_search.ImageSearchRemotePagingDataSource
import javax.inject.Inject

@HiltViewModel
class ImageSearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val imageSearchRemotePagingDataSourceFactory: ImageSearchRemotePagingDataSource.AssistedFactory
) : ViewModel() {
    val query = MutableLiveData("kakao")

    val searchedDocuments =
        query.switchMap {
            LivePagedListBuilder(
                ImageSearchRemotePagingDataSource.Factory(
                    imageSearchRemotePagingDataSourceFactory,
                    it
                ),
                PagedList.Config.Builder().setPageSize(ImageSearchRemotePagingDataSource.PAGE_SIZE)
                    .setEnablePlaceholders(false).build()
            ).build()
        }

    val tags = searchedDocuments.map {
        it.map { it.collection }.distinct()
    }
}