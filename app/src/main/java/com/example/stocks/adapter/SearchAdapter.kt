package com.example.stocks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocks.R
import com.example.stocks.model_data.SearchListResponse


class SearchAdapter (var arrayList: ArrayList<SearchListResponse.SearchListItem>) :
    RecyclerView.Adapter<SearchAdapter.ElementHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementHolder {
      val  itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_stock, parent, false)
        return ElementHolder(itemView)
    }

    override fun getItemCount(): Int {
      return  arrayList.size
    }

    override fun onBindViewHolder(holder: ElementHolder, position: Int) {
        val searchListItem = arrayList[position]
        holder.bind(searchListItem)
    }

    class ElementHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(item: SearchListResponse.SearchListItem) {
            with(itemView) {
                findViewById<TextView>(R.id.full_nameTextView).text = item.description
                findViewById<TextView>(R.id.tikerTextView).text = item.symbol

                findViewById<CardView>(R.id.cardview).elevation = 0f

                if (((adapterPosition) % 2) == 0) {
                    setBackgroundResource(R.drawable.corners_dark)
                } else {
                    setBackgroundResource(R.color.colorWait)
                }
            }

        }

    }
}