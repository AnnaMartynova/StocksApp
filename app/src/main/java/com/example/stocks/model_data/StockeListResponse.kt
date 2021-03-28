package com.example.stocks.model_data

import com.google.gson.annotations.SerializedName

data class StockeListResponse(val quotes: List<StockListItem>) {

    data class StockListItem(
        @SerializedName("symbol")
        var tiker: String?,

        @SerializedName("displayName")
        var full_name: String?,

        @SerializedName("regularMarketPrice")
        var price: String?,

        @SerializedName("postMarketChangePercent")
        var changesproc: String?,

        @SerializedName("postMarketChange")
        var changes: String?,

        @SerializedName("currency")
        var currency: String?

    )
}