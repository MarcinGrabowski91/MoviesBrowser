package eu.gitcode.moviesbrowser.films.presentation.show

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowDetailsUseCase
import kotlinx.coroutines.launch

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
                try {
                    showData.value = ShowState.Success(getShowDetailsUseCase.execute(it))
                } catch (e: Exception) {
                    showData.value = ShowState.Error(Throwable())
                }

            }
        } ?: run { showData.value = ShowState.Error(Throwable()) }
    }

    companion object {
        private const val MOVIE_ID_KEY = "movieId"
    }
}
