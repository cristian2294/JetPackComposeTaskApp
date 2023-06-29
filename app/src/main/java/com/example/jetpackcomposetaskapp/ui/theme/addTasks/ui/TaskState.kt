package com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui

import com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui.model.TaskModel

sealed class TaskState {
    object Loading : TaskState()
    data class Success(val tasks: List<TaskModel>) : TaskState()
    data class Error(val throwable: Throwable) : TaskState()
}
