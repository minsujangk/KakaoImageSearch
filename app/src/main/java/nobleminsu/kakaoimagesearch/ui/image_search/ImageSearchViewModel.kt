package nobleminsu.kakaoimagesearch.ui.image_search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import nobleminsu.kakaoimagesearch.data.sources.image_search.ImageSearchRemotePagingSource
import javax.inject.Inject

@HiltViewModel
class ImageSearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val imageSearchRemotePagingSource: ImageSearchRemotePagingSource
) : ViewModel() {
    val searchDocuments =
        Pager(PagingConfig(pageSize = ImageSearchRemotePagingSource.PAGE_SIZE)) {
            imageSearchRemotePagingSource.apply { query = "pop" }
        }.flow.cachedIn(viewModelScope)
}