package com.example.android.infinitescroll.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.android.infinitescroll.paging.PagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status

    private var _toTop = MutableLiveData<Boolean?>()
    val toTop: LiveData<Boolean?> get() = _toTop

    val characterList = MutableStateFlow("")
    var listData = characterList.flatMapLatest {
        Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                PagingSource(status = status.value!!)
            }
        ).flow.cachedIn(viewModelScope)
    }

    init {
        _status.value = "alive"
        getCharacters()
    }

    fun updateStatus(status: String) {
        if (status != _status.value) {
            scrollToTop()
        }
        _status.value = status
        getCharacters()
    }

    private fun scrollToTop() {
        _toTop.value = true
    }

    fun sendScrollToTop() {
        _toTop.value = null
    }

    fun getCharacters() {
        listData = Pager(PagingConfig(pageSize = 10), pagingSourceFactory = {
            PagingSource(status.value!!)
        }).flow.cachedIn(viewModelScope)
    }
}