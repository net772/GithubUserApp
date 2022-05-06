package com.example.githubuserapp.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import com.example.githubuserapp.R
import com.example.githubuserapp.data.response.GithubData
import com.example.githubuserapp.databinding.FragmentUsersBinding
import com.example.githubuserapp.extensions.addFragment
import com.example.githubuserapp.ui.base.BaseFragment
import com.example.githubuserapp.ui.adapter.UserListAdapter
import com.example.githubuserapp.ui.detail.UserDetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment<UsersViewModel, FragmentUsersBinding>()  {
    companion object {
        fun newInstance() = UsersFragment()
    }

    override val viewModel by viewModel<UsersViewModel>()

    private val userListAdapter by lazy {
        UserListAdapter() {
            val bundle = Bundle().apply {
                putParcelable("USERDATA", it)
            }
            addFragment(R.id.fragmentContainer, UserDetailFragment().apply {
                arguments = bundle
            }, true)
        }
    }

    override fun getViewBinding() = FragmentUsersBinding.inflate(layoutInflater)

    override fun initFragment() {
        initAdapter()
    }

    private fun initAdapter() = with(binding) {
        recyclerView.apply {
            hasFixedSize()
            adapter = userListAdapter
        }
    }

    override fun observeData() {
        viewModel.userState.onUiState(
            error = { handleError() },
            loading = { handleLoading() },
            success = {
                handleSuccess(it)
            }
        )
    }

    @SuppressLint("ShowToast")
    private fun handleError() {
        Toast.makeText(requireContext() , "네트워크 오류입니다.", Toast.LENGTH_SHORT )
    }

    private fun handleLoading() = with(binding) {

    }

    private fun handleSuccess(data: List<GithubData>) = with(binding) {
        userListAdapter.set(data)
    }
}