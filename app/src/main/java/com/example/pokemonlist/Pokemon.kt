package com.example.pokemonlist

import java.io.Serializable

data class Pokemon(
    val name: String,
    val number: String,
    val imageUrl: String,
    val types: String,
    val height: String,
    val weight: String,
    val description: String,
    val evolution: String
) :Serializable