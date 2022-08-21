package com.diana_ukrainsky.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diana_ukrainsky.todolist.R.layout.item_todo
import kotlinx.android.synthetic.main.item_todo.view.*

// This class represents the main logic in the app
// "(" represents what the constructor receives
class TodoAdapter (
    private val todos: MutableList<Todo>



    )  : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    // ":" Inherits
    // Class that holds views
    // Takes reference to the view it should hold = itemView: View
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) // (itemView) constructor receives

    // This function will create the create to do view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return  TodoViewHolder(
            // Will make the file from xml to kotlin
            LayoutInflater.from(parent.context).inflate(
                item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        // Inserts item to the end of the list
        notifyItemInserted(todos.size -1 )
    }

    fun deleteDoneTodos() {
        todos.removeAll{todo ->
            todo.isChecked
        }
        // Update the whole list
        notifyDataSetChanged()
    }


private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
    if(isChecked) {
        // STRIKE_THRU_TEXT_FLAG is
        tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
    } else {
        tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
    }

}
    // Will be called when a new view holder item is visible(checkbox or the text)
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        // Current to do item we are talking about
        val curTodo = todos[position]
        // will use the view holder to access the views ".apply" is shortcut
        holder.itemView.apply {
            // we want to set the title of the view to curTodo
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle,curTodo.isChecked)
            cbDone.setOnCheckedChangeListener {_, isChecked ->
                toggleStrikeThrough(tvTodoTitle,isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }
    // Return the amount of items in our list so the recyclerview will know how much items there are
    override fun getItemCount(): Int {
        return todos.size
    }
}