package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val todo = intent.getParcelableExtra<Todo>("todo")
        if (todo != null){
            val nameTextView: TextView = findViewById(R.id.detailsTV1)
            val commentTextView: TextView = findViewById(R.id.detailsTV2)

            nameTextView.text = todo.name
            commentTextView.text = todo.comment
        }
    }
}