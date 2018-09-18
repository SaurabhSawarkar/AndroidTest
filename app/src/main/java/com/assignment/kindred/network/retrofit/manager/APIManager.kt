package com.assignment.kindred.network.retrofit.manager

import android.util.Log
import com.assignment.kindred.base.BaseResponse
import com.assignment.kindred.network.model.Categories
import com.assignment.kindred.network.model.GamesResponse
import com.assignment.kindred.network.retrofit.IEndPoints
import com.assignment.kindred.network.retrofit.client.RetrofitClient
import com.assignment.kindred.network.util.IResponsePublisher
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class APIManager : IAPIManager {

    private val TAG = "APIManager"

    companion object {

        @Volatile
        private var instance: IAPIManager? = null
        private lateinit var baseUrl: String
        private lateinit var retrofitClient: Retrofit

        fun getInstance(): IAPIManager {
            if (null == instance) {
                synchronized(APIManager::class.java) {
                    if (null == instance) {
                        instance = APIManager()
                    }
                }
            }
            return instance!!
        }

        fun setBaseUrl(baseUrl: String) {
            this.baseUrl = baseUrl
            updateRetrofitClient()
        }

        private fun updateRetrofitClient() {
            retrofitClient = RetrofitClient.baseUrl(baseUrl).debug(true).buildSimple()
        }
    }

    override fun getGame(requestType: Int, jurisdiction: String, brand: String, deviceGroup: String,
                         locale: String, currency: String, categories: Categories, clientId: String,
                         @NonNull publisher: IResponsePublisher<BaseResponse>) {

        val api = retrofitClient.create(IEndPoints.IGamesEndPoint::class.java)
        val observable = api.getGames(jurisdiction, brand, deviceGroup, locale, currency, categories, clientId)
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<GamesResponse> {
                    override fun onSuccess(t: GamesResponse) {
                        Log.d(TAG, "Success: ")
                        publisher.onSuccess(requestType, t)
                    }

                    override fun onSubscribe(d: Disposable) {
                        publisher.onSubscribe(requestType, d)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "Error: " + e.message)
                        publisher.onError(requestType, e)
                    }
                })
    }
}