package com.assignment.kindred.network.retrofit.manager

import com.assignment.kindred.base.BaseResponse
import com.assignment.kindred.network.model.Categories
import com.assignment.kindred.network.util.IResponsePublisher
import com.assignment.kindred.util.RequestTypes
import io.reactivex.annotations.NonNull

interface IAPIManager {

    fun getGame(@RequestTypes.Interface requestType: Int, jurisdiction: String,
                brand: String,
                deviceGroup: String,
                locale: String,
                currency: String,
                categories: Categories,
                clientId: String, @NonNull publisher: IResponsePublisher<BaseResponse>)
}