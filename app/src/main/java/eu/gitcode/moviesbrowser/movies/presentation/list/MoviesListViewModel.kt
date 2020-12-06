package eu.gitcode.moviesbrowser.movies.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetMoviesListUseCase
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val getMoviesListUseCase: GetMoviesListUseCase
) : ViewModel() {

    val moviesListData = MutableLiveData<MoviesListState>()

    init {
        viewModelScope.launch {
            moviesListData.value = MoviesListState.Success(getMoviesListUseCase.execute())
        }
    }
}
