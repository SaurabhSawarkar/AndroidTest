package com.assignment.kindred.gameDetail

import android.support.v4.content.ContextCompat
import android.view.MenuItem
import android.widget.TextView
import com.android.databinding.library.baseAdapters.BR
import com.assignment.kindred.R
import com.assignment.kindred.base.ToolbarBaseActivity
import com.assignment.kindred.databinding.ActivityGameDetailBinding
import com.assignment.kindred.network.model.Game
import com.assignment.kindred.util.AppConstants

class GameDetailActivity : ToolbarBaseActivity<GameDetailViewModel, ActivityGameDetailBinding>() {

    override fun initVariables() {
        setToolbar()
        val bundle = intent.getBundleExtra(AppConstants.KEY_BUNDLE)
        val gameDetails = bundle?.getSerializable(AppConstants.KEY_GAME_DETAILS) as Game
        viewModelInstance.setGameModel(gameDetails)
        populateTags()
    }

    private fun setToolbar() {
        setupToolbar("GameDetails", true)
    }

    override fun getViewModelClassName(): Class<GameDetailViewModel> {
        return GameDetailViewModel::class.java
    }

    private fun populateTags() {
        val tagsList = viewModelInstance.getTags()
        val containerView = dataBindingInstance.layoutTagsContainer
        containerView.removeAllViews()
        if (tagsList != null) {
            for (tag: String in tagsList) {
                val tagTextView = TextView(this)
                tagTextView.text = tag
                tagTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_bright))
                containerView.addView(tagTextView)
            }
        }
    }

    override fun getBindingVariable(): Int {
        return BR.gameDetailsVM
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_game_detail
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}