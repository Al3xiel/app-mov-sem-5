package com.example.semana5sesion1.views

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.semana5sesion1.model.Usuario
import com.example.semana5sesion1.viewmodel.UsuViewModel

@Composable
fun Edit(viewModel: UsuViewModel, context: Context, nav: NavHostController) {
    val userId = nav.currentBackStackEntry?.arguments?.getString("userId")?.toIntOrNull()

    val user = viewModel.userslist.find { it.id == userId }
        ?: return // Handle the case where the user is not found

    if(user == null){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Error while fetchomg user data",
                fontSize = 25.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            OutlinedButton(
                onClick = {
                    nav.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(text = "Go Back")
            }
        }
    }else{
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            var txtNom by remember { mutableStateOf("") }
            var txtEdad by remember { mutableStateOf("") }
            var txtMail by remember { mutableStateOf("") }

            Text(
                text="Edit User",
                fontSize = 25.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = txtNom,
                modifier = Modifier
                    .padding(7.dp)
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                label = { Text(text="Nombre ") },
                placeholder = { Text(text= user.nombre) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        tint = Color.Gray,
                        contentDescription = ""
                    )
                },
                onValueChange = {
                    txtNom=it
                }
            )

            OutlinedTextField(
                value = txtEdad,
                modifier = Modifier
                    .padding(7.dp)
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                label = { Text(text="Edad") },
                placeholder = { Text(text=user.edad.toString()) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        tint = Color.Gray,
                        contentDescription = ""
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    txtEdad=it
                }
            )

            OutlinedTextField(
                value = txtMail,
                modifier = Modifier
                    .padding(7.dp)
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                label = { Text(text="E-Mail") },
                placeholder = { Text(text=user.mail) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.MailOutline,
                        tint = Color.Gray,
                        contentDescription = ""
                    )
                },
                onValueChange = {
                    txtMail=it
                }
            )

            OutlinedButton(
                onClick = {
                    viewModel.editUser(context,
                        Usuario(user.id,
                            txtNom.ifEmpty { user.nombre },
                            if (txtEdad.isNotEmpty()) txtEdad.toInt() else user.edad,
                            txtMail.ifEmpty { user.mail })
                    )
                    txtNom = ""
                    txtEdad = ""
                    txtMail = ""
                    nav.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Guardar Datos")
            }

            OutlinedButton(
                onClick = {
                    nav.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Cancelar")
            }
        }
    }
}