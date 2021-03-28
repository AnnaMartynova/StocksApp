package com.example.stocks.retrofit.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.stocks.model_data.SearchListResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class SearchListViewModel(application: Application): AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun fetchSearchList(searchApi: SearchApi, query: String): Single<SearchListResponse> {
        return searchApi.getSearchList(query)
    }


}
