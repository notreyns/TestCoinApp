package com.example.rei.testcoinapp.view.base

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cointestapp.view.model.fragment.FragmentTransaction
import com.example.rei.testcoinapp.R

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var navigation: NavController

    protected fun initializeNavController() {
        navigation = Navigation.findNavController(this, R.id.main_nav_container)
    }

    fun navigateToFragment(fragmentTransaction: FragmentTransaction) {
        navigation.navigate(
            fragmentTransaction.navigationId,
            fragmentTransaction.bundle
        )
    }

    fun popBackStack(@IdRes navigationId: Int, inclusive: Boolean = false) {
        navigation.popBackStack(navigationId, inclusive)
    }
}