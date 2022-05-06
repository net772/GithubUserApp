package com.example.githubuserapp.ui.detail

import android.content.Intent
import android.net.Uri
import com.example.githubuserapp.data.response.GithubData
import com.example.githubuserapp.databinding.FragmentUserDetailBinding
import com.example.githubuserapp.extensions.removeFragment
import com.example.githubuserapp.ui.base.BaseFragment
import com.example.githubuserapp.utility.loadCenterCrop
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserDetailFragment: BaseFragment<UserDetailViewModel, FragmentUserDetailBinding>() {
    private val userDetailData by lazy { arguments?.getParcelable<GithubData>("USERDATA") }
    override val viewModel by viewModel<UserDetailViewModel> {
        parametersOf(userDetailData)
    }


    override fun getViewBinding() = FragmentUserDetailBinding.inflate(layoutInflater)

    override fun initFragment() {
        binding.userImage.loadCenterCrop(userDetailData?.avatar_url!!)
        binding.title.text = userDetailData?.login
        binding.link.text = userDetailData?.html_url

        binding.link.setOnClickListener {
            openWebView(userDetailData?.html_url ?: "")
        }
        binding.userImage.setOnClickListener {
            removeFragment(this@UserDetailFragment)
        }


    }

    override fun observeData() = Unit

    private fun openWebView(url: String) = startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
}