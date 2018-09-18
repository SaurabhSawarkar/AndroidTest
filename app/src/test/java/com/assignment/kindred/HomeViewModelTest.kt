package com.assignment.kindred

import com.assignment.kindred.base.BaseResponse
import com.assignment.kindred.home.HomeViewModel
import com.assignment.kindred.network.model.Categories
import com.assignment.kindred.network.retrofit.manager.APIManager
import com.assignment.kindred.network.util.IResponsePublisher
import com.assignment.kindred.util.AppConstants
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer

class HomeViewModelTest {

    @Test
    public fun testGetGames() {
        APIManager.setBaseUrl(AppConstants.BASE_URL)
        val apiManager = Mockito.mock(APIManager::class.java)
        val callback = mock<IResponsePublisher<BaseResponse>>()
        val categories = mock<Categories>()
        val homeViewModel = Mockito.spy(HomeViewModel())
        homeViewModel.setApiManager(apiManager)

        Mockito.doAnswer(object : Answer<Any> {
            override fun answer(invocation: InvocationOnMock?): Any? {
                //This is not being called
                invocation?.arguments
                return null
            }
        }).`when`(apiManager).getGame(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()
                , Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                eq(categories), Mockito.anyString(), eq(callback))

        homeViewModel.getGames()
    }
}