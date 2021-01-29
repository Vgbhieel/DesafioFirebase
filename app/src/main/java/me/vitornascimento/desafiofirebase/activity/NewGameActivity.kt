package me.vitornascimento.desafiofirebase.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dmax.dialog.SpotsDialog
import me.vitornascimento.desafiofirebase.databinding.ActivityNewGameBinding
import me.vitornascimento.desafiofirebase.domain.Game
import java.sql.Timestamp
import java.text.SimpleDateFormat


class NewGameActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewGameBinding

    private lateinit var alertDialog : AlertDialog
    private lateinit var storageReference: StorageReference
    private val CODE_IMG = 1000

    private val sdf = SimpleDateFormat("yyyyMMddHHmmss")

    private var imageUrl = ""

    private val gameViewModel : GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.floatingActionButton3.setOnClickListener {
            setIntent()
        }

        binding.btnSave.setOnClickListener {
            val game = Game(
                binding.inputName.text.toString(),
                binding.inputAno.text.toString(),
                imageUrl,
                binding.inputDescricao.text.toString()
            )
            gameViewModel.saveGame(game)
        }

        gameViewModel.response.observe(this, Observer {
            if (it == "Sucesso") {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        config()

    }

    fun config() {
        alertDialog = SpotsDialog.Builder().setContext(this).build()
        val timestamp = Timestamp(System.currentTimeMillis())
        val timestampString = sdf.format(timestamp)
        storageReference = FirebaseStorage.getInstance().getReference(timestampString)
    }

    fun setIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Select Image from here..."
            ),
            CODE_IMG
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMG) {
            alertDialog.show()
            val uploadTask = storageReference.putFile(data!!.data!!)
            uploadTask.continueWithTask { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Imagem carregada", Toast.LENGTH_SHORT).show()
                }
                storageReference!!.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    val url = downloadUri!!.toString()
                        .substring(0, downloadUri.toString().indexOf("&token"))
                    imageUrl = url
                    Log.i("Link direto", url)
                    alertDialog.dismiss()
                }
            }
        }
    }
}