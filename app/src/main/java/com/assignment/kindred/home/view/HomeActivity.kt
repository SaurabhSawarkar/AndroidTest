package com.assignment.kindred.home.view

import com.assignment.kindred.BR
import com.assignment.kindred.R
import com.assignment.kindred.base.ToolbarBaseActivity
import com.assignment.kindred.databinding.ActivityHomeBinding
import com.assignment.kindred.home.HomeViewModel


class HomeActivity : ToolbarBaseActivity<HomeViewModel, ActivityHomeBinding>() {

    override fun getViewModelClassName(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.homeVM
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initVariables() {
        setToolbar()
        initObserver()
    }

    private fun setToolbar() {
        setupToolbar("HomeScreen", false)
    }

    private fun initObserver() {

    }
}
