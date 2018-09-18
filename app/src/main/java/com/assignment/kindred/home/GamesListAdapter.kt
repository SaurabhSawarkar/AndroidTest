package com.assignment.kindred.home

import android.databinding.DataBindingUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.assignment.kindred.KindredApplication
import com.assignment.kindred.R
import com.assignment.kindred.databinding.GamesListItemBinding
import com.assignment.kindred.network.model.Game


class GamesListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    interface IGamesListClickListener {
        fun onGameItemClick(game: Game)
    }

    companion object {
        private var clickListener: IGamesListClickListener? = null
    }

    private var gameList = ArrayList<Game>()

    constructor(listener: IGamesListClickListener?) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val childBinding = DataBindingUtil.inflate(inflater, R.layout.games_list_item,
                parent, false) as GamesListItemBinding
        return Holder(childBinding)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Holder).bindViewHolder(gameList.get(position))
    }

    fun updateList(list: List<Game>?) {
        if (null != list) {
            gameList.clear()
            gameList.addAll(list)
            notifyDataSetChanged()
        }
    }

    class Holder : RecyclerView.ViewHolder {

        private val childBiding: GamesListItemBinding
        private var isCombine = true
        private val layoutManager: GridLayoutManager

        init {
            layoutManager = GridLayoutManager(KindredApplication.instance, 2)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    isCombine = !isCombine
                    return if (isCombine) 1 else 2
                }
            }
        }

        constructor(binder: GamesListItemBinding) : super(binder.root) {
            childBiding = binder
        }

        fun bindViewHolder(game: Game) {
            val viewModel = childBiding.gameVM
            if (null == viewModel) {
                childBiding.gameVM = GameListViewModel()
            }
            childBiding.gameVM?.setGameModel(game)
            val view = childBiding.root
            view.tag = game
            view.setOnClickListener { v -> clickListener?.onGameItemClick(v.tag as Game) }
        }
    }
}