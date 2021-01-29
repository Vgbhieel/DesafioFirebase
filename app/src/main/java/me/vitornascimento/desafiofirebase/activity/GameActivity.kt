package me.vitornascimento.desafiofirebase.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.vitornascimento.desafiofirebase.domain.Game
import com.bumptech.glide.Glide
import me.vitornascimento.desafiofirebase.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val game = intent.getSerializableExtra("game") as Game

        binding.toolbar.setNavigationOnClickListener {
            this.onBackPressed()
        }

        binding.tvTitulo.text = game.name
        binding.tvTitulo2.text = game.name
        binding.tvAno.text = game.ano
        binding.tvDescricao.text = game.descricao

        Glide.with(this).asBitmap()
            .load(game.image)
            .into(binding.ivCover)
    }
}