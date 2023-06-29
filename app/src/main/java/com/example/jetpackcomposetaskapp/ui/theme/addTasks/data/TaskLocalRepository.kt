package com.example.jetpackcomposetaskapp.ui.theme.addTasks.data

import com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskLocalRepository @Inject constructor(private val taskDAO: TaskDAO) {

    val tasks: Flow<List<TaskModel>> = taskDAO.getTask().map { taskEntities ->
        taskEntities.map { taskEntity ->
            TaskModel(
                id = taskEntity.id,
                taskName = taskEntity.taskName,
                isSelected = taskEntity.isSelected,
            )
        }
    }

    suspend fun addTask(taskModel: TaskModel) {
        taskDAO.addTask(taskModel.mapToData())
    }

    suspend fun editTask(taskModel: TaskModel) {
        taskDAO.editTask(taskModel.mapToData())
    }

    suspend fun deleteTask(taskModel: TaskModel) {
        taskDAO.deleteTask(taskModel.mapToData())
    }
}

fun TaskModel.mapToData() = TaskEntity(this.id, this.taskName, this.isSelected)
