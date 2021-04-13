package nobleminsu.kakaoimagesearch.ui.image_search

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        listOf(COLLECTION_ALL) + it.map { it.collection }.distinct()
    }

    private val _selectedCollection = MutableLiveData(emptyList<String>())
    // TODO: 태그가 바뀌면 all로 초기화
    //        tags.switchMap { MutableLiveData(emptyList<String>()) } as MutableLiveData

    val selectedCollection = _selectedCollection as LiveData<List<String>>

    fun toggleCollection(collection: String) {
        viewModelScope.launch {
            _selectedCollection.value?.also { curSelection ->
                when (collection) {
                    COLLECTION_ALL -> {
                        if (curSelection.isNotEmpty()) _selectedCollection.value = emptyList()
                    }
                    else -> {
                        if (collection in curSelection) {
                            _selectedCollection.value =
                                curSelection.toMutableList().apply { remove(collection) }
                        } else {
                            _selectedCollection.value =
                                curSelection.toMutableList().apply { add(collection) }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val COLLECTION_ALL = "all"
    }
}