package com.example.githubuserapp.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.githubuserapp.data.entity.UserLikeEntity
import com.example.githubuserapp.databinding.ActivityMainBinding
import com.example.githubuserapp.ui.adapter.UserListAdapter
import com.example.githubuserapp.ui.base.BaseActivity
import com.example.githubuserapp.ui.detail.UserDetailActivity
import com.example.githubuserapp.ui.detail.UserDetailViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel by viewModel<MainViewModel>()

    private val userListAdapter by lazy {
        UserListAdapter() {
            val intent = Intent(this, UserDetailActivity::class.java)
            intent.putExtra(UserDetailViewModel.KEY_USERDATA, it)
            startActivity(intent)
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initActivity() {
        initDataBinding()
        initAdapter()
        tabSetting()
    }

    private fun initDataBinding() {
        binding.userViewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initAdapter() = with(binding) {
        recyclerView.apply {
            hasFixedSize()
            adapter = userListAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        onTabSelected(binding.tabLayout.selectedTabPosition)
    }

    private fun tabSetting() = with(binding) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                onTabSelected(tab?.position ?: 0)
                recyclerView.scrollToPosition(0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })
    }

    private fun onTabSelected(position: Int) {
        when (position) {
            MainViewModel.TAB_ALL -> {
                viewModel.fetchData()
            }

            MainViewModel.TAB_LIKE -> {
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
        Toast.makeText(this , "네트워크 오류입니다.", Toast.LENGTH_SHORT )
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