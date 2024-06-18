package com.example.pokemonlist

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class PokemonDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        

        val pokemon = intent.getSerializableExtra("pokemon")
        if (pokemon is Pokemon) {
            // Maintenant, vous pouvez utiliser pokemon en toute sécurité
            val imgPokemon: ImageView = findViewById(R.id.imgPokemon)
            val pokemonName: TextView = findViewById(R.id.pokemonName)

            val pokemonType: TextView = findViewById(R.id.pokemonType)

            /*val pokemonType1: TextView = findViewById(R.id.pokemonType1)
            val pokemonType2: TextView = findViewById(R.id.pokemonType2)*/
            val pokemonSizeValue: TextView = findViewById(R.id.pokemonSizeValue)
            val pokemonWeightValue: TextView = findViewById(R.id.pokemonWeightValue)
            val pokemonDescription: TextView = findViewById(R.id.pokemonDescription)
            val pokemonEvolution: TextView = findViewById(R.id.pokemonEvolution)

            // Set the image, name, types, height, weight, and description
            Glide.with(this)
                .load(pokemon.imageUrl)
                .placeholder(R.drawable.pokeblack)
                .into(imgPokemon)
            pokemonName.text = pokemon.name

            pokemonType.text = pokemon.types

            /*pokemonType1.text = pokemon.types.joinToString(", ")
            pokemonType2.text = pokemon.types.joinToString(", ")*/
            pokemonSizeValue.text = pokemon.height
            pokemonWeightValue.text = pokemon.weight
            pokemonDescription.text = pokemon.description
            pokemonEvolution.text = pokemon.evolution

            val buttonReturn: Button = findViewById(R.id.buttonReturn)
            buttonReturn.setOnClickListener { finish() }

            val buttonNext: Button = findViewById(R.id.buttonNext)
            if(pokemonEvolution.text != "") {
                buttonNext.setOnClickListener {  }
            } else {
                buttonNext.setVisibility(View.INVISIBLE)
            }


        } else {
            // Gérer le cas où l'objet n'est pas de type Pokemon
            Toast.makeText(this, "Invalid Pokemon data", Toast.LENGTH_SHORT).show()
            finish() // Terminer l'activité si les données Pokemon sont invalides
        }
    }
}