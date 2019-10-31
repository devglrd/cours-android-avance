package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.models.Game
import com.example.myapplication.http.GameApi
import com.example.myapplication.R
import com.example.myapplication.ui.fragments.DetailFragment
import com.example.myapplication.ui.fragments.GamesFragment

import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.frameContainer, GamesFragment()).commit()

    }

}
