package com.example.gymz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class ExerciseViewModel: ViewModel() {
    private val repository = ExerciseRepository()
    private val _exercises = MutableLiveData<List<Exercise>>()
    val exercises: LiveData<List<Exercise>> = _exercises

    init {
        fetchExercises()
    }

    private fun fetchExercises() {
        viewModelScope.launch {
            val allExercises = withContext(Dispatchers.IO) { repository.getExercises() }?: emptyList()
            val filteredExercises = filterExercises(allExercises)
            generateSetsAndReps(filteredExercises)
            Log.d("ExerciseViewModel", "Filtered exercises: $filteredExercises")

            // Update LiveData with only the specified exercises
            _exercises.value = filteredExercises
        }
    }

    private fun generateSetsAndReps(exercises: List<Exercise>) {
        val setsAndRepsMap = mutableMapOf<String, Pair<Int, Int>>()
        for (exercise in exercises) {
            val targetMuscle = exercise.target
            if (!setsAndRepsMap.containsKey(targetMuscle)) {
                val sets = Random.nextInt(2, 4)  // Generate random sets between 2 and 3
                val reps = Random.nextInt(3, 6)  // Generate random reps between 3 and 5
                setsAndRepsMap[targetMuscle] = Pair(sets, reps)
            }
            // Assign sets and reps to the exercise object
            val (sets, reps) = setsAndRepsMap[targetMuscle]!!
            exercise.sets = sets
            exercise.reps = reps
        }
    }

    private fun filterExercises(exercises: List<Exercise>): List<Exercise> {
        val allowedExercises = setOf(
            Pair("alternate lateral pulldown", "lats"),
//            Pair("barbell bent over row", "upper back"),
            Pair("assisted chest dip (kneeling)", "pectorals"),
//            Pair("barbell incline shoulder raise", "serratus anterior"

        )

        return exercises.filter { exercise ->
            allowedExercises.contains(Pair(exercise.name.lowercase(), exercise.target.lowercase()))
        }

    }
}