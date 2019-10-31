package com.example.myapplication.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.CommunicatorList

import com.example.myapplication.R
import com.example.myapplication.http.GameApi
import com.example.myapplication.models.Game
import com.example.myapplication.ui.ListAdapter
import kotlinx.android.synthetic.main.games_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesFragment : Fragment() {

    companion object {
        fun newInstance() = GamesFragment()
    }

    private lateinit var viewModel: GamesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.games_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GamesViewModel::class.java)

        // TODO: Use the ViewModel

        refresh_layout.setOnRefreshListener { fetchGames() }
        fetchGames()
    }


    private fun fetchGames() {
        refresh_layout.isRefreshing = true;
        GameApi()
            .games().enqueue(object : Callback<List<Game>> {
                override fun onFailure(call: Call<List<Game>>, t: Throwable) {
                    Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                    refresh_layout.isRefreshing = false;
                }

                override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                    refresh_layout.isRefreshing = false;
                    val games = response.body();
                    games?.let {
                        showGame(it)
                    }
                }

            })

    }

    private fun showGame(games: List<Game>) {
        rv_list.layoutManager = LinearLayoutManager(activity)
        rv_list.adapter = ListAdapter(object : CommunicatorList{
            override val game: List<Game> = games

            override fun open(game: Game) {
                fragmentManager!!.beginTransaction().replace(R.id.frameContainer, DetailFragment(game)).addToBackStack(null).commit()
            }
        })
    }

}
