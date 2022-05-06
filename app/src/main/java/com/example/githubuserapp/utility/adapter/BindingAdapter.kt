package com.example.githubuserapp.utility.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapp.ui.adapter.CustomRecyclerViewAdapter

object BindingAdapter {
    @BindingAdapter("items")
    @JvmStatic
    fun <T> setList(recyclerView: RecyclerView, items: List<T>?) {
        var adapter: CustomRecyclerViewAdapter? = null
        items?.let {
            adapter = recyclerView.adapter as CustomRecyclerViewAdapter?
        }

        adapter?.replaceData(items)
    }
}