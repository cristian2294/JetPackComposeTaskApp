package com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.domain.usecase.AddTaskUseCase
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.domain.usecase.DeleteTaskUseCase
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.domain.usecase.EditTaskUseCase
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.domain.usecase.GetTasksUseCase
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui.TaskState.Error
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui.TaskState.Loading
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui.TaskState.Success
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTasksUseCase: GetTasksUseCase,
) : ViewModel() {

    val uiState: StateFlow<TaskState> = getTasksUseCase.invoke()
        .map(::Success)
        .catch { Error(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            Loading,
        )

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> get() = _showDialog

    fun onDialogClosed() {
        _showDialog.value = false
    }

    fun onDialogOpen() {
        _showDialog.value = true
    }

    fun onTaskAdded(taskName: String) {
        _showDialog.value = false

        viewModelScope.launch {
            addTaskUseCase(TaskModel(taskName = taskName))
        }
    }

    fun onCheckBoxChanged(taskModel: TaskModel) {
        viewModelScope.launch {
            editTaskUseCase(taskModel.copy(isSelected = !taskModel.isSelected))
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase(taskModel)
        }
    }
}
