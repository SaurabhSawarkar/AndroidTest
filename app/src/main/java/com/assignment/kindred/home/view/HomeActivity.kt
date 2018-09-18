package com.assignment.kindred.home.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.assignment.kindred.BR
import com.assignment.kindred.R
import com.assignment.kindred.base.ToolbarBaseActivity
import com.assignment.kindred.databinding.ActivityHomeBinding
import com.assignment.kindred.extension.showShortToast
import com.assignment.kindred.gameDetail.GameDetailActivity
import com.assignment.kindred.home.GamesListAdapter
import com.assignment.kindred.home.HomeViewModel
import com.assignment.kindred.network.model.Game
import com.assignment.kindred.util.AppConstants


class HomeActivity : ToolbarBaseActivity<HomeViewModel, ActivityHomeBinding>() {

    private var progressBar: View? = null

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
        init()
        setToolbar()
        initObserver()
    }

    private fun init() {
        viewModelInstance.getGames()
        progressBar = dataBindingInstance.progressBar?.root
    }

    private fun setToolbar() {
        setupToolbar("HomeScreen", false)
    }

    private fun initObserver() {
        viewModelInstance.getGamesList().observe(this, Observer { list -> updateGameList(list) })
        viewModelInstance.getNetworkError().observe(this, Observer {
            "Network call to fetch games is fail".showShortToast(this)
            progressBar?.visibility = View.GONE
        })
    }

    private fun updateGameList(list: List<Game>?) {
        progressBar?.visibility = View.GONE
        val recyclerView = dataBindingInstance.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val gamesAdapter = GamesListAdapter(gamesListClickListener)
        recyclerView.adapter = gamesAdapter
        gamesAdapter.updateList(list)
        "success".showShortToast(this)
    }

    private val gamesListClickListener = object : GamesListAdapter.IGamesListClickListener {
        override fun onGameItemClick(game: Game) {
            val intent = Intent(this@HomeActivity, GameDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable(AppConstants.KEY_GAME_DETAILS, game)
            intent.putExtra(AppConstants.KEY_BUNDLE, bundle)
            startActivity(intent)
        }
    }
}
