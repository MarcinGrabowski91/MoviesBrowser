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

    fun loadData() {
        viewModelScope.launch {
            getMoviesListUseCase.execute().also { result ->
                when (result) {
                    is GetMoviesListUseCase.Result.Success -> moviesListData.value =
                        MoviesListState.Success(result.data)
                    is GetMoviesListUseCase.Result.Error -> moviesListData.value =
                        MoviesListState.Error(result.error)
                }
            }
        }
    }
}
