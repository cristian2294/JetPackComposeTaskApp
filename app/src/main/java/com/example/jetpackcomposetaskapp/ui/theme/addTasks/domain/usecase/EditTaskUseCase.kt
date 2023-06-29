package com.example.jetpackcomposetaskapp.ui.theme.addTasks.domain.usecase

import com.example.jetpackcomposetaskapp.ui.theme.addTasks.data.TaskLocalRepository
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui.model.TaskModel
import javax.inject.Inject

class EditTaskUseCase @Inject constructor(private val taskLocalRepository: TaskLocalRepository) {
    suspend operator fun invoke(taskModel: TaskModel) = taskLocalRepository.editTask(taskModel)
}
