package com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui.model

data class TaskModel(
    val id: Long = System.currentTimeMillis(),
    var taskName: String,
    val isSelected: Boolean = false,
)
