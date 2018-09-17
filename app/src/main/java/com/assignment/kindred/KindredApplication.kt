package com.assignment.kindred

import android.app.Application
import com.assignment.kindred.network.retrofit.manager.APIManager
import com.assignment.kindred.util.AppConstants

class KindredApplication : Application() {

    companion object {
        lateinit var instance: KindredApplication
    }

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        instance = this
        APIManager.setBaseUrl(AppConstants.BASE_URL)
    }
}