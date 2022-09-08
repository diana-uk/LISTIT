package com.diana_ukrainsky.listit.model.data

import com.diana_ukrainsky.listit.model.local.TodoDatabase

/* "Repository" is single point of control, do all decision-making regarding Apps data
 I used a dependency injection to supply the TodoDatabase instance to this repository */
class TodoRepository(private val todoDao: TodoDAO) {

    val todos = todoDao.getAllTodos()

    suspend fun insert(todo:Todo) {
        todoDao.insertTodo(todo)
    }

    suspend fun update(todo:Todo) {
        todoDao.updateTodo(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    suspend fun deleteAll(){
        todoDao.deleteAll()
    }
}