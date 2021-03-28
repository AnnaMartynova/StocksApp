package com.example.stocks

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocks.adapter.StockAdapter
import com.example.stocks.retrofit.get_inf_stocks.StockApp
import com.example.stocks.retrofit.get_inf_stocks.StockListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HomeFragment : Fragment(R.layout.home_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val stockListViewModel = ViewModelProvider(this).get(StockListViewModel::class.java)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val findText =view.findViewById<TextView>(R.id.findText)

        stockListViewModel.fetchStockList((requireActivity().application as StockApp).stockApi)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                recyclerView?.adapter = StockAdapter(ArrayList(it.quotes))
            }, { error ->
                Log.e(HomeFragment::class.simpleName, "Error", error)
            })


            
            findText.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, SearchFragment())
                    .commit()
           }


    }


    }



