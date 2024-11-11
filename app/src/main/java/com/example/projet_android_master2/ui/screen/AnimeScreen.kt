package com.example.projet_android_master2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.projet_android_master2.ui.model.AnimeObject
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
                when (item) {
                    is AnimeObject.Header -> {
                        HeaderItem(date = item.date)
                    }
                    is AnimeObject.Anime -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .border(2.dp, Color.Black, shape = MaterialTheme.shapes.medium)
                        ) {
                            AnimeItem(title = item.title, poster = item.poster)
                        }
                    }
                    is AnimeObject.Footer -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .border(2.dp, Color.Black, shape = MaterialTheme.shapes.medium)
                        ) {
                            FooterItem(genres = item.genres)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderItem(date: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Date d'insertion : $date",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp
            )
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FooterItem(genres: List<String>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Gray.copy(alpha = 0.2f))
            .padding(16.dp)
    ) {
        genres.forEach { genre ->
            if(genre.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .border(1.dp, Color.Black, shape = MaterialTheme.shapes.small)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = genre,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                }
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
