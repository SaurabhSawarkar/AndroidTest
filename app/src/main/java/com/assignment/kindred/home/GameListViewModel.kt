package com.assignment.kindred.home

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.assignment.kindred.extension.lazyN
import com.assignment.kindred.network.model.Game

class GameListViewModel : ViewModel() {

    val gameName by lazyN { ObservableField<String>() }
    val gameNodeName by lazyN { ObservableField<String>() }
    val imageUrl by lazyN { ObservableField<String>() }

    fun setGameModel(game: Game) {
        gameName.set(game.gameName)
        gameNodeName.set(game.nodeName)
        imageUrl.set(game.imageUrl)
    }
}