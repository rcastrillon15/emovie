package com.test.emovie.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.test.common.toDateYYYY
import com.test.emovie.R
import com.test.emovie.components.DetailTopAppBar
import com.test.emovie.components.LinearProgressBarCustom
import com.test.emovie.components.LoadErrorScreen
import com.test.emovie.ui.theme.EMovieTheme
import com.test.emovie.ui.theme.medium
import com.test.emovie.ui.theme.normal
import com.test.emovie.ui.theme.small
import com.test.emovie.viewModel.MovieViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    viewModel: MovieViewModel,
    onBack: () -> Unit
) {
    val detailState by viewModel.detailState.collectAsState()
    viewModel.getDetail()
    val context = LocalContext.current
    val handler = LocalUriHandler.current
    val messageWithoutTrailer = stringResource(R.string.without_trailer)

    Scaffold(topBar = { DetailTopAppBar(title = detailState.data.title, onBack = { onBack() }) }) {
        when {
            detailState.isLoading -> {
                LinearProgressBarCustom()
            }
            detailState.failed -> {
                Toast.makeText(context, stringResource(R.string.internet_connection_required_message), Toast.LENGTH_LONG).show()
            }
            else -> {
                Card(
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {

                        Image(
                            painter = rememberAsyncImagePainter(detailState.data.backdrop_path),
                            contentDescription = "Movie Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(medium),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = detailState.data.title,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.h6.copy(
                                    fontSize = 40.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {

                                (if (detailState.data.release_date.isNotBlank()) detailState.data.release_date.toDateYYYY() else "")?.let { date ->
                                    RateCard(
                                        value = date
                                    )
                                }

                                RateCard(
                                    value = detailState.data.original_language
                                )
                                RateCard(
                                    value = detailState.data.vote_average.toString(),
                                    modifier = Modifier
                                        .background(Color.Yellow),
                                    image = Icons.Filled.Star
                                )
                            }

                            MovieGenre(detailState.data.genres.joinToString(separator = " ✹ ") { it.name })

                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Black.copy(
                                        alpha = 0.2f
                                    )
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = .5.dp,
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(5.dp)
                                    ),
                                onClick = {
                                    if (!detailState.data.urlTrailer.isNullOrEmpty()) {
                                        handler.openUri(checkNotNull(detailState.data.urlTrailer))
                                    } else {
                                        Toast.makeText(
                                            context,
                                            messageWithoutTrailer,
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = stringResource(R.string.watch_trailer),
                                    style = MaterialTheme.typography.h6.copy(
                                        fontSize = 16.sp,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }

                            Text(
                                text = stringResource(R.string.movie_plot),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = normal),
                                style = MaterialTheme.typography.h6.copy(
                                    fontSize = 12.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                            Text(
                                text = detailState.data.overview,
                                maxLines = 6,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.h6.copy(
                                    fontSize = 12.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Thin
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RateCard(value: String, modifier: Modifier = Modifier, image: ImageVector? = null) {
    Card(
        shape = RoundedCornerShape(normal),
        modifier = Modifier
            .padding(small)
    ) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            if (image != null) {
                Icon(
                    imageVector = image,
                    contentDescription = null
                )
            }

            Text(
                text = value,
                modifier = modifier
                    .padding(normal)
                    .wrapContentSize(),
                style = MaterialTheme.typography.h6.copy(
                    fontSize = 12.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

@Composable
fun MovieGenre(genre: String) {
    Text(
        text = genre,
        modifier = Modifier.padding(vertical = medium, horizontal = small),
        style = MaterialTheme.typography.h6.copy(
            fontSize = 12.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    )
}

@Preview(showBackground = true)
@Composable
fun MovieDetailScreenPreview() {
    EMovieTheme {
        MovieDetailScreen(viewModel = hiltViewModel(), onBack = {})
    }
}