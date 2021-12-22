package com.example.android.infinitescroll.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.android.infinitescroll.paging.PagingSource

class MainViewModel(application: Application): AndroidViewModel(application) {

    val listData = Pager(PagingConfig(pageSize = 20)) {
        PagingSource()
    }.flow.cachedIn(viewModelScope)
}