package eu.gitcode.moviesbrowser.films.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.films.domain.enum.OrderType
import eu.gitcode.moviesbrowser.films.domain.usecase.GetMoviesListUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowsListUseCase
import kotlinx.coroutines.Dispatchers
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

    fun loadData() {
        viewModelScope.launch {
            combine(getMoviesListUseCase.execute(), getShowsListUseCase.execute()) { a, b -> a + b }
                .map { it ->
                    if (orderType == OrderType.DESCENDING) {
                        it.sortedByDescending { it.watchers }
                    } else {
                        it.sortedBy { it.watchers }
                    }
                }
                .flowOn(Dispatchers.IO)
                .catch { cause ->
                    Timber.e(cause)
                    filmsListData.value = FilmsListState.Error(cause)
                }
                .collect { value -> filmsListData.value = FilmsListState.Success(value) }
        }
    }

    private companion object {
        private const val ORDER_TYPE_KEY = "sortTypeKey"
    }
}
