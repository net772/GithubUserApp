package com.example.githubuserapp.extensions

import androidx.fragment.app.Fragment

fun Fragment.addFragment(containerViewId:Int, mFragment: Fragment, backStack: Boolean = false) {
    activity?.let {
        val transaction = it.supportFragmentManager.beginTransaction()
        if(backStack) transaction.addToBackStack(null)
        transaction.replace( containerViewId, mFragment)
        transaction.commitAllowingStateLoss()
    }
}

fun Fragment.removeFragment(mFragment: Fragment) {
    activity?.let {
        val manager = it.supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.remove(mFragment)
        transaction.commitAllowingStateLoss()
        manager.popBackStack()
    }
}