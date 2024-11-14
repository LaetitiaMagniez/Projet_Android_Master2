package com.example.projet_android_master2.ui.screen

import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirebaseRegisterScreen(
    navController: NavController,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(context.getString(R.string.auth_register)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = context.getString(R.string.anime_back_button)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        // Appliquez le padding de Scaffold à MyFirebaseRegisterScreen
        MyFirebaseRegisterScreen(modifier = Modifier.padding(padding))
    }
}

@Composable
fun MyFirebaseRegisterScreen(modifier: Modifier) {
    val viewModel: FirebaseAuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp)
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
            label = { Text(context.getString(R.string.auth_password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.registerNewUser(email, password)
                    Toast.makeText(context, "Vous êtes inscrit", Toast.LENGTH_SHORT).show()
                } else {
                    errorMessage = "Veuillez entrer un email et un mot de passe."
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
    }
}
