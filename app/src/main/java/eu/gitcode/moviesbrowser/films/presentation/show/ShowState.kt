package eu.gitcode.moviesbrowser.films.presentation.show

import eu.gitcode.moviesbrowser.films.domain.model.ShowDetailsDomainModel

sealed class ShowState {
    class Success(val show: ShowDetailsDomainModel) : ShowState()
    class Error(val throwable: Throwable) : ShowState()
}
