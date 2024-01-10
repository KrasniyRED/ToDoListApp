package com.sample.todolistapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.todolistapp.databinding.ListitemBinding

class TaskHolder(
    private val binding: ListitemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task) {
        binding.taskTitle.text = task.title

        binding.priorityView.text = task.priority.toString()

        if(task.priority == 1){
            binding.priorityView.setBackgroundResource(R.color.High_priority_color)
        }
        if(task.priority == 2){
            binding.priorityView.setBackgroundResource(R.color.Medium_priority_color)
        }
        if(task.priority == 3){
            binding.priorityView.setBackgroundResource(R.color.Low_priority_color)
        }

    }

}

class TasksListAdapter(
    private val tasks: List<Task>
) : RecyclerView.Adapter<TaskHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : TaskHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListitemBinding.inflate(inflater, parent, false)
        return TaskHolder(binding)
    }
    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }
    override fun getItemCount() = tasks.size
}