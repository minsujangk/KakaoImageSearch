package nobleminsu.kakaoimagesearch.ui.main

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import nobleminsu.kakaoimagesearch.data.repositories.main.MainRepository
import nobleminsu.kakaoimagesearch.domain.image_search.GetImageSearchUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MainRepository,
    private val imageSearchUseCase: GetImageSearchUseCase
) : ViewModel() {
    init {
        imageSearchUseCase.errorHandler = {
            Timber.e(it.toString())
        }
    }

    val imageSearchResponse = liveData {
        imageSearchUseCase("apple")?.also { emit(it) }
    }
}