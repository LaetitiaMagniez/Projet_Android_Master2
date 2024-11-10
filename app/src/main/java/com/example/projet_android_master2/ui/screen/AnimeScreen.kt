package com.example.projet_android_master2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.projet_android_master2.ui.viewmodel.AnimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeScreen(
    navController: NavController,
    viewModel: AnimeViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Liste Animes") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Row {
                Button(
                    modifier = Modifier.weight(1f),
                    content = { Text("Ajouter") },
                    onClick = { viewModel.insertNewQuote() }
                )
                Button(
                    modifier = Modifier.weight(1f),
                    content = { Text("Supprimer") },
                    onClick = { viewModel.deleteAllQuote() }
                )
            }
        }
    ) { padding ->
        MyAnimeScreen(modifier = Modifier.padding(padding))
    }
}

@Composable
private fun MyAnimeScreen(modifier: Modifier) {
    val viewModel: AnimeViewModel = viewModel()
    val list = viewModel.anime.collectAsState(emptyList()).value

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(list) { item ->
                AnimeItem(item.title, item.poster)
            }
        }
    }
}

@Composable
fun AnimeItem(title: String, poster: String) {
    val context = LocalContext.current

    OutlinedCard(
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(poster)
                    .build(),
                contentDescription = "Poster of $title",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = title,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .background(Color.Gray.copy(alpha = 0.3f)),
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}
