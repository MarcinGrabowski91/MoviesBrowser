package eu.gitcode.moviesbrowser.films.presentation.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.films.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val movieData = MutableLiveData<MovieState>()

    var movieId: Long?
        get() = savedStateHandle.get(MOVIE_ID_KEY)
        set(value) = savedStateHandle.set(MOVIE_ID_KEY, value)

    fun loadData() {
        movieId?.let {
            viewModelScope.launch {
                getMovieDetailsUseCase.execute(it)
                    .flowOn(Dispatchers.IO)
                    .catch { cause ->
                        Timber.e(cause)
                        movieData.value = MovieState.Error(cause)
                    }
                    .collect { value -> movieData.value = MovieState.Success(value) }
            }
        } ?: run { movieData.value = MovieState.Error(Throwable()) }
    }

    companion object {
        private const val MOVIE_ID_KEY = "movieId"
    }
}

