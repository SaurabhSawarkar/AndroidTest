package com.assignment.kindred.network.model

import com.assignment.kindred.base.BaseResponse

data class GamesResponse(
        var gamesList: List<Game>? = null
) : BaseResponse()