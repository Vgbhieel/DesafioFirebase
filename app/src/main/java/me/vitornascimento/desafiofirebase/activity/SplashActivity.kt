package me.vitornascimento.desafiofirebase.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import me.vitornascimento.desafiofirebase.R

class SplashActivity : AppCompatActivity() {

    private val splashScreenTime : Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, splashScreenTime)
    }
}