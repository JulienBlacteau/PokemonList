package com.example.pokemonlist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val pokemonList: ArrayList<Pokemon> = ArrayList()

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

                    val pokemonDetails = Pokemon(name, number, imageUrl)
                    pokemonList.add(pokemonDetails)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } else {
            // Gérer le cas où jsonString est null
            Log.e("MainActivity", "JSON string is null")
        }

        // Log the size of the pokemonList
        Log.d("MainActivity", "pokemonList size: ${pokemonList.size}")

        val itemAdapter = RecyclerAdapter(this, pokemonList)
        recyclerView.adapter = itemAdapter
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
