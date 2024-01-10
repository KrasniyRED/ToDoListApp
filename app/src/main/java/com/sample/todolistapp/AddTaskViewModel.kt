package com.sample.todolistapp

import androidx.lifecycle.ViewModel

class AddTaskViewModel : ViewModel() {
    private val taskRepository = TaskRepository.get()
    suspend fun addTask(task: Task) {
        taskRepository.addTask(task)
    }


}