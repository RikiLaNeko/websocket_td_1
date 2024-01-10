package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Valeur(
    val id: Int? = null,
    val valeur1: Int? = null,
    val valeur2: Int? = null
)
