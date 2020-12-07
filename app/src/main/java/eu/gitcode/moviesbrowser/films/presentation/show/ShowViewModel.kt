package eu.gitcode.moviesbrowser.films.presentation.show

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowDetailsUseCase
import kotlinx.coroutines.launch

class ShowViewModel(
    private val getShowDetailsUseCase: GetShowDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val showData = MutableLiveData<ShowState>()

    var showId: Long?
        get() = savedStateHandle.get(SHOW_ID_KEY)
        set(value) = savedStateHandle.set(SHOW_ID_KEY, value)

    fun loadData() {
        showId?.let {
            viewModelScope.launch {
                getShowDetailsUseCase.execute(it).also { result ->
                    when (result) {
                        is BaseUseCase.Result.Success -> showData.value =
                            ShowState.Success(result.data)
                        is BaseUseCase.Result.Error -> showData.value =
                            ShowState.Error(result.error)
                    }
                }
            }
        } ?: run { showData.value = ShowState.Error(Throwable()) }
    }

    companion object {
        private const val SHOW_ID_KEY = "showId"
    }
}
