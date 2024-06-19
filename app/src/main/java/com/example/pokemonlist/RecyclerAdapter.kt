package com.example.pokemonlist

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter(private val context: Context, private val items: ArrayList<Pokemon>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        Log.e("RecyclerAdapter", "onBindViewHolder: position $position, élément: ${item.name}")

        holder.tv_pokename.text = item.name
        holder.tv_pokenum.text = item.number

        Log.e("RecyclerAdapter", "onBindViewHolder: chargement de l'image depuis l'URL: ${item.imageUrl}")

        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(R.drawable.pokeblack)
            .into(holder.img_pokemon)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, PokemonDetailActivity::class.java)
            intent.putExtra("pokemon", item)
            context.startActivity(intent)
        }

        Log.e("RecyclerAdapter", "onBindViewHolder: Chargement de l'image initié")
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_pokemon: ImageView = itemView.findViewById(R.id.img_pokemon)
        var tv_pokename: TextView = itemView.findViewById(R.id.tv_pokename)
        var tv_pokenum: TextView = itemView.findViewById(R.id.tv_pokenum)
    }
}




