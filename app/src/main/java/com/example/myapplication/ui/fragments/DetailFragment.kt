package com.example.myapplication.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.myapplication.R
import com.example.myapplication.models.Game
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.list_item.view.*

class DetailFragment(game: Game) : Fragment() {
    private val game = game

    companion object {
        fun newInstance(game: Game) = DetailFragment(game)
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val pref = this.activity!!.getSharedPreferences("navigator", Context.MODE_PRIVATE);
        val value = pref.getBoolean("value", false);

        pref.edit().putBoolean("value", !value).apply();
        if (value) {
            buttonDetail.text = "Open Broswer"
            buttonDetail.setOnClickListener {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(game.link)
                )
                startActivity(browserIntent)
            }
        } else {
            buttonDetail.text = "Open WebView"
            buttonDetail.setOnClickListener {
                val browserIntent = Intent(
                    this.context,
                    ActivityWebView::class.java
                );

                val bundle = Bundle();
                browserIntent.putExtra("url", Uri.parse(game.link).toString())
                startActivity(browserIntent)
            }
        }

        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        desc.text = game.description
        Glide.with(this)
            .load(game.img)
            .into(imgDetail)


    }

}
