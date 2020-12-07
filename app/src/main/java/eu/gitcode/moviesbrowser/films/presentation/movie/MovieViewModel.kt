package eu.gitcode.moviesbrowser.films.presentation.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.launch

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
                getMovieDetailsUseCase.execute(it).also { result ->
                    when (result) {
                        is BaseUseCase.Result.Success -> movieData.value =
                            MovieState.Success(result.data)
                        is BaseUseCase.Result.Error -> movieData.value =
                            MovieState.Error(result.error)
                    }
                }
            }
        } ?: run { movieData.value = MovieState.Error(Throwable()) }
    }

    companion object {
        private const val MOVIE_ID_KEY = "movieId"
    }
}
