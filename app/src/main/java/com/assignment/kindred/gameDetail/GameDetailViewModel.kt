package com.assignment.kindred.gameDetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.assignment.kindred.extension.lazyN
import com.assignment.kindred.network.model.Game

class GameDetailViewModel : ViewModel() {
    val gameName by lazyN { ObservableField<String>() }
    val gameNodeName by lazyN { ObservableField<String>() }
    val tags by lazyN { ObservableField<List<String>>() }
    val licenses by lazyN { ObservableField<List<String>>() }
    val playUrl by lazyN { ObservableField<String>() }
    val gameId by lazyN { ObservableField<String>() }
    val backgroundImageUrl by lazyN { ObservableField<String>() }
    val vendorId by lazyN { ObservableField<String>() }
    val launchLocale by lazyN { ObservableField<String>() }

    fun setGameModel(game: Game) {
        gameName.set(game.gameName)
        gameNodeName.set(game.nodeName)
        tags.set(game.tags)
        licenses.set(game.licenses)
        playUrl.set(game.playUrl)
        gameId.set(game.gameId)
        vendorId.set(game.vendorId)
        backgroundImageUrl.set(game.backgroundImageUrl)
        launchLocale.set(game.launchLocale)
    }

    fun getTags(): List<String>? {
        return tags.get()
    }
}