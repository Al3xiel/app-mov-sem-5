package com.example.semana5sesion1.views.navigator

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.semana5sesion1.viewmodel.UsuViewModel
import com.example.semana5sesion1.views.Edit
import com.example.semana5sesion1.views.Home
import com.example.semana5sesion1.views.Lists

@Composable
fun Navigation(viewModel: UsuViewModel, context: Context){
    val rememberScreen= rememberNavController()
    NavHost(navController=rememberScreen, startDestination="S1"){
        composable("S1"){Home(viewModel, context, rememberScreen)}
        composable("S2"){Lists(viewModel, context, rememberScreen)}
        composable("S3/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            val usuario = viewModel.userslist.find { it.id == userId }
            if (usuario != null) {
                Edit(viewModel, context, rememberScreen, usuario)
            }
        }
    }
}
