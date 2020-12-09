package eu.gitcode.moviesbrowser.films.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.films.domain.enum.OrderType
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.usecase.GetMoviesListUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber

class FilmsListViewModel(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getShowsListUseCase: GetShowsListUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val filmsListData = MutableLiveData<FilmsListState>()

    var orderType: OrderType
        get() = run {
            val orderType = savedStateHandle.get<String>(ORDER_TYPE_KEY)
            if (orderType == null) {
                OrderType.DESCENDING
            } else {
                OrderType.valueOf(orderType)
            }
        }
        set(value) = savedStateHandle.set(ORDER_TYPE_KEY, value.name)

    var startYear: String
        get() = savedStateHandle.get<String>(START_YEAR_KEY) ?: ""
        set(value) = savedStateHandle.set(START_YEAR_KEY, value)

    var endYear: String
        get() = savedStateHandle.get<String>(END_YEAR_KEY) ?: ""
        set(value) = savedStateHandle.set(END_YEAR_KEY, value)

    fun loadFilmsData() {
        if (startYear.isNotEmpty() && endYear.isNotEmpty()) {
            val movies = getMoviesListUseCase.execute(startYear, endYear)
            val shows = getShowsListUseCase.execute(startYear, endYear)
            loadData(movies, shows)
        } else {
            val movies = getMoviesListUseCase.execute()
            val shows = getShowsListUseCase.execute()
            loadData(movies, shows)
        }

    }

    private fun loadData(
        movies: Flow<List<FilmDomainModel>>,
        shows: Flow<List<FilmDomainModel>>
    ) {
        viewModelScope.launch {
            combine(movies, shows) { a, b -> a + b }
                .flowOn(Dispatchers.IO)
                .map { it ->
                    if (orderType == OrderType.DESCENDING) {
                        it.sortedByDescending { it.watchers }
                    } else {
                        it.sortedBy { it.watchers }
                    }
                }
                .flowOn(Dispatchers.Default)
                .catch { cause ->
                    Timber.e(cause)
                    filmsListData.value = FilmsListState.Error(cause)
                }
                .collect { value -> filmsListData.value = FilmsListState.Success(value) }
        }
    }


    companion object {
        const val ORDER_TYPE_KEY = "sortTypeKey"
        const val START_YEAR_KEY = "startYearKey"
        const val END_YEAR_KEY = "endYearKey"
    }
}
