package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val todoList: ArrayList<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    var onItemClick: ((Todo) -> Unit)? = null

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameView: TextView = itemView.findViewById(R.id.textView1)
        val commentView: TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.nameView.text = todo.name
        holder.commentView.text = todo.comment

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(todo)
        }


    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}


