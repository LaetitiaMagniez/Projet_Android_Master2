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
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    onButtonClick: () -> Unit,
    onButton2Click: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Membres de l'équipe",
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
                Text("Voir la liste des Anime")
            }
        )
        Button(
            content = {
                Text("Authentification Firebase")
            },
            onClick = { onButton2Click() }
        )
    }
}