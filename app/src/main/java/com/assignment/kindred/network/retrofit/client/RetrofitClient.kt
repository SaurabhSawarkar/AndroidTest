package com.assignment.kindred.network.retrofit.client

import com.assignment.kindred.network.Deserializer.GameResponseDeserializer
import com.assignment.kindred.network.model.GamesResponse
import com.assignment.kindred.util.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.util.concurrent.TimeUnit


class RetrofitClient {

    companion object {
        var isDebug: Boolean = false
        var baseUrl: String? = null
        var gson: Gson? = null
        var interceptor: Interceptor? = null
        var certificate: InputStream? = null


        fun baseUrl(baseUrl: String): RetrofitClient.Companion {
            this.baseUrl = baseUrl
            return this
        }

        fun gson(gson: Gson): RetrofitClient.Companion {
            this.gson = gson
            return this
        }

        fun debug(debug: Boolean): RetrofitClient.Companion {
            isDebug = debug
            return this
        }

        fun interceptor(interceptor: Interceptor): RetrofitClient.Companion {
            this.interceptor = interceptor
            return this
        }

        fun certificate(certificate: InputStream): RetrofitClient.Companion {
            this.certificate = certificate
            return this
        }

        fun buildSimple(): Retrofit {
            val okHttpClient = getHttpClient()
            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(buildGsonConverter())
                    .build()
        }

        private fun buildGsonConverter(): GsonConverterFactory {
            val gsonBuilder = GsonBuilder()

            // Adding custom deserializers
            gsonBuilder.registerTypeAdapter(GamesResponse::class.java, GameResponseDeserializer())
            val myGson = gsonBuilder.create()

            return GsonConverterFactory.create(myGson)
        }

        private fun getHttpClient(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(AppConstants.OKHTTP_CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            httpClient.readTimeout(AppConstants.OKHTTP_READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            httpClient.followRedirects(false)
            if (interceptor != null) {
                httpClient.addInterceptor(interceptor)
            }
            if (isDebug) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)
            }
            return httpClient.build()
        }
    }
}