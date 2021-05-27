package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.viewmodel.BaseViewModel
import com.example.core.domain.model.Media
import com.example.core.domain.usecase.MediaUseCase
import kotlinx.coroutines.flow.collectLatest

class CatalogueDetailViewModel(
    private val mediaUseCase: MediaUseCase
) :
    BaseViewModel() {
    private val _media = MutableLiveData<Media>()
    val media: LiveData<Media> get() = _media

    fun getCatalogueDetail(mediaId: Int) {
        launchViewModelScope {
            mediaUseCase.getMedia(mediaId).collectLatest {
                _media.postValue(it)
            }
        }
    }

    fun toggleFavorite() {
        launchViewModelScope {
            media.value?.let {
                mediaUseCase.setMediaFavorite(it, it.isFavorite xor true)
            }
        }
    }
}