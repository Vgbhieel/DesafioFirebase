package me.vitornascimento.desafiofirebase.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import me.vitornascimento.desafiofirebase.R
import me.vitornascimento.desafiofirebase.activity.GameActivity
import me.vitornascimento.desafiofirebase.domain.Game
import com.bumptech.glide.Glide
import me.vitornascimento.desafiofirebase.databinding.ItemGameBinding

class GameAdapter() : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private val games = arrayListOf<Game>()

    class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage : ImageView = view.findViewById(R.id.ivImage)
        val tvGameName : TextView = view.findViewById(R.id.tvGameName)
        val tvGameAno : TextView = view.findViewById(R.id.tvGameAno)
        val cardView : CardView = view.findViewById(R.id.cvGame)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemBinding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(itemBinding.root)
    }

    override fun getItemCount() = games.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]

        holder.tvGameName.text = game.name
        holder.tvGameAno.text = game.ano

//        holder.ivImage.setImageResource(R.drawable.splash)
        Glide.with(holder.ivImage.context).asBitmap()
            .load(game.image)
            .into(holder.ivImage)

        holder.cardView.setOnClickListener {
            val intent = Intent(it.context, GameActivity::class.java)
            intent.putExtra("game", game)
            it.context.startActivity(intent)
        }
    }

    fun addGames(_comics: ArrayList<Game>) {
        var size = this.games.size
        games.addAll(_comics)
        var newSize = this.games.size
        notifyItemRangeChanged(size, newSize)
    }
}