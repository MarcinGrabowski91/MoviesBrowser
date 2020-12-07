package eu.gitcode.moviesbrowser.films.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetMoviesListUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowsListUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class FilmsListViewModel(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getShowsListUseCase: GetShowsListUseCase
) : ViewModel() {

    val filmsListData = MutableLiveData<FilmsListState>()

    fun loadData() {
        viewModelScope.launch {
            getMoviesListUseCase.execute().also { result ->
                when (result) {
                    is BaseUseCase.Result.Success -> filmsListData.value =
                        FilmsListState.Success(result.data)
                    is BaseUseCase.Result.Error -> {
                        Timber.e(result.error)
                        filmsListData.value =
                            FilmsListState.Error(result.error)
                    }
                }
            }
        }
    }
}
