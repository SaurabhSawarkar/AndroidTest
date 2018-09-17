package com.assignment.kindred.network.model

data class Game(

        var nodeName: String? = null,

        val tags: List<String>? = null,

        val playUrl: String? = null,

        val backgroundImageUrl: String? = null,

        val imageUrl: String? = null,

        val gameId: String? = null,

        val licenses: List<String>? = null,

        val vendorId: String? = null,

        val gameName: String? = null,

        val launchLocale: String? = null)