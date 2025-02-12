package com.example.gymz

import android.util.Log

class ExerciseRepository {
    suspend fun getExercises(): List<Exercise>? {
        // Make the API call synchronous by using `execute()`
        return try {
            val response = RetrofitClient.exerciseApiService.getExercises().execute()
            Log.d("ExerciseRepository", "API response: ${response.body()}")  // Log the entire response

            if (response.isSuccessful) {
                response.body()
            } else {
                // Handle unsuccessful response (e.g., log the error)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions during API call (e.g., network error)
            null
        }
    }
}