package com.samuelconra.rickmortyapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.samuelconra.rickmortyapp.R
import com.samuelconra.rickmortyapp.models.Character
import com.samuelconra.rickmortyapp.models.Location
import com.samuelconra.rickmortyapp.models.Origin
import com.samuelconra.rickmortyapp.services.CharacterService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun DetailScreen(innerPadding: PaddingValues, id: Int) {
    var character by remember { mutableStateOf(Character(
        created = "",
        episode = listOf(),
        gender = "",
        id = 0,
        image = "",
        location = Location(name = "", url = ""),
        name = "",
        origin = Origin(name = "", url = ""),
        species = "",
        status = "",
        type = "",
        url = ""
    ))}

    var isLoading by remember { mutableStateOf(false) }

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
            val response = characterService.getCharacterById(id)
            character = response
            isLoading = false
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else {
        // BOX MAIN
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
        ) {

            // INFO CHARACTER
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 40.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                // IMAGE CHARACTER
                AsyncImage(
                    model = character.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .offset(y = -80.dp)
                        .size(150.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .align(Alignment.TopCenter)
                        .zIndex(1f)
                )

                // CARD INFO
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(30.dp))
                        .background(MaterialTheme.colorScheme.tertiary)
                        .padding(bottom = 20.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(horizontal = 40.dp)
                            .padding(top = 100.dp, bottom = 70.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // NAME CHARACTER
                        Text(
                            text = character.name,
                            color = MaterialTheme.colorScheme.onTertiary,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                        // SPECIE
                        Text(
                            text = character.species.uppercase(),
                            color = MaterialTheme.colorScheme.onTertiary,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // STATUS ICON
                        Image(
                            painter = if (character.status == "Alive") {
                                painterResource(id = R.drawable.alive)
                            } else if (character.status == "Dead") {
                                painterResource(id = R.drawable.dead)
                            } else {
                                painterResource(id = R.drawable.unknown)
                            },
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.height(40.dp))

                        // LAST KNOWN LOCATION
                        Text(
                            text = "Last known location",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            text = character.location.name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start,
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        // FIRST SEEN IN
                        Text(
                            text = "First seen in",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            text = character.origin.name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start,
                        )
                    }
                }
            }

            // IMAGE BACKGROUND
            Image(
                painter = painterResource(R.drawable.rickandmorty),
                contentDescription = null,
                modifier = Modifier
                    .height(350.dp)
                    .align(Alignment.BottomEnd),
                contentScale = ContentScale.Crop,
            )
        }
    }
}