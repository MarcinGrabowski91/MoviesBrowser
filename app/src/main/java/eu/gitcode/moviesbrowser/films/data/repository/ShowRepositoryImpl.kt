package eu.gitcode.moviesbrowser.films.data.repository

import eu.gitcode.moviesbrowser.films.domain.enum.MovieType
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.model.ShowDetailsDomainModel
import eu.gitcode.moviesbrowser.films.domain.repository.ShowRepository

class ShowRepositoryImpl : ShowRepository {
    // TODO: 07/12/2020 Add logic
    override suspend fun getShowsList(): List<FilmDomainModel> {
        return listOf(
            FilmDomainModel(
                2,
                MovieType.SHOW,
                "Game of Thrones",
                2018,
                3333
            ),
            FilmDomainModel(
                4,
                MovieType.SHOW,
                "The Walking Dead",
                2019,
                950
            )
        )
    }

    override suspend fun getShowDetails(showId: Long): ShowDetailsDomainModel {
        return ShowDetailsDomainModel(
            traktId = 1,
            airedEpisodes = 30,
            airs = ShowDetailsDomainModel.Airs(
                day = "Sunday",
                time = "21:00",
                timezone = "America/New_York"
            ),
            availableTranslations = listOf("en"),
            certification = "ENG",
            commentCount = 3,
            country = "US",
            firstAired = "2011-04-18T01:00:00.000Z",
            genres = listOf("Horror"),
            homepage = "www.lionking.com",
            language = "ENG",
            network = "HBO",
            overview = "Story about the lion",
            rating = 5,
            runtime = 130,
            status = "Released",
            title = "Lion King",
            updatedAt = "2014-07-23T03:21:46.000Z",
            votes = 2222,
            year = 1993
        )
    }
}
