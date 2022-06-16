package com.codingurkan.todolist2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingurkan.todolist2.R
import com.codingurkan.todolist2.adapter.TodoAdapter
import com.codingurkan.todolist2.databinding.ActivityMainBinding
import com.codingurkan.todolist2.model.Todo
import com.codingurkan.todolist2.util.showMessage

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        buttonClick()
        setupAdapter()
    }
    private fun initBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun buttonClick(){
        binding.apply {
            btnAddTodo.setOnClickListener {
                val todoTitle = binding.etTodoTitle.text.toString()
                if (todoTitle.isNotEmpty()){
                    val todo =Todo(todoTitle)
                    adapter.addTodo(todo)
                    binding.etTodoTitle.text.clear()
                }
            }
        binding.btnDeleteDoneTodo.setOnClickListener {
            adapter.deleteTodo()
        }
        }
    }
    private fun setupAdapter(){
        adapter = TodoAdapter(mutableListOf(),object : TodoAdapter.ItemClickListener{
            override fun onclick(text: String) {
                showMessage(this@MainActivity,text)
            }
        })
        binding.rvTodoItems.adapter = adapter
        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)
    }
}