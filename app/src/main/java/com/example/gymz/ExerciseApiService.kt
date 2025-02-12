package com.example.gymz

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ExerciseApiService {
    @Headers(
        "X-RapidAPI-Key: 4a64a05b79msh89e0142002e9677p19c490jsnd2f1f1f3089b",
        "X-RapidAPI-Host: exercisedb.p.rapidapi.com"
    )
    @GET("exercises") // Endpoint to get exercises
    fun getExercises(): Call<List<Exercise>>
}