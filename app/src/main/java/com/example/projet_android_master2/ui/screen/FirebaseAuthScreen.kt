package com.example.projet_android_master2.ui.screen

import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.projet_android_master2.R
import com.example.projet_android_master2.firebase.viewmodel.FirebaseAuthViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun FirebaseAuthScreen(
    viewModel: FirebaseAuthViewModel = viewModel(),
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val user = Firebase.auth.currentUser
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(context.getString(R.string.auth_email)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(context.getString(R.string.auth_password))},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.loginUser(email, password)
                    Toast.makeText(context, "vous êtes connecté", Toast.LENGTH_SHORT).show()
                } else {
                    errorMessage = "Merci de renseigner un mail et un mot de passe d'au moins 6 caractères."
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(context.getString(R.string.auth_login))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.registerNewUser(email, password)
                    Toast.makeText(context, "Vous êtes inscrit", Toast.LENGTH_SHORT).show()

                } else {
                    errorMessage = "Please enter both email and password."
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(context.getString(R.string.auth_register))
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (user != null) {
                    viewModel.disconnectUser()
                    Toast.makeText(context, "Vous êtes déconnecté", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "impossible de se déconnecter", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(context.getString(R.string.auth_disconnect))
        }

        OutlinedCard(
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                if (user != null) {
                    // Récupérer l'UID de l'utilisateur et l'afficher
                    val userId = user.uid
                    "User ID : $userId"
                } else {
                    // Afficher un message si aucun utilisateur n'est connecté
                    "Aucun utilisateur connecté"
                }
            )
        }
    }
}
