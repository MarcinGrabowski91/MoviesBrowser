package eu.gitcode.moviesbrowser.movies.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetFilmsListUseCase
import kotlinx.coroutines.launch

class FilmsListViewModel(
    private val getFilmsListUseCase: GetFilmsListUseCase
) : ViewModel() {

    val moviesListData = MutableLiveData<FilmsListState>()

    fun loadData() {
        viewModelScope.launch {
            getFilmsListUseCase.execute().also { result ->
                when (result) {
                    is BaseUseCase.Result.Success -> moviesListData.value =
                        FilmsListState.Success(result.data)
                    is BaseUseCase.Result.Error -> moviesListData.value =
                        FilmsListState.Error(result.error)
                }
            }
        }
    }
}
