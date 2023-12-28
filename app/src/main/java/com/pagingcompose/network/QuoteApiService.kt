package com.pagingcompose.network

import com.pagingcompose.model.QuoteData
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApiService {

    @GET("quotes")
    suspend fun getQuotes(@Query("page") page: Int): QuoteData
}