package com.example.stocks.retrofit.search

import com.example.stocks.model_data.SearchListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("./api/v1/search?token=sandbox_c117j8748v6tj8r99ml0")
    fun getSearchList(@Query(value = "q") query: String): Single<SearchListResponse>
}