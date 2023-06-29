package com.example.jetpackcomposetaskapp.ui.theme.addTasks.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskAppDatabase() : RoomDatabase() {
    abstract fun taskDAO(): TaskDAO
}
