package com.sample.todolistapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Task(
    @PrimaryKey val id:UUID,
    val title:String,
    val priority: Int
)
