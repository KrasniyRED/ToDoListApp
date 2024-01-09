package com.sample.todolistapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sample.todolistapp.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY priority")
    fun getTasks(): Flow<List<Task>>
    @Insert
    suspend fun addTask(crime: Task)


}