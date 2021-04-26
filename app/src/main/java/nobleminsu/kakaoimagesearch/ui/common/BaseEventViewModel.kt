package nobleminsu.kakaoimagesearch.ui.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseEventViewModel<T> : ViewModel() {
    protected val eventChannel = Channel<T>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()
}