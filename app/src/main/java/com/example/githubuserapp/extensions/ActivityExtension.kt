package com.example.githubuserapp.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.addFragment(@IdRes containerId: Int, fragment: Fragment?, addBackStack: Boolean = false) {
    requireNotNull(fragment)

    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(containerId, fragment).apply {
        if (addBackStack) addToBackStack(null)
    }
    transaction.commitAllowingStateLoss()
}