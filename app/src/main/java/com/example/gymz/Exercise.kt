package com.example.gymz

data class Exercise(
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    val id: String,
    val name: String,
    val target: String,
    var sets: Int? = null,  // Add nullable sets property
    var reps: Int? = null
)
