package com.pagingcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.pagingcompose.network.QuoteApiService
import com.pagingcompose.paging.QuotePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(val apiService: QuoteApiService) : ViewModel() {

    val data = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 180),
        pagingSourceFactory = { QuotePagingSource(apiService) }
    ).flow.cachedIn(viewModelScope)
}