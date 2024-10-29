package com.samuelconra.rickmortyapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.samuelconra.rickmortyapp.R
import com.samuelconra.rickmortyapp.models.Character
import com.samuelconra.rickmortyapp.services.CharacterService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun CharactersScreen(innerPadding: PaddingValues, navController: NavController) {
    var characters by remember { mutableStateOf(emptyList<Character>()) }
    var isLoading by remember { mutableStateOf(true) }

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        scope.launch {
            val BASE_URL = "https://rickandmortyapi.com/api/"
            val characterService= Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CharacterService::class.java)

            isLoading = true
            val response = characterService.getAllCharacters()
            characters = response.results
            delay(1500)
            isLoading = false
            Log.i("Response", characters.toString())
        }
    }

    if (isLoading){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ){
            // CircularProgressIndicator()
            Text(
                text = "R&M",
                color = Color.Cyan,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .border(3.dp, Color.Cyan, MaterialTheme.shapes.small)
                    .padding(20.dp)
            )
            Text(
                text = "APP",
                color = Color.Cyan,
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .offset(y = (28).dp)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(10.dp)
            )

        }
    } else{
        // MAIN CONTAINER
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            // INFO API + LOGO
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                // LOGO
                Box(
                    modifier = Modifier
                        .width(275.dp)
                        .height(83.dp)
                        .zIndex(1F),
                ){
                    AsyncImage(
                        model = "https://subsonic.com/img/m/20.jpg",
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize().zIndex(1F),
                        contentScale = ContentScale.Crop,
                    )
                    Image(
                        painter = painterResource(R.drawable.logo_shadow),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize().offset(y = 10.dp),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                }

                // INFORMATION
                Box(
                    modifier = Modifier
                        .offset(y = (-25).dp)
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiary)
                ){
                    Column(
                        modifier = Modifier
                            .offset(y = (-15).dp)
                            .clip(RoundedCornerShape(20.dp))
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(top = 60.dp, bottom = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        Text(
                            text = "EPISODES: 51",
                            style = MaterialTheme.typography.labelMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = "LOCATION: 126",
                            style = MaterialTheme.typography.labelMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = "CHARACTERS: 826",
                            style = MaterialTheme.typography.labelMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            }

            // CHARACTERS TITLE
            Text(
                text = "CHARACTERS",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 20.dp)
            )

            // CHARACTERS CARDS
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                columns = GridCells.Fixed(2),
            ){
                items(characters){
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .width(130.dp)
                            .height(120.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .clickable {
                                navController.navigate("character/${it.id}")
                            }
                    ){
                        AsyncImage(
                            model = it.image,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 10.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
        }
    }
}