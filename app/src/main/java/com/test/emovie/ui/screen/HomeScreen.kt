package com.test.emovie.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.test.common.FilterType
import com.test.domain.models.Movie
import com.test.emovie.R
import com.test.emovie.components.LinearProgressBarCustom
import com.test.emovie.components.LoadErrorScreen
import com.test.emovie.ui.theme.EMovieTheme
import com.test.emovie.ui.theme.medium
import com.test.emovie.ui.theme.normal
import com.test.emovie.ui.theme.regular
import com.test.emovie.viewModel.MovieViewModel

@Composable
fun HomeScreen(onNavigate: (Int) -> Unit, viewModel: MovieViewModel) {

    val upcomingState by viewModel.upcomingState.collectAsState()
    val topRatedState by viewModel.topRatedState.collectAsState()
    val recommendedState by viewModel.recommendedState.collectAsState()
    val openDialog = remember { mutableStateOf(false) }
    val filterType = remember { mutableStateOf(FilterType.UNKNOWN) }
    val listFilter = remember { mutableStateOf(listOf<String>()) }
    var title = remember { 0 }

    EMovieTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val scrollState = rememberScrollState()
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.Black)
                    .verticalScroll(scrollState)
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                when {
                    upcomingState.isLoading -> {
                        LinearProgressBarCustom()
                    }
                    upcomingState.failed -> {
                        LoadErrorScreen(viewModel.stateErrorMessage)
                    }
                    else -> {
                        MovieType(stringResource(R.string.upcoming))
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            state = rememberLazyListState()
                        ) {

                            itemsIndexed(upcomingState.data) { _, movie ->
                                ItemCardMovie(movie = movie, onClick = {
                                    onNavigate(it)
                                })
                            }
                        }
                        MovieType(stringResource(R.string.top_rated))

                        when {
                            topRatedState.isLoading -> {
                                LinearProgressBarCustom()
                            }
                            topRatedState.failed -> {
                                LoadErrorScreen(viewModel.stateErrorMessage)
                            }
                            else -> {
                                LazyRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    state = rememberLazyListState()
                                ) {

                                    itemsIndexed(topRatedState.data) { _, movie ->
                                        ItemCardMovie(movie = movie, onClick = {
                                            onNavigate(it)
                                        })
                                    }
                                }
                            }
                        }

                        Column {
                            MovieType(stringResource(R.string.recommended_for_you))
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                state = rememberLazyListState()
                            ) {
                                item {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(normal),
                                        modifier = Modifier.padding(start = medium)
                                    ) {

                                        ButtonFilter(title = R.string.language, onClick = {
                                            title = R.string.language
                                            listFilter.value = viewModel.languages
                                            filterType.value = FilterType.LANGUAGE
                                            openDialog.value = true
                                        })

                                        ButtonFilter(title = R.string.release_year, onClick = {
                                            title = R.string.release_year
                                            listFilter.value = viewModel.releaseYears
                                            filterType.value = FilterType.RELEASE_YEAR
                                            openDialog.value = true
                                        })

                                        ButtonFilter(title = R.string.vote_average, onClick = {
                                            viewModel.filter(voteAverage = true)
                                        })
                                    }
                                }
                            }
                        }
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            state = rememberLazyListState()
                        ) {

                            itemsIndexed(recommendedState.data.take(6)) { _, movie ->
                                ItemCardMovie(movie = movie, onClick = {
                                    onNavigate(it)
                                })
                            }
                        }
                    }
                }
            }

            if (openDialog.value) {
                AlertDialogFilter(
                    title = title,
                    listFilter = listFilter,
                    openDialog = openDialog,
                    filterType = filterType,
                    onClick = { value, filterType ->
                        when (filterType) {
                            FilterType.RELEASE_YEAR -> {
                                viewModel.filter(releaseYear = value)
                            }
                            FilterType.LANGUAGE -> {
                                viewModel.filter(language = value)
                            }
                            else -> {
                                openDialog.value = false
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ButtonFilter(title: Int, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        onClick = onClick,
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.h6.copy(
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
fun MovieType(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(medium),
        style = MaterialTheme.typography.h6.copy(
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
    )
}

@Composable
fun ItemCardMovie(movie: Movie, onClick: (movieId: Int) -> Unit) {

    Card(
        shape = RoundedCornerShape(normal),
        modifier = Modifier
            .fillMaxWidth()
            .padding(normal)
            .clickable { onClick(movie.id) }
    ) {
        Box(
            modifier = Modifier
                .width(138.dp)
                .height(181.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(movie.backdrop_path),
                contentDescription = "Movie Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(normal),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = movie.original_title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = movie.overview,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 8.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Thin
                    )
                )
            }
        }
    }
}

@Composable
fun AlertDialogFilter(
    title: Int,
    listFilter: MutableState<List<String>>,
    openDialog: MutableState<Boolean>,
    filterType: MutableState<FilterType>,
    onClick: (value: String, filterType: FilterType) -> Unit
) {

    val (selectedOption) = remember { mutableStateOf(listFilter.value[0]) }

    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp,
                )
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                listFilter.value.forEach { text ->
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = {
                                    onClick(text, filterType.value)
                                    openDialog.value = false
                                }
                            )
                            .padding(vertical = normal)
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = {
                                onClick(text, filterType.value)
                                openDialog.value = false
                            }
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                            ),
                            modifier = Modifier.padding(start = medium, top = regular)
                        )
                    }
                }
            }
        },
        buttons = {}
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EMovieTheme {
        ItemCardMovie(Movie(), onClick = {})
    }
}
