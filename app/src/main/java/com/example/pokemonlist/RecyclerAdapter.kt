package com.example.pokemonlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter(val context: Context, val items: ArrayList<Pokemon>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.tv_pokename.text = item.name
        holder.tv_pokenum.text = item.number
        // Utilisation de Glide pour charger l'image à partir de l'URL
        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(R.drawable.pokeblack)
            .skipMemoryCache(true)
            .into(holder.img_pokemon)

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


