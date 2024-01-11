package com.sample.todolistapp

import android.content.Context
import androidx.room.Room
import com.sample.todolistapp.database.TaskDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import java.util.UUID

private const val DATABASE_NAME = "task-database"

class TaskRepository private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
) {
    private val database: TaskDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            TaskDatabase::class.java,
            DATABASE_NAME
        )
        .build()

    fun getTasks(): Flow<List<Task>> = database.taskDao().getTasks()
    suspend fun addTask(task: Task) {
        database.taskDao().addTask(task)
    }
    suspend fun deletetask(id: UUID) {
        database.taskDao().deletetask(id)
    }


    companion object {
        private var INSTANCE: TaskRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = TaskRepository(context)
            }
        }
        fun get(): TaskRepository {
            return INSTANCE ?:
            throw IllegalStateException("TaskRepository must be initialized")
        }
    }
}