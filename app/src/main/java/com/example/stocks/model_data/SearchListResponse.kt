package com.example.stocks.model_data

import com.google.gson.annotations.SerializedName

data class SearchListResponse  (val result: List<SearchListItem>) {

        data class SearchListItem(
            @SerializedName("description")
            var description: String?,

            @SerializedName("symbol")
            var symbol: String?

        )
    }
