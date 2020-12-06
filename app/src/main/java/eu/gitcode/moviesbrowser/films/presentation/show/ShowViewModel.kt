package eu.gitcode.moviesbrowser.films.presentation.show

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowDetailsUseCase
import kotlinx.coroutines.launch

class ShowViewModel(
    private val getShowDetailsUseCase: GetShowDetailsUseCase
) : ViewModel() {

    val showData = MutableLiveData<ShowState>()

    fun loadData() {
        viewModelScope.launch {
            getShowDetailsUseCase.execute().also { result ->
                when (result) {
                    is BaseUseCase.Result.Success -> showData.value = ShowState.Success(result.data)
                    is BaseUseCase.Result.Error -> showData.value = ShowState.Error(result.error)
                }
            }
        }
    }
}
