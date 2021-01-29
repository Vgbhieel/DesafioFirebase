package me.vitornascimento.desafiofirebase.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import me.vitornascimento.desafiofirebase.adapter.GameAdapter
import me.vitornascimento.desafiofirebase.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    private val gameAdapter = GameAdapter()
    private val layoutManager = GridLayoutManager(this, 2)

    private val gameViewModel : GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvGames.adapter = gameAdapter
        binding.rvGames.layoutManager = layoutManager

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, NewGameActivity::class.java)
            startActivity(intent)
        }

        gameViewModel.games.observe(this, Observer {
            gameAdapter.addGames(it)
        })


        gameViewModel.getGames()
    }
}