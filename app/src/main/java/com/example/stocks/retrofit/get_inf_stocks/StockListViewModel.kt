package com.example.stocks.retrofit.get_inf_stocks

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.provider.Settings.Global.getString
import android.provider.Settings.Global.putInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stocks.R
import com.example.stocks.model_data.StockeListResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class StockListViewModel(application: Application): AndroidViewModel(application) {


    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


   fun fetchStockList(stockApi: StockApi): Single<StockeListResponse> {
       return stockApi.getStockList()
    }
}


