package com.assignment.kindred.network.retrofit

import com.assignment.kindred.network.model.Categories
import com.assignment.kindred.network.model.GamesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IEndPoints {

    interface IGamesEndPoint {
        @GET("game-library-rest-api/getGamesByMarketAndDevice.json")
        fun getGames(@Query("jurisdiction") jurisdiction: String,
                     @Query("brand") brand: String,
                     @Query("deviceGroup") deviceGroup: String,
                     @Query("locale") locale: String,
                     @Query("currency") currency: String,
                     @Query("categories") categories: Categories,
                     @Query("clientId") clientId: String): Single<GamesResponse>
    }
}