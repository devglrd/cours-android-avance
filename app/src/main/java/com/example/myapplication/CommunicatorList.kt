package com.example.myapplication

import com.example.myapplication.models.Game

interface CommunicatorList {
    val game: List<Game>

    fun open(game: Game): Unit
}