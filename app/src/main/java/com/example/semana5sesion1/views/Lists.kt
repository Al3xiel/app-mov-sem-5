package com.example.semana5sesion1.views

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.semana5sesion1.model.Usuario
import com.example.semana5sesion1.viewmodel.UsuViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun Lists(viewModel: UsuViewModel, context: Context, rememberScreen: NavHostController) {
    var listaUsuarios : MutableList<Usuario> by mutableStateOf(arrayListOf())

    viewModel.listUsers(context)
    listaUsuarios = viewModel.userslist

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "LISTADO DE USUARIOS",
            fontSize = 25.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold)

        LazyColumn (
            modifier = Modifier.fillMaxWidth()
        ){
            items(listaUsuarios) {
                Card (
                    onClick = {},
                    modifier = Modifier.padding(10.dp)
                        .fillMaxWidth()
                        .height(110.dp)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize().padding(5.dp)
                    ){
                        Column (
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .width(200.dp)
                        ){
                            Text(text = "${it.id}")
                            Text(text = "${it.nombre}")
                            Text(text = "${it.edad}")
                            Text(text = "${it.mail}")
                        }
                        IconButton(
                            onClick = {
                                viewModel.deleteUser(context, it.id)
                                viewModel.listUsers(context)
                            }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = null)
                        }
                        IconButton(
                            onClick = {
                                rememberScreen.navigate("S3/${it.id}")
                            }
                        ) {
                            Icon(Icons.Default.Create, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}