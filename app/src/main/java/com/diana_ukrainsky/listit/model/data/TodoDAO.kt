package com.diana_ukrainsky.listit.model.data

import androidx.lifecycle.LiveData
import androidx.room.*

/* Room will recognize this interface as a Data Access Object
interface after seeing this @Dao annotation */
@Dao
interface TodoDAO {

    /**
     * The function purpose is to add a Todo item
     * @param todo the todo item you would like to add
     * @return Long of the rowId for verification
     */
    @Insert
    suspend fun insertTodo(todo : Todo) : Long

    /**
     * The function purpose is to get all Todos as a LiveData list.
     * No need to execute this function in a background thread using coroutines
     * since this function returns LiveData Room library do its work from a background thread.
     * @return Todo list as a Live Data
     */
    @Query("SELECT * FROM todo_data_table")
     fun getAllTodos() : LiveData<List<Todo>>

    /**
     * The function purpose is to update an item of the Todo list
     * @param todo the todo item you would like to update
     * @return Int of the number of rows updated in the room database
     */
    @Update
    suspend fun updateTodo(todo : Todo) : Int

    /**
     * The function purpose is to delete an item of the Todo list
     * @param todo the todo item you would like to delete
     * @return Int of the number of rows deleted
     */
    @Delete
    suspend fun deleteTodo(todo : Todo)  : Int

    @Query("DELETE FROM todo_data_table")
    suspend fun deleteAll()
}