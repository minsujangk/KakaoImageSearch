package nobleminsu.kakaoimagesearch.ui.image_search.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDocumentDto

class ImageViewViewModel @AssistedInject constructor(
    @Assisted documentDto: ImageSearchResponseDocumentDto?
) : ViewModel() {
    val documentDtoLiveData = liveData { documentDto?.let { emit(it) } }

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
}