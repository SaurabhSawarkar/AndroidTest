package com.assignment.kindred.network.Deserializer

import com.assignment.kindred.network.model.Game
import com.assignment.kindred.network.model.GamesResponse
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class GameResponseDeserializer : JsonDeserializer<GamesResponse> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): GamesResponse {
        val response = GamesResponse()
        val gameList = ArrayList<Game>()

        val gson = Gson()
        val jsonObject = json?.asJsonObject?.get("games")?.asJsonObject
        val keyset = jsonObject?.keySet()
        val size = keyset?.size
        if (null != keyset) {
            for (key: String in keyset) {
                val jsonElement = jsonObject.get(key)
                val gameObject = gson.fromJson<Game>(jsonElement, Game::class.java)
                gameObject.nodeName = key
                gameList.add(gameObject)
            }
        }
        response.gamesList = gameList
        return response
    }
}