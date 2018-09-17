package com.assignment.kindred.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.assignment.kindred.extension.lazyN
import io.reactivex.disposables.Disposable

class HomeViewModel : ViewModel() {

    private val isLoadMore by lazyN { MutableLiveData<Boolean>() }
    private val networkError by lazyN { MutableLiveData<Throwable>() }
    private val userSubscribe by lazyN { MutableLiveData<Disposable>() }


    fun getLoadMore(): LiveData<Boolean> {
        return isLoadMore
    }

    fun getNetworkError(): LiveData<Throwable> {
        return networkError
    }

    fun getUserSubscribe(): LiveData<Disposable> {
        return userSubscribe
    }
}