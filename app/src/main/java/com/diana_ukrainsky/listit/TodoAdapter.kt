package com.diana_ukrainsky.listit

import android.graphics.Paint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.diana_ukrainsky.listit.databinding.ItemTodoBinding
import com.diana_ukrainsky.listit.model.data.Todo

// This class represents the main logic in the app
// "(" represents what the constructor receives

class TodoAdapter(private val todos: List<Todo>,private val clickListener: OnItemClickListener)
    : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    // ":" Inherits
    // Class that holds views
    // Takes reference to the view it should hold = itemView: View

    // This function will create the create to do view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
            // Will make the file from xml to kotlin
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding : ItemTodoBinding = DataBindingUtil.inflate(layoutInflater,R.layout.item_todo,parent,false)

        return TodoViewHolder(binding)
    }

    // Return the amount of items in our list so the recyclerview will know how much items there are
    override fun getItemCount(): Int {
        return todos.size
    }

    // Will be called when a new view holder item is visible(checkbox or the text)
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position],clickListener)
    }


    class TodoViewHolder(val binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo, clickListener : OnItemClickListener) {
            binding.apply {
                itemTodoTodoTitleTV.text = todo.title
                itemTodoItemLayoutLayout.setOnClickListener {
                    clickListener.onItemClick(todo)

                    itemTodoCbDoneCB.setOnCheckedChangeListener { button, isChecked ->
                        clickListener.onCheckBoxClick(todo)

                        if (isChecked) {
                            // STRIKE_THRU_TEXT_FLAG is
                            itemTodoTodoTitleTV.paintFlags = itemTodoTodoTitleTV.paintFlags or STRIKE_THRU_TEXT_FLAG
                        } else {
                            itemTodoTodoTitleTV.paintFlags = itemTodoTodoTitleTV.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
                        }
                    }
                }



                }

            }
        }

         fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
            if (isChecked) {
                // STRIKE_THRU_TEXT_FLAG is
                tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
            } else {
                tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            }

        }// (itemView) constructor receives


//    fun addTodo(todo: Todo) {
//        todos.add(todo)
//        // Inserts item to the end of the list
//        notifyItemInserted(todos.size -1 )
//    }
//

//    fun deleteDoneTodos() {
//        todos.removeAll{todo ->
//            todo.isChecked
//        }
//        // Update the whole list
//        notifyDataSetChanged()
//    }
interface OnItemClickListener {
    fun onItemClick(todo: Todo)
    fun onCheckBoxClick(todo: Todo)
}

    }



