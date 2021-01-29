package me.vitornascimento.desafiofirebase.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.vitornascimento.desafiofirebase.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCadastro.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}