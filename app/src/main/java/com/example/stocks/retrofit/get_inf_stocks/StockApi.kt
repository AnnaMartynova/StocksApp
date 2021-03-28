package com.example.stocks.retrofit.get_inf_stocks

import com.example.stocks.model_data.StockeListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface StockApi {

    @GET("./api/v1/co/collections/?list=growth_technology_stocks&start=1&apikey=demo")
    fun getStockList(): Single<StockeListResponse>
}