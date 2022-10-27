package com.example.todoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var commentEditText: EditText
    private lateinit var btnAdd: Button
    private lateinit var recycleView: RecyclerView
    private lateinit var todoList: ArrayList<Todo>
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.editText1)
        commentEditText = findViewById(R.id.editText2)
        btnAdd = findViewById(R.id.btnAdd)
        recycleView = findViewById(R.id.recycleView1)
        todoList = ArrayList()
        todoAdapter = TodoAdapter(todoList)


        init()



        recycleView.layoutManager = LinearLayoutManager(this)

        recycleView.adapter = todoAdapter


        todoAdapter.onItemClick = {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("todo", it)
            startActivity(intent)
        }


        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(10, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedTodo: Todo = todoList[viewHolder.adapterPosition]

                val position = viewHolder.adapterPosition

                todoList.removeAt(viewHolder.adapterPosition)

                todoAdapter.notifyItemRemoved(viewHolder.adapterPosition)

                Snackbar.make(recycleView, "DELETED " + deletedTodo.name, Snackbar.LENGTH_LONG)
                    .setAction(
                        "Undo", View.OnClickListener {
                            todoList.add(position, deletedTodo)

                            todoAdapter.notifyItemInserted(position)
                        }
                    ).show()
            }
        }).attachToRecyclerView(recycleView)
    }

    private fun init() {


        btnAdd.setOnClickListener {
            val name: String = nameEditText.text.toString()
            val comment: String = commentEditText.text.toString()
            if (name != "" && comment != "") {

                todoList.add(Todo(name, comment))

                nameEditText.setText("")
                commentEditText.setText("")
                todoAdapter.notifyDataSetChanged()

                return@setOnClickListener
            } else {
                Toast.makeText(this, "Please fill out all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }


//    override fun onPause() {
//        super.onPause()
//        val bundle: Bundle = bundleOf()
//        bundle.putParcelableArrayList("notes", todoList)
//        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
//        with (sharedPref.edit()){
//            putStringSet("notes", )
//        }
//    }

}






