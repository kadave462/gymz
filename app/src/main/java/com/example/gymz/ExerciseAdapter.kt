package com.example.gymz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ExerciseAdapter(var exercises: List<Exercise>):
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val targetTextView: TextView = itemView.findViewById(R.id.targetTextView)
        val gifImageView: ImageView = itemView.findViewById(R.id.gifImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_item, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.nameTextView.text = exercise.name

        // Display sets and reps in the targetTextView (if available)
        val setsRepsText = if (exercise.sets!= null && exercise.reps!= null) {
            "${exercise.target} - ${exercise.sets} serie de ${exercise.reps} repetition"
        } else {
            exercise.target
        }
        holder.targetTextView.text = setsRepsText

        Glide.with(holder.itemView.context)
            .load(exercise.gifUrl)
            .into(holder.gifImageView)
    }

    override fun getItemCount(): Int = exercises.size
}