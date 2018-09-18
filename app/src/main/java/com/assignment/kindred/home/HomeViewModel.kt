package com.assignment.kindred.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.assignment.kindred.base.BaseResponse
import com.assignment.kindred.extension.lazyN
import com.assignment.kindred.network.model.Game
import com.assignment.kindred.network.model.GamesResponse
import com.assignment.kindred.network.retrofit.manager.APIManager
import com.assignment.kindred.network.util.IResponsePublisher
import com.assignment.kindred.util.RequestTypes
import io.reactivex.disposables.Disposable

class HomeViewModel : ViewModel() {

    private val gamesList by lazyN { MutableLiveData<List<Game>>() }
    private val networkError by lazyN { MutableLiveData<Throwable>() }
    private val userSubscribe by lazyN { MutableLiveData<Disposable>() }

    fun getGames() {
        APIManager.getInstance().getGame(RequestTypes.GET_GAMES, "UK", "unibet", "mobilephone", "en_GB",
                "GBP", "casino", "casinoapp", responsePublisher)
    }


    fun getGamesList(): LiveData<List<Game>> {
        return gamesList
    }

    fun getNetworkError(): LiveData<Throwable> {
        return networkError
    }

    fun getUserSubscribe(): LiveData<Disposable> {
        return userSubscribe
    }

    private val responsePublisher = object : IResponsePublisher<BaseResponse> {
        override fun onSuccess(requestType: Int, responseBean: BaseResponse?) {
            when (requestType) {
                RequestTypes.GET_GAMES -> {
                    val response = responseBean as GamesResponse?
                    gamesList.value = response?.gamesList
                }

            }
        }

        override fun onError(requestType: Int, error: Throwable?) {
            networkError.value = error
        }

        override fun onSubscribe(requestType: Int, d: Disposable) {
            when (requestType) {
                RequestTypes.GET_GAMES -> {
                    userSubscribe.value = d
                }
            }
        }
    }

}