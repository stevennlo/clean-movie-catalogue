package com.example.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.viewmodel.BaseViewModel
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaFormat
import com.example.core.domain.usecase.MediaUseCase
import kotlinx.coroutines.flow.collectLatest

class FavoriteViewModel(
    private val mediaUseCase: MediaUseCase
) :
    BaseViewModel() {
    private val _medias = MutableLiveData<List<Media>>()
    val medias: LiveData<List<Media>> get() = _medias

    fun getMediasFavorite(format: MediaFormat) {
        launchViewModelScope {
            mediaUseCase.getFavoriteMedias(format).collectLatest {
                _medias.postValue(it)
            }
        }
    }
}