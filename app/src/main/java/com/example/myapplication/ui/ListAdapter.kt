package com.example.myapplication.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.CommunicatorList
import com.example.myapplication.R
import com.example.myapplication.models.Game
import kotlinx.android.synthetic.main.list_item.view.*


class ListAdapter(val instanceOfGame: CommunicatorList) :
    RecyclerView.Adapter<GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = instanceOfGame.game[position];
        val state = true;
        holder.view.game_name.text = game.name
        Glide.with(holder.view.context)
            .load(game.img)
            .into(holder.view.img)

        holder.view.btn.setOnClickListener { instanceOfGame.open(game) }

    }

    override fun getItemCount(): Int {
        return instanceOfGame.game.size
    }

}


class GameViewHolder(val view: View) : RecyclerView.ViewHolder(view)