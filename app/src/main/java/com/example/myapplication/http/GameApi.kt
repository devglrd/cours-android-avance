package com.example.myapplication.http

import com.example.myapplication.models.Game
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val base_url = "https://my-json-server.typicode.com/bgdom/cours-android/"

interface GameApi {
    @GET("games")
    fun games(): Call<List<Game>>

    companion object {
        operator fun invoke(): GameApi {
            return Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build().create(
                GameApi::class.java)
        }
    }
}