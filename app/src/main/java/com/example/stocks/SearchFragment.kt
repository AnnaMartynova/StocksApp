package com.example.stocks

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocks.adapter.SearchAdapter
import com.example.stocks.retrofit.get_inf_stocks.StockApp
import com.example.stocks.retrofit.search.SearchListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchFragment : Fragment(R.layout.search_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchListViewModel = ViewModelProvider(this).get(SearchListViewModel::class.java)
        val editText = view.findViewById<EditText>(R.id.editText)
        val recyclerViewSearch = view.findViewById<RecyclerView>(R.id.recyclerSearch)
        recyclerViewSearch.layoutManager = LinearLayoutManager(activity)
        val backButton = view.findViewById<ImageButton>(R.id.back)
        val deleteButton = view.findViewById<ImageButton>(R.id.delete)




        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchListViewModel.fetchSearchList((requireActivity().application as StockApp).searchApi, query = s.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        recyclerViewSearch?.adapter = SearchAdapter(ArrayList(it.result))
                    }, { error ->
                        Log.e(SearchFragment::class.simpleName, "Error", error)
                    })
            }

        })
        deleteButton.setOnClickListener {
              editText.getText().clear()
        }

        backButton.setOnClickListener {
            val fm: FragmentManager = requireActivity().supportFragmentManager
            fm.popBackStack()
        }


    }
}