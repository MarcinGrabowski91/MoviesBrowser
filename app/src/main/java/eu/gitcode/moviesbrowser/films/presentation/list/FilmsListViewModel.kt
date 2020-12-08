package eu.gitcode.moviesbrowser.films.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.usecase.GetMoviesListUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class FilmsListViewModel(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getShowsListUseCase: GetShowsListUseCase
) : ViewModel() {

    val filmsListData = MutableLiveData<FilmsListState>()

    fun loadData() {
        viewModelScope.launch {
            try {
                val movies = async { getMoviesListUseCase.execute() }
                val shows = async { getShowsListUseCase.execute() }
                val sortedData = sortData(movies.await(), shows.await())
                filmsListData.value = FilmsListState.Success(sortedData)
            } catch (e: Exception) {
                Timber.e(e)
                filmsListData.value = FilmsListState.Error(e)
            }
        }
    }

    private suspend fun sortData(
        movies: List<FilmDomainModel>,
        shows: List<FilmDomainModel>
    ): List<FilmDomainModel> {
        return withContext(Dispatchers.Default) {
            val filmsList = movies + shows
            filmsList.sortedByDescending { it.watchers }
        }
    }
}
