package com.diana_ukrainsky.listit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.diana_ukrainsky.listit.R
import com.diana_ukrainsky.listit.TodoAdapter
import com.diana_ukrainsky.listit.databinding.ActivityMainBinding
import com.diana_ukrainsky.listit.model.data.Todo
import com.diana_ukrainsky.listit.model.data.TodoRepository
import com.diana_ukrainsky.listit.model.local.TodoDatabase
import com.diana_ukrainsky.listit.viewmodel.TodosViewModel

// View only calls functions on the ViewModel and
class MainActivity : AppCompatActivity(),TodoAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoViewModel : TodosViewModel
    // promise to Kotlin that we'll initialize this later
   // private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val todoDao = TodoDatabase.getInstance(application).todoDAO
        val todoRepository = TodoRepository(todoDao)
        val todoFactory = TodosViewModelFactory(todoRepository)
        todoViewModel = ViewModelProvider(this,todoFactory).get(TodosViewModel::class.java)
        binding.myViewModel = todoViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
//        // Empty list because we want the list to be empty at first
//        todoAdapter = TodoAdapter(mutableListOf())
//
//        rvTodoItems.adapter = todoAdapter
//        rvTodoItems.layoutManager = LinearLayoutManager(this)
//
//        btnAddTodo.setOnClickListener {
//            val todoTitle = etTodoTitle.text.toString()
//            if(todoTitle.isNotEmpty()) {
//                val todo = Todo(todoTitle) // False by default
//                todoAdapter.addTodo(todo)
//                etTodoTitle.text.clear()
//            }
//        }
//        btnDeleteDoneTodos.setOnClickListener {
//            todoAdapter.deleteDoneTodos()
//        }
    }

//    private fun initializeUi() {
//        val factory = InjectorUtils.provideTodosViewModelFactory()
//        val viewModel =ViewModelProviders.of(this,factory)
//            /*  Asking the viewModel provider to provide me with already
//                 present or actually instantiate if it's not present todos view model
//                  for me */
//            .get(TodosViewModel::class.java)
//
//        viewModel.getTodos().observe(this, Observer { todo->
//           // Update the UI
//            todo.forEach {todo->
//                todoAdapter.addTodo(todo)
//            }
//
//        })
//        btnAddTodo.setOnClickListener {
//            val todoTitle = etTodoTitle.text.toString()
//            if(todoTitle.isNotEmpty()) {
//                val todo = Todo(todoTitle) // False by default
//                todoAdapter.addTodo(todo)
//                viewModel.addTodo(todo)
//                etTodoTitle.text.clear()
//            }
//        }
//
//        btnDeleteDoneTodos.setOnClickListener {
//            todoAdapter.deleteDoneTodos()
//        }
//
//    }

    private fun initRecyclerView() {
        binding.mainActivityRvTodoItemsRV.layoutManager = LinearLayoutManager(this)
        displayTodosList()
    }

    // Display data on the recyclerView
    private fun displayTodosList() {
            todoViewModel.todos.observe(this, Observer {
                    binding.mainActivityRvTodoItemsRV.adapter = TodoAdapter(it, this )
            })
        }

    override fun onItemClick(todo: Todo) {
        Toast.makeText(this,"Selected title is ${todo.title}",Toast.LENGTH_LONG).show()
        todoViewModel.initUpdateAndDelete(todo)
    }

    override fun onCheckBoxClick(todo: Todo) {
        todoViewModel.onTodoCheckedChanged(todo)
    }
}