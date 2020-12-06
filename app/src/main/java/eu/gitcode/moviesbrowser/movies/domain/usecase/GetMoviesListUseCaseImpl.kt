package eu.gitcode.moviesbrowser.movies.domain.usecase

import eu.gitcode.moviesbrowser.movies.domain.enum.MovieType
import eu.gitcode.moviesbrowser.movies.domain.model.MovieDomainModel

class GetMoviesListUseCaseImpl : GetMoviesListUseCase {
    // TODO: 06/12/2020 Add real data source
    override suspend fun execute(): List<MovieDomainModel> {
        return listOf(
            MovieDomainModel(
                1,
                MovieType.MOVIE,
                "Lion King",
                1993,
                1500
            ),
            MovieDomainModel(
                3,
                MovieType.SHOW,
                "Matrix",
                2000,
                11111
            )
        )
    }
}
