package com.pagingcompose.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pagingcompose.model.Results
import com.pagingcompose.network.QuoteApiService
import kotlinx.coroutines.delay

class QuotePagingSource(private val apiService: QuoteApiService) : PagingSource<Int, Results>() {

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        val position = params.key ?: 1
        if (position!=1){
            delay(3000L)
        }
        if (position > 5){
            return LoadResult.Error(Exception())
        }
        val response = apiService.getQuotes(position)
        return try {
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}