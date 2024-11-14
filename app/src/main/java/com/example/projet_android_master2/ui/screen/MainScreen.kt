package com.example.projet_android_master2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.projet_android_master2.R

@Composable
fun MainScreen(
    onButtonClick: () -> Unit,
    onButton2Click: () -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = context.getString(R.string.main_team_members),
            modifier = Modifier.padding(bottom = 8.dp))
        Text(
            text = "Laëtitia Magniez",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Dorian Gruny",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = onButtonClick,
            content = {
                Text(context.getString(R.string.main_anime_list))
            }
        )
        Button(
            content = {
                Text(context.getString(R.string.main_auth_firebase))
            },
            onClick = { onButton2Click() }
        )
    }
}
