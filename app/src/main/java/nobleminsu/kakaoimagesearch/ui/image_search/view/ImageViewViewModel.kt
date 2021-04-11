package nobleminsu.kakaoimagesearch.ui.image_search.view

import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDocumentDto

class ImageViewViewModel @AssistedInject constructor(
    @Assisted documentDto: ImageSearchResponseDocumentDto?
) : ViewModel() {
    val documentDtoLiveData = liveData { documentDto?.let { emit(it) } }
    val isImageInfoShown = MutableLiveData(true)
    var lastJob: Job? = null

    @AssistedFactory
    interface Factory {
        fun create(documentDto: ImageSearchResponseDocumentDto?): ImageViewViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            documentDto: ImageSearchResponseDocumentDto?
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(documentDto) as T
            }
        }
    }

    fun hideViewAfter(timeMillis: Long) {
        cancelHideViewJob()
        viewModelScope.launch {
            delay(timeMillis)
            isImageInfoShown.value = false
        }.also { lastJob = it }
    }

    fun cancelHideViewJob() {
        lastJob?.cancel()
    }
}