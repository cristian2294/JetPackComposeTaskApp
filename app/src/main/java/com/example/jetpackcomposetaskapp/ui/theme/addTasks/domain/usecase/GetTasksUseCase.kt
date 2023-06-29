package com.example.jetpackcomposetaskapp.ui.theme.addTasks.domain.usecase

import com.example.jetpackcomposetaskapp.ui.theme.addTasks.data.TaskLocalRepository
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val taskLocalRepository: TaskLocalRepository,
) {
    operator fun invoke(): Flow<List<TaskModel>> = taskLocalRepository.tasks
}
