package com.diana_ukrainsky.listit.viewmodel

import android.graphics.Paint
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.diana_ukrainsky.listit.TodoAdapter
import com.diana_ukrainsky.listit.model.data.Todo
import com.diana_ukrainsky.listit.model.data.TodoRepository
import kotlinx.coroutines.launch

class TodosViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    val todos = todoRepository.todos
    private var isUpdateOrDelete = false
    private lateinit var todoToUpdateOrDelete: Todo

    val inputTitle = MutableLiveData<String?>()
    val isChecked = MutableLiveData<Boolean>()
    val addOrUpdateButtonText = MutableLiveData<String>()
    val deleteOrDeleteDoneText = MutableLiveData<String>()

//    lateinit var todoAdapter : TodoAdapter

    init {
        addOrUpdateButtonText.value = "Add Todo"
        deleteOrDeleteDoneText.value = "Delete Done"
        //todoAdapter = TodoAdapter(mutableListOf())
    }

    fun addOrUpdate() {
        if(isUpdateOrDelete) {
            todoToUpdateOrDelete.title = inputTitle.value!!
            update(todoToUpdateOrDelete)
        }
        else {
            // This value should not be null
            val title = inputTitle.value!!
            insert(Todo(0,title))
            inputTitle.value = null
        }
    }

    fun deleteOrDeleteDone() {
        if(isUpdateOrDelete)
            delete(todoToUpdateOrDelete)
            else
                deleteAllDone()
    }

    fun onTodoCheckedChanged(todo:Todo) {
        todoToUpdateOrDelete.isChecked = !todo.isChecked
        Log.i("My_Tag","is checked: "+todoToUpdateOrDelete.isChecked)
        update(todoToUpdateOrDelete)
    }

    fun insert(todo: Todo)  =  viewModelScope.launch {
            todoRepository.insert(todo)
    }

    fun update(todo: Todo)  = viewModelScope.launch {
        todoRepository.update(todo)
        inputTitle.value = null
        isUpdateOrDelete = false
        addOrUpdateButtonText. value = "Save"
        deleteOrDeleteDoneText.value = "Delete Done"
    }

    fun delete(todo: Todo) = viewModelScope.launch {
        todoRepository.delete(todo)
    }

    fun deleteAllDone() = viewModelScope.launch {
        todos.value?.forEach {
            if(it.isChecked)
                todoRepository.delete(it)
        }
       // todoRepository.deleteAll()
        inputTitle.value = null
        isUpdateOrDelete = false
        addOrUpdateButtonText. value = "Save"
        deleteOrDeleteDoneText.value = "Delete Done"
    }

    fun initUpdateAndDelete(todo: Todo) {
        inputTitle.value = todo.title
        isUpdateOrDelete = true
        todoToUpdateOrDelete = todo
        addOrUpdateButtonText. value = "Update"
        deleteOrDeleteDoneText.value = "Delete"
    }

//    fun getAdapter():  TodoAdapter{
//        return todoAdapter
//    }

//    fun setAdapterData(data: List<Todo>) {
//        todoAdapter.setDataList(data)
//        todoAdapter.notifyDataSetChanged()
//    }
//
//    fun getTodos() = todoRepository.getTodos()
//
//    fun addTodo(todo: Todo) = todoRepository.addTodo(todo)
}