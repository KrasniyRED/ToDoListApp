package com.sample.todolistapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sample.todolistapp.Task
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY priority")
    fun getTasks(): Flow<List<Task>>
    @Insert
    suspend fun addTask(crime: Task)
    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deletetask(id: UUID)


}