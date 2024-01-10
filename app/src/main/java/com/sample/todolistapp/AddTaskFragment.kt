package com.sample.todolistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sample.todolistapp.databinding.FragmentAddTaskBinding
import kotlinx.coroutines.launch
import java.util.UUID

class AddTaskFragment: Fragment() {

    private lateinit var task: Task


    private val addTaskViewModel: AddTaskViewModel by viewModels()

    private var _binding: FragmentAddTaskBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaskBinding.inflate(layoutInflater, container, false)
            binding.addBtn.setOnClickListener{
                var priority = 0
                if(binding.prior1.isChecked){
                    priority = 1
                }
                if(binding.prior2.isChecked){
                    priority = 2
                }
                if(binding.prior3.isChecked) {
                    priority = 3
                }

                viewLifecycleOwner.lifecycleScope.launch {
                    task = Task(
                        id = UUID.randomUUID(),
                        title = binding.taskTitle.text.toString(),
                        priority = priority
                    )
                    addTaskViewModel.addTask(task)
                    findNavController().navigate(
                        AddTaskFragmentDirections.backPath()
                    )
                }

            }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}