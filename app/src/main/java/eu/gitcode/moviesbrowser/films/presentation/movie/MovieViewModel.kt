package eu.gitcode.moviesbrowser.films.presentation.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    val moviesListData = MutableLiveData<MovieState>()

    fun loadData() {
        viewModelScope.launch {
            getMovieDetailsUseCase.execute().also { result ->
                when (result) {
                    is BaseUseCase.Result.Success -> moviesListData.value =
                        MovieState.Success(result.data)
                    is BaseUseCase.Result.Error -> moviesListData.value =
                        MovieState.Error(result.error)
                }
            }
        }
    }
}
