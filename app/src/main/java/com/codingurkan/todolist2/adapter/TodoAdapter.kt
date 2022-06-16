package com.codingurkan.todolist2.adapter

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingurkan.todolist2.databinding.RecyclerRowBinding
import com.codingurkan.todolist2.model.Todo
import org.w3c.dom.Text

class TodoAdapter(private var todoList : MutableList<Todo>,
private val itemClickListener : ItemClickListener) : RecyclerView.Adapter<TodoAdapter.TodoVH>() {




    inner class TodoVH(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    fun addTodo(todo : Todo){
        todoList.add(todo)
        notifyItemInserted(todoList.size-1)
    }
    fun deleteTodo(){
        todoList.removeAll {
            it.isChecked
        }
        notifyDataSetChanged()
    }
    private fun toggleStrikeThrough(tvTodoTitle : TextView, isChecked : Boolean){
        if (isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags =tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoVH {
        val view = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoVH(view)
    }

    override fun onBindViewHolder(holder: TodoVH, position: Int) {
        val curTodo = todoList[position]
        holder.binding.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle,curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, b ->
                toggleStrikeThrough(tvTodoTitle, b)
                curTodo.isChecked = !curTodo.isChecked

                tvTodoTitle.setOnClickListener {
                    itemClickListener.onclick(curTodo.isChecked.toString())
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
    interface ItemClickListener{
        fun onclick(text : String)
    }
}