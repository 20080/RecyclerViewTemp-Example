package com.example.todolistnotsmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        ToDoItemsRV.adapter = todoAdapter
        ToDoItemsRV.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener{
            val todoTitle = ToDoEditBox.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo =Todo(todoTitle)
                todoAdapter.addTodo(todo)
                ToDoEditBox.text.clear()
            }
        }

        removeDone.setOnClickListener{
            todoAdapter.deleteDoneTodo()
        }
    }
}