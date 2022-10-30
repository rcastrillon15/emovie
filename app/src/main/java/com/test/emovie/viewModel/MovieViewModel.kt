package com.test.emovie.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.common.LanguageName
import com.test.common.LoadingViewState
import com.test.common.toDateYYYY
import com.test.domain.mappers.toMovie
import com.test.domain.models.DetailModel
import com.test.domain.models.Movie
import com.test.domain.usecases.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MovieViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCase: MovieUseCase
) : ViewModel() {

    private val page = 1
    private val movieId = savedStateHandle.get<Int>("movieId")

    private val _upcomingState = MutableStateFlow(LoadingViewState<List<Movie>>(emptyList()))
    val upcomingState = _upcomingState.asStateFlow()

    private val _topRatedState = MutableStateFlow(LoadingViewState<List<Movie>>(emptyList()))
    val topRatedState = _topRatedState.asStateFlow()

    private val _recommendedState = MutableStateFlow(LoadingViewState<List<Movie>>(emptyList()))
    val recommendedState = _recommendedState.asStateFlow()

    private val _detailState = MutableStateFlow(LoadingViewState(DetailModel()))
    val detailState = _detailState.asStateFlow()

    var stateErrorMessage by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            awaitAll(async {
                getUpcoming()
            }, async {
                getTopRated()
            })
        }
    }

    private fun getUpcoming() {
        viewModelScope.launch {
            val newState = useCase.getUpcoming(page = page)
                .fold({
                    stateErrorMessage = it.toString()
                    _upcomingState.value.asFailure()
                }, { model ->
                    _upcomingState.value.asSuccess(model.map { it.toMovie() })
                })
            _upcomingState.update { newState }
        }
    }

    private fun getTopRated() {
        viewModelScope.launch {
            val newState = useCase.getTopRated(page = page)
                .fold({
                    stateErrorMessage = it.toString()
                    _topRatedState.value.asFailure()
                }, { model ->
                    _topRatedState.value.asSuccess(model.map { it.toMovie() })
                })
            _topRatedState.update { newState }
            _recommendedState.update { newState }
        }
    }

    fun getDetail() {
        viewModelScope.launch {
            awaitAll(async {
                viewModelScope.launch {
                    val newState = useCase.getDetail(checkNotNull(movieId))
                        .fold({
                            stateErrorMessage = it.toString()
                            _detailState.value.asFailure()
                        }, {
                            _detailState.value.asSuccess(it)
                        })
                    _detailState.update { newState }
                }
            }, async {
                viewModelScope.launch {
                    useCase.getTrailer(checkNotNull(movieId))
                        .fold({
                            stateErrorMessage = it.toString()
                        }, { trailers ->
                            _detailState.update {
                                it.asSuccess(
                                    it.data.copy(urlTrailer = if (trailers.isNullOrEmpty()) null else trailers.random())
                                )
                            }
                        })
                }
            })
        }
    }

    fun filter(
        releaseYear: String = "",
        language: String = "",
        voteAverage: Boolean = false
    ) {
        _recommendedState.value.asSuccess(_topRatedState.value.data)
        when {
            releaseYear.isNotEmpty() -> {
                val filter =
                    _topRatedState.value.data.filter { it.release_date.toDateYYYY() == releaseYear }
                        .take(6)
                _recommendedState.update { movies ->
                    movies.asSuccess(filter.ifEmpty { _topRatedState.value.data })
                }
            }

            language.isNotEmpty() -> {
                val filter = LanguageName.values().find { it.name == language }?.value

                _recommendedState.update { movies ->
                    movies.asSuccess(
                        if (language == LanguageName.ALL.name) _topRatedState.value.data
                        else _topRatedState.value.data.filter { it.original_language == filter }
                            .take(6)
                    )
                }
            }

            voteAverage -> {
                _recommendedState.update { movies ->
                    movies.asSuccess(_topRatedState.value.data.filter { it.vote_average > 8 }
                        .take(6))
                }
            }
        }
    }

    val releaseYears =
        mutableListOf(
            "Todos",
            "1957",
            "1995",
            "1994",
            "2016",
            "2019",
            "2020",
            "2021"
        )

    val languages = LanguageName.values().map { it.name }
}
