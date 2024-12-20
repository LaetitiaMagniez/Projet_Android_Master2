package com.example.projet_android_master2.ui.screen

import android.widget.Toast
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
import com.example.projet_android_master2.ui.viewmodel.AuthentificationViewModel
import androidx.compose.runtime.livedata.observeAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirebaseAuthScreen(
    navController: NavController,
    onButton3Click: () -> Unit,
) {
    val viewModel: AuthentificationViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val user by viewModel.mCurrentUser.observeAsState(null)
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(context.getString(R.string.auth_login)) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
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
                label = { Text(context.getString(R.string.auth_password)) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        viewModel.loginUser(email, password)
                        Toast.makeText(context, context.getString(R.string.auth_toast_login), Toast.LENGTH_SHORT).show()
                    } else {
                        errorMessage = context.getString(R.string.auth_toast_error_login)
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(context.getString(R.string.auth_login))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onButton3Click,
                modifier = Modifier.fillMaxWidth(),
                content = {
                    Text(context.getString(R.string.auth_register))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (user != null) {
                Button(
                    onClick = {
                        viewModel.disconnectUser()
                        Toast.makeText(context, context.getString(R.string.auth_toast_disconnect), Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(context.getString(R.string.auth_disconnect))
                }
            }

            OutlinedCard(
                colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    if (user != null) {
                        val userId = user!!.uid
                        context.getString(R.string.auth_user_id, userId)
                    } else {
                        context.getString(R.string.auth_no_user_logged_in)
                    }
                )
            }
        }
    }
}
