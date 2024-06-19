package com.example.pokemonlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var pokemonList: List<Pokemon>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        pokemonList = pokemonList()

        val pokemon = intent.getSerializableExtra("pokemon")
        if (pokemon is Pokemon) {
            val imgPokemon: ImageView = findViewById(R.id.imgPokemon)
            val pokemonName: TextView = findViewById(R.id.pokemonName)

            val pokemonType: TextView = findViewById(R.id.pokemonType)
            /*val pokemonType1: TextView = findViewById(R.id.pokemonType1)
            val pokemonType2: TextView = findViewById(R.id.pokemonType2)*/

            val pokemonSizeValue: TextView = findViewById(R.id.pokemonSizeValue)
            val pokemonWeightValue: TextView = findViewById(R.id.pokemonWeightValue)
            val pokemonDescription: TextView = findViewById(R.id.pokemonDescription)
            val pokemonEvolution: TextView = findViewById(R.id.pokemonEvolution)

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
            buttonReturn.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            val buttonNext: Button = findViewById(R.id.buttonNext)
            if(pokemonEvolution.text != "") {
                buttonNext.setOnClickListener {

                }
            } else {
                buttonNext.visibility = View.INVISIBLE
            }

        }
    }

    private fun pokemonList(): List<Pokemon> {
        val pokemonList = mutableListOf<Pokemon>()
        val jsonString = getJSONFromAssets()
        if (jsonString != null) {
            try {
                val obj = JSONObject(jsonString)
                val pokemonArray = obj.getJSONArray("pokemon")

                for (i in 0 until pokemonArray.length()) {
                    val pokemon = pokemonArray.getJSONObject(i)
                    val name = pokemon.getString("name")
                    val number = pokemon.getString("number")
                    val imageUrl = pokemon.getString("imageUrl")
                    val types = pokemon.getString("types")
                    val height = pokemon.getString("height")
                    val weight = pokemon.getString("weight")
                    val description = pokemon.getString("description")
                    val evolution = pokemon.getString("evolution")

                    val pokemonDetails = Pokemon(name, number, imageUrl, types, height, weight, description, evolution)
                    pokemonList.add(pokemonDetails)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } else {
            Log.e("PokemonDetailActivity", "JSON string is null")
        }
        return pokemonList
    }

    private fun getJSONFromAssets(): String? {
        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myPokemonJSonFile = assets.open("pokemon.json")
            val size = myPokemonJSonFile.available()
            val buffer = ByteArray(size)
            myPokemonJSonFile.read(buffer)
            myPokemonJSonFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json
    }

}