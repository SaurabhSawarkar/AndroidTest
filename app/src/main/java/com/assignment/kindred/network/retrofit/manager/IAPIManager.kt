package com.assignment.kindred.network.retrofit.manager

import com.assignment.kindred.util.RequestTypes

interface IAPIManager {

    fun getGame(@RequestTypes.Interface requestType: Int, jurisdiction: String,
                brand: String,
                deviceGroup: String,
                locale: String,
                currency: String,
                categories: String,
                clientId: String)
}