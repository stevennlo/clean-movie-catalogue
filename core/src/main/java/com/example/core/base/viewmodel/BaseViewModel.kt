package com.example.core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected fun launchViewModelScope(block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            block.invoke()
        }
    }
}