package com.example.pokemonlist

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition

class RecyclerAdapter(val context: Context, val items: ArrayList<Pokemon>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

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
            .skipMemoryCache(true)
            .into(holder.img_pokemon)

        Log.e("RecyclerAdapter", "onBindViewHolder: Chargement de l'image initié")

        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(R.drawable.pokeblack)
            .skipMemoryCache(true)
            .into(object : CustomViewTarget<ImageView, Drawable>(holder.img_pokemon) {
                override fun onLoadFailed(errorDrawable: Drawable?) {
                    Log.e("RecyclerAdapter", "Glide onLoadFailed: Impossible de charger l'image depuis l'URL: ${item.imageUrl}") // à partir de #032
                }

                override fun onResourceCleared(placeholder: Drawable?) {
                    Log.e("RecyclerAdapter", "Glide onResourceCleared: Ressource d'image nettoyée")
                }

                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    Log.e("RecyclerAdapter", "Glide onResourceReady: Image chargée depuis l'URL: ${item.imageUrl}")
                    holder.img_pokemon.setImageDrawable(resource)
                }
            })
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



