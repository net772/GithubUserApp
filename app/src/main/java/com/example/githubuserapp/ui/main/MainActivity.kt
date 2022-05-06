package com.example.githubuserapp.ui.main

import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.ActivityMainBinding
import com.example.githubuserapp.extensions.addFragment
import com.example.githubuserapp.ui.base.BaseActivity
import com.example.githubuserapp.ui.users.UsersFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel by viewModel<MainViewModel>()
    private var usersFragment: UsersFragment? = null

    override fun getViewBinding(): ActivityMainBinding =ActivityMainBinding.inflate(layoutInflater)

    override fun initActivity() {
        addFragment()
    }

    private fun addFragment() {
        usersFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as UsersFragment?
                ?: UsersFragment.newInstance()
        addFragment(R.id.fragmentContainer, usersFragment)
    }
}