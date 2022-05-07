package com.example.githubuserapp.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.githubuserapp.R
import com.example.githubuserapp.data.entity.UserLikeEntity
import com.example.githubuserapp.data.response.GithubData
import com.example.githubuserapp.databinding.FragmentUsersBinding
import com.example.githubuserapp.extensions.addFragment
import com.example.githubuserapp.ui.adapter.UserListAdapter
import com.example.githubuserapp.ui.base.BaseFragment
import com.example.githubuserapp.ui.detail.UserDetailFragment
import com.example.githubuserapp.ui.main.MainActivity
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.FieldPosition

class UsersFragment : BaseFragment<UsersViewModel, FragmentUsersBinding>()  {
    companion object {
        fun newInstance() = UsersFragment()
    }

    override val viewModel by viewModel<UsersViewModel>()

    private var userDetailFragment: UserDetailFragment? = null

    private val userListAdapter by lazy {
        UserListAdapter() {
            val bundle = Bundle().apply {
                putParcelable("USERDATA", it)
            }
            userDetailFragment = UserDetailFragment().apply { arguments = bundle  }
            (activity as MainActivity).addFragment(userDetailFragment!!)
           // addFragment(R.id.fragmentContainer, userDetailFragment!!, true)
        }
    }

    override fun getViewBinding() = FragmentUsersBinding.inflate(layoutInflater)

    override fun initDataBinding() {
        binding.userViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initFragment() {
        initAdapter()
        tabSetting()
    }

    private fun initAdapter() = with(binding) {
        recyclerView.apply {
            hasFixedSize()
            adapter = userListAdapter
        }
    }

    private fun tabSetting() = with(binding) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d("동현","tab : ${tab?.position}")
                onTabSelected(tab?.position ?: 0)
                recyclerView.scrollToPosition(0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })
    }

    private fun onTabSelected(position: Int) {
        when (position) {
            0 -> {
                viewModel.fetchData()
            }

            1 -> {
                viewModel.getUserLikesData()
            }
        }
    }

    override fun observeData() {
        viewModel.userState.onUiState(
            error = { handleError() },
            loading = { handleLoading() },
            success = {
                handleSuccess(it)
            },
            finish = { handleComplete() }
        )

        viewModel.userLikeResponseState.onUiState(
            error = { handleError() },
            loading = { handleLoading() },
            success = {
                handleSuccess(it)
            },
            finish = { handleComplete() }
        )
    }

    @SuppressLint("ShowToast")
    private fun handleError() {
        Toast.makeText(requireContext() , "네트워크 오류입니다.", Toast.LENGTH_SHORT )
    }

    private fun handleLoading() = with(binding) {
        userListLoading.isVisible = true
    }

    private fun handleSuccess(data: List<UserLikeEntity>) {
        viewModel.setUserList(data as ArrayList<UserLikeEntity>)
    }

    private fun handleComplete() = with(binding) {
        userListLoading.isVisible = false
    }
}