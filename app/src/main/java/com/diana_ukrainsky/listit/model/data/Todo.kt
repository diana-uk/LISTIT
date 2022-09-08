package com.diana_ukrainsky.listit.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_data_table")
// Kotlic data class just to hold data
data class Todo (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_item_id")
    val id : Int,
    @ColumnInfo(name = "todo_title")
    var title: String,
    @ColumnInfo(name = "todo_is_checked")
    var isChecked: Boolean = false
)
