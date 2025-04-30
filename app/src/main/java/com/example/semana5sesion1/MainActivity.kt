package com.example.semana5sesion1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.semana5sesion1.ui.theme.Semana5Sesion1Theme
import com.example.semana5sesion1.viewmodel.UsuViewModel
import com.example.semana5sesion1.views.navigator.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel by viewModels<UsuViewModel>()
        setContent {
            Semana5Sesion1Theme {
                Navigation(viewModel,this)
            }
        }
    }
}
