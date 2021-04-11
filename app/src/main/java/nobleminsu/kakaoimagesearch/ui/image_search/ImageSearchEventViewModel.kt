package nobleminsu.kakaoimagesearch.ui.image_search

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nobleminsu.kakaoimagesearch.data.models.ImageSearchResponseDocumentDto
import nobleminsu.kakaoimagesearch.ui.common.BaseEventViewModel
import javax.inject.Inject

@HiltViewModel
class ImageSearchEventViewModel @Inject constructor() :
    BaseEventViewModel<ImageSearchEventViewModel.Event>() {
    sealed class Event {
        class ShowImage(val documentDto: ImageSearchResponseDocumentDto) : Event()
    }

    fun clickImage(documentDto: ImageSearchResponseDocumentDto) {
        viewModelScope.launch {
            eventChannel.send(Event.ShowImage(documentDto))
        }
    }
}