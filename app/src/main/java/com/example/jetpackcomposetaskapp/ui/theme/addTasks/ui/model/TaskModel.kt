package com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui.model

data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    var taskName: String,
    val isSelected: Boolean = false,
)
