package com.example.semana5sesion1.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.semana5sesion1.model.Usuario
import com.example.semana5sesion1.model.openHelper.OpenHelper

class UsuViewModel (): ViewModel(){

    var userslist: MutableList<Usuario> by mutableStateOf(arrayListOf())

    fun insertUser(context:Context,usu: Usuario){
        var dbHelper = OpenHelper(context)
        dbHelper.newUser(usu)
    }

    fun listUsers(context: Context){
        var dbHelper = OpenHelper(context)
        userslist = dbHelper.readData()
    }

    fun deleteUser(context: Context, id: Int){
        var dbHelper = OpenHelper(context)
        dbHelper.deleteUser(id)
    }
}