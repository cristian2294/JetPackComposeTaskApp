package com.example.jetpackcomposetaskapp.ui.theme.addTasks.data.di

import android.content.Context
import androidx.room.Room
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.data.TaskAppDatabase
import com.example.jetpackcomposetaskapp.ui.theme.addTasks.data.TaskDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskAppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = TaskAppDatabase::class.java,
            name = "TaskAppDatabase",
        ).build()
    }

    @Provides
    fun provideTaskDAO(taskAppDatabase: TaskAppDatabase): TaskDAO = taskAppDatabase.taskDAO()
}
