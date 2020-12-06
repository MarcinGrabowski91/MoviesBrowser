package eu.gitcode.moviesbrowser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.films.domain.enum.MovieType
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.usecase.GetMoviesListUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowsListUseCase
import eu.gitcode.moviesbrowser.films.presentation.list.FilmsListState
import eu.gitcode.moviesbrowser.films.presentation.list.FilmsListViewModel
import eu.gitcode.utils.CoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FilmsListViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    val coroutineTestRule = InstantTaskExecutorRule()

    private val getMoviesListUseCase: GetMoviesListUseCase = mockk()
    private val getShowsListUseCase: GetShowsListUseCase = mockk()

    private lateinit var filmsListViewModel: FilmsListViewModel

    @Before
    fun setUp() {
        filmsListViewModel = FilmsListViewModel(getMoviesListUseCase, getShowsListUseCase)
    }

    @Test
    fun `verify getMoviesUseCase execution`() {
        //given
        coEvery { getMoviesListUseCase.execute() } returns BaseUseCase.Result.Success(
            listOf()
        )

        // when
        filmsListViewModel.loadData()

        // then
        coVerify { getMoviesListUseCase.execute() }
    }

    @Test
    fun `verify state value when getMoviesUseCase returns a value`() {
        val moviesList = listOf(EXAMPLE_MOVIE_MODEL)
        //given
        coEvery { getMoviesListUseCase.execute() } returns BaseUseCase.Result.Success(
            moviesList
        )

        // when
        filmsListViewModel.loadData()

        // then
        val value = filmsListViewModel.moviesListData.value
        assert((value as FilmsListState.Success).moviesList.size == moviesList.size)
        assert(value.moviesList[0] == EXAMPLE_MOVIE_MODEL)
    }

    @Test
    fun `verify state value when getMoviesUseCase returns an error`() {
        //given
        coEvery { getMoviesListUseCase.execute() } returns
                BaseUseCase.Result.Error(
                    Throwable()
                )

        // when
        filmsListViewModel.loadData()

        // then
        assert(filmsListViewModel.moviesListData.value is FilmsListState.Error)
    }

    companion object {
        private val EXAMPLE_MOVIE_MODEL = FilmDomainModel(
            1,
            MovieType.MOVIE,
            "Lion King",
            1993,
            1111
        )
    }
}
