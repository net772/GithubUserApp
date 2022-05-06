package com.example.githubuserapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapp.data.response.GithubData
import com.example.githubuserapp.databinding.ItemUsersBinding
import com.example.githubuserapp.utility.loadCenterCrop

class UserListAdapter(
    private val callback: (data: GithubData) -> Unit
) : RecyclerView.Adapter<UserListAdapter.UserItemViewHolder>() {
    private val adapterList = mutableListOf<GithubData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val view = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bindData(adapterList[position])
    }

    override fun getItemCount() = adapterList.size

    fun set(list: List<GithubData>) {
        adapterList.clear()
        adapterList.addAll(list)
        notifyDataSetChanged()
    }

    inner class UserItemViewHolder(
        private val binding: ItemUsersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: GithubData) = with(binding) {
            userImage.loadCenterCrop(data.avatar_url)
            title.text = data.login
            content.text = data.html_url

            root.setOnClickListener {
                callback.invoke(data)
            }
        }
    }
}