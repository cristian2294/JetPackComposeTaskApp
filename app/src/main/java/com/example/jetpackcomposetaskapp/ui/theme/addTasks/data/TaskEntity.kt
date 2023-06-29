package com.example.jetpackcomposetaskapp.ui.theme.addTasks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey
    val id: Int,
    var taskName: String,
    val isSelected: Boolean = false,
)
