package com.codingurkan.todolist2.util

import android.content.Context
import android.widget.Toast

fun showMessage(context: Context,text : String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}