package com.example.theanimes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theanimes.data.DataSource
import com.example.theanimes.model.Anime
import com.example.theanimes.ui.theme.TheAnimesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheAnimesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AnimeApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            AnimeTopbar()
        }
    ) {
        LazyColumn(
            modifier.background(MaterialTheme.colors.background)
        ) {
            items(DataSource.animesData) {
                AnimeItem(anime = it)
            }
        }
    }
}

@Composable
fun AnimeItem(
    anime: Anime,
    modifier: Modifier = Modifier
) {
    var showDesc by remember {
        mutableStateOf(false)
    }

    Card(
        elevation = 4.dp,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(id = anime.name),
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .padding(bottom = 12.dp)
            )
            Image(
                painter = painterResource(id = anime.img),
                contentDescription = stringResource(id = anime.name),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clickable {
                        showDesc = !showDesc
                    }
            )
            if (showDesc) {
                Text(
                    text = stringResource(id = anime.description),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                            .padding(top = 12.dp)
                )
            }

        }
    }
}

@Composable
fun AnimeTopbar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    TheAnimesTheme {
        AnimeApp()
    }
}