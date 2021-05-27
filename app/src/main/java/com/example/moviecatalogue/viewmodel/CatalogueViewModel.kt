package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.viewmodel.BaseViewModel
import com.example.core.domain.Resource
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaFormat
import com.example.core.domain.usecase.MediaUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

@ObsoleteCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
class CatalogueViewModel(
    private val mediaUseCase: MediaUseCase
) :
    BaseViewModel() {
    private val _medias = MutableLiveData<Resource<List<Media>>>()
    val medias: LiveData<Resource<List<Media>>> get() = _medias
    val queryChannel = BroadcastChannel<String>(CONFLATED)
    var keyword: String = ""
        private set

    fun setSearchFormat(format: MediaFormat) {
        launchViewModelScope {
            queryChannel.asFlow()
                .debounce(300)
                .flatMapLatest {
                    keyword = it
                    mediaUseCase.searchMedias(format, it)
                }.collectLatest {
                    _medias.postValue(it)
                }
        }
    }

    fun getMedias(format: MediaFormat) {
        launchViewModelScope {
            mediaUseCase.getMedias(format).collectLatest {
                _medias.postValue(it)
            }
        }
    }

    fun refreshSearch() {
        queryChannel.trySend(keyword)
    }
}