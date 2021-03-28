package com.example.stocks.retrofit.get_inf_stocks

import android.app.Application
import com.example.stocks.retrofit.search.SearchApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class StockApp : Application() {

    lateinit var stockApi: StockApi
    lateinit var searchApi: SearchApi

    override fun onCreate() {
        super.onCreate()
        stockApi = configureRetrofit("https://mboum.com/").create(StockApi::class.java)
        searchApi = configureRetrofit("https://finnhub.io").create(SearchApi::class.java)
    }

    private fun configureRetrofit(baseUrl: String): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}