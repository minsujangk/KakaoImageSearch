package nobleminsu.kakaoimagesearch.ui.image_search

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import nobleminsu.kakaoimagesearch.data.sources.image_search.ImageSearchRemotePagingSource
import javax.inject.Inject

@HiltViewModel
class ImageSearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val imageSearchRemotePagingSourceFactory: ImageSearchRemotePagingSource.Factory
) : ViewModel() {
    val query = MutableLiveData("kakao")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchDocuments =
        query.asFlow().flatMapLatest {
            Pager(PagingConfig(pageSize = ImageSearchRemotePagingSource.PAGE_SIZE)) {
                imageSearchRemotePagingSourceFactory.create(it)
            }.flow.cachedIn(viewModelScope)
        }
}