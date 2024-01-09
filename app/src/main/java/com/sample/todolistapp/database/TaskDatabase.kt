package com.sample.todolistapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.todolistapp.Task

@Database(entities = [ Task::class ], version=1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

}