package com.example.stocks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.stocks.R
import com.example.stocks.model_data.StockeListResponse


class StockAdapter(var arrayList: ArrayList<StockeListResponse.StockListItem>) :
    RecyclerView.Adapter<StockAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_stock, parent, false)
        return ItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val stockListItem = arrayList[position]
        holder.bind(stockListItem)
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: StockeListResponse.StockListItem) {
            with(itemView) {
                val changesTextView = findViewById<TextView>(R.id.changesTextView)
                val priceTextView = findViewById<TextView>(R.id.priceTextView)


                findViewById<TextView>(R.id.tikerTextView).text = item.tiker
                findViewById<TextView>(R.id.full_nameTextView).text = item.full_name
                priceTextView.text = item.price
                changesTextView.text = item.changes

                findViewById<CardView>(R.id.cardview).elevation = 0f

                val price = item.price.toString()

                if (item.currency == "USD") {
                    priceTextView.setText("$ $price")
                }

                if (((adapterPosition) % 2) == 0) {
                    setBackgroundResource(R.drawable.corners_dark)
                } else {
                    setBackgroundResource(R.color.colorWait)
                }

                if (item.changes == null || item.changesproc == null) {

                    val chislo = item.changes.toString()
                    val chisloproc = item.changesproc.toString()

                    changesTextView.setTextColor(
                        ContextCompat.getColor(context, R.color.colorPrimaryDark)
                    )

                    changesTextView.setText("$chislo ($chisloproc %)")
                } else {


                    val chislo = item.changes.toString().toFloat()
                    val chisloproc = item.changesproc.toString().toFloat()


                    if (chislo >= 0) {
                        changesTextView.setTextColor(
                            ContextCompat.getColor(context, R.color.colorGreen)
                        )

                        val changes = (Math.round(chislo * 100.0) / 100.0).toString()
                        val changesproc = (Math.round(chisloproc * 100.0) / 100.0).toString()

                        changesTextView.setText("+$changes ($changesproc %)")


                    } else {
                        changesTextView.setTextColor(
                            ContextCompat.getColor(context, R.color.colorRed)
                        )

                        val changes = (Math.round(chislo * 100.0) / 100.0).toString()
                        val changesproc = (Math.round(chisloproc * 100.0) / 100.0).toString()

                        val changespro = changesproc.removePrefix("-")

                        changesTextView.setText("$changes ($changespro %)")

                    }


                }

            }


        }
    }
}