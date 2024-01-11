package com.sample.todolistapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.todolistapp.databinding.FragmentTasksListBinding
import kotlinx.coroutines.launch

class TasksListFragment : Fragment() {

    private var _binding: FragmentTasksListBinding? = null

    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val tasksListViewModel: TasksListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksListBinding.inflate(inflater, container, false)

        binding.taskRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.addButton.setOnClickListener{
            findNavController().navigate(
                TasksListFragmentDirections.addTask()
            )
        }

                ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        // this method is called
                        // when the item is moved.
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        // this method is called when item is swiped.
                        // below line is to remove item from our array list.
                        viewLifecycleOwner.lifecycleScope.launch {
                            tasksListViewModel.deletetask(viewHolder.bindingAdapterPosition)
                        }
                    }
                    // at last we are adding this
                    // to our recycler view.
                }).attachToRecyclerView(binding.taskRecyclerView)





        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tasksListViewModel.tasks.collect { tasks ->
                    binding.taskRecyclerView.adapter = TasksListAdapter(tasks)

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}