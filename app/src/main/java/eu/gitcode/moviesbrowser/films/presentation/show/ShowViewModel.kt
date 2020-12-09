package eu.gitcode.moviesbrowser.films.presentation.show

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class ShowViewModel(
    private val getShowDetailsUseCase: GetShowDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val showData = MutableLiveData<ShowState>()

    var showId: Long?
        get() = savedStateHandle.get(MOVIE_ID_KEY)
        set(value) = savedStateHandle.set(MOVIE_ID_KEY, value)

    fun loadData() {
        showId?.let {
            viewModelScope.launch {
                getShowDetailsUseCase.execute(it)
                    .flowOn(Dispatchers.IO)
                    .catch { cause ->
                        Timber.e(cause)
                        showData.value = ShowState.Error(cause)
                    }
                    .collect { value -> showData.value = ShowState.Success(value) }
            }
        } ?: run { showData.value = ShowState.Error(Throwable()) }
    }

    companion object {
        private const val MOVIE_ID_KEY = "movieId"
    }
}
