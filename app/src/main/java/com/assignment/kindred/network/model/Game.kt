package com.assignment.kindred.network.model

import java.io.Serializable

data class Game(

        var nodeName: String? = null,

        var tags: List<String>? = null,

        var playUrl: String? = null,

        var backgroundImageUrl: String? = null,

        var imageUrl: String? = null,

        var gameId: String? = null,

        var licenses: List<String>? = null,

        var vendorId: String? = null,

        var gameName: String? = null,

        var launchLocale: String? = null) : Serializable