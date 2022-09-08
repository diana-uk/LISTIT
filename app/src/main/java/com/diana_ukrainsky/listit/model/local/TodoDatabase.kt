package com.diana_ukrainsky.listit.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diana_ukrainsky.listit.model.data.Todo
import com.diana_ukrainsky.listit.model.data.TodoDAO

@Database(entities = [Todo::class],version = 1)
// Class AppDatabase is simply holder for all of the data access objects
abstract class TodoDatabase  : RoomDatabase() {

    /* The Volatile annotation makes the field immediately made visible
     to other threads */
    abstract  val todoDAO : TodoDAO

    companion object {
        @Volatile
        private var INSTANCE : TodoDatabase ?= null
        fun getInstance(context: Context) : TodoDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase ::class.java,
                        "todo_data_databse"
                    ).build()
                }
                return instance
            }
        }
    }


}

