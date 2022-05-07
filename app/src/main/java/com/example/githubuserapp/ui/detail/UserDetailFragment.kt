package com.example.githubuserapp.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.githubuserapp.R
import com.example.githubuserapp.data.entity.UserLikeEntity
import com.example.githubuserapp.data.response.GithubData
import com.example.githubuserapp.databinding.FragmentUserDetailBinding
import com.example.githubuserapp.extensions.removeFragment
import com.example.githubuserapp.ui.base.BaseFragment
import com.example.githubuserapp.ui.users.UsersFragment
import com.example.githubuserapp.utility.loadCenterCrop
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserDetailFragment: BaseFragment<UserDetailViewModel, FragmentUserDetailBinding>() {

    private val userDetailData by lazy { arguments?.getParcelable<UserLikeEntity>("USERDATA") }
    override val viewModel by viewModel<UserDetailViewModel> {
        parametersOf(userDetailData)
    }

    override fun getViewBinding() = FragmentUserDetailBinding.inflate(layoutInflater)

    override fun initDataBinding() = Unit

    override fun initFragment() {
        setClickListeners()
        observeViewModel()
    }

    private fun setClickListeners() = with(binding) {
        link.setOnClickListener {
            openWebView(userDetailData?.html_url ?: "")
        }

        likeImageButton.setOnClickListener {
            viewModel.setToggleLiked()
        }
    }

    private fun observeViewModel() = with(viewModel) {
        likeState.observe {
            if (it) binding.likeImageButton.setImageResource(R.drawable.ic_like_on)
            else binding.likeImageButton.setImageResource(R.drawable.ic_like_off)
        }
    }

    override fun observeData() {
        viewModel.userDetailStateLiveData.onUiState(
            error = { handleError() },
            success = { handleSuccess(it) }
        )
    }

    @SuppressLint("ShowToast")
    private fun handleError() {
        Toast.makeText(requireContext() , "네트워크 오류입니다.", Toast.LENGTH_SHORT )
    }

    private fun handleSuccess(data: UserLikeEntity) {
        binding.userImage.loadCenterCrop(data.avatar_url)
        binding.title.text = data.login
        binding.link.text = data.html_url

    }

    private fun openWebView(url: String) = startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))

}