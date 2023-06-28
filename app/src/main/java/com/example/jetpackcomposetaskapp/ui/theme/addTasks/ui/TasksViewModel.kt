package com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui.model.TaskModel
import javax.inject.Inject

class TasksViewModel @Inject constructor() : ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> get() = _showDialog

    private val _tasks = mutableStateListOf<TaskModel>()
    val tasks: List<TaskModel> get() = _tasks

    fun onDialogClosed() {
        _showDialog.value = false
    }

    fun onDialogOpen() {
        _showDialog.value = true
    }

    fun onTaskAdded(taskName: String) {
        _tasks.add(TaskModel(taskName = taskName))
        _showDialog.value = false
    }

    fun onCheckBoxChanged(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let {
            it.copy(isSelected = !it.isSelected)
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        val task = _tasks.find{
            it.id == taskModel.id
        }
        _tasks.remove(task)
    }
}
