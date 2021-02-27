package com.example.todolistnotsmart
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.AutoText
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_layout.view.*

class TodoAdapter(
    private val todos:MutableList<Todo>
):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_layout,
                parent,
                false
            )
        )
    }
    fun addTodo(todo:Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteDoneTodo(){
        todos.removeAll{ todo->
            todo.isChecked
        }

        notifyDataSetChanged()
    }

    private fun toggleSt(tvTitle:TextView,isChecked:Boolean){
        if (isChecked){
            tvTitle.paintFlags = tvTitle.paintFlags or STRIKE_THRU_TEXT_FLAG

        }
        else{
            tvTitle.paintFlags = tvTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
      val curTodo = todos[position]
        holder.itemView.apply {
            //get data type i guess
            todoItemTextView.text = curTodo.title
            DoneCB.isChecked=curTodo.isChecked
            toggleSt(todoItemTextView,curTodo.isChecked)
            DoneCB.setOnCheckedChangeListener{ _, isChecked ->
                toggleSt(todoItemTextView,isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}