package com.diana_ukrainsky.listit.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diana_ukrainsky.listit.TodoAdapter
import com.diana_ukrainsky.listit.model.data.TodoRepository
import com.diana_ukrainsky.listit.viewmodel.TodosViewModel

/* Factory handles the need of creation of one and only object of ViewModel
 for the Dependency Injection as a way to modularize my code */
class TodosViewModelFactory(private val todoRepository: TodoRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TodosViewModel::class.java)) {
            return TodosViewModel(todoRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}