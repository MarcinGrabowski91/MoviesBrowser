package eu.gitcode.moviesbrowser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import eu.gitcode.moviesbrowser.movies.domain.enum.MovieType
import eu.gitcode.moviesbrowser.movies.domain.model.MovieDomainModel
import eu.gitcode.moviesbrowser.movies.domain.usecase.GetMoviesListUseCase
import eu.gitcode.moviesbrowser.movies.presentation.list.MoviesListState
import eu.gitcode.moviesbrowser.movies.presentation.list.MoviesListViewModel
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
class MoviesListViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    val coroutineTestRule = InstantTaskExecutorRule()

    private val getMoviesListUseCase: GetMoviesListUseCase = mockk()

    private lateinit var moviesListViewModel: MoviesListViewModel

    @Before
    fun setUp() {
        moviesListViewModel = MoviesListViewModel(getMoviesListUseCase)
    }

    @Test
    fun `verify getMoviesUseCase execution`() {
        //given
        coEvery { getMoviesListUseCase.execute() } returns GetMoviesListUseCase.Result.Success(
            listOf()
        )

        // when
        moviesListViewModel.loadData()

        // then
        coVerify { getMoviesListUseCase.execute() }
    }

    @Test
    fun `verify state value when getMoviesUseCase returns a value`() {
        val moviesList = listOf(EXAMPLE_MOVIE_MODEL)
        //given
        coEvery { getMoviesListUseCase.execute() } returns GetMoviesListUseCase.Result.Success(
            moviesList
        )

        // when
        moviesListViewModel.loadData()

        // then
        val value = moviesListViewModel.moviesListData.value
        assert((value as MoviesListState.Success).moviesList.size == moviesList.size)
        assert(value.moviesList[0] == EXAMPLE_MOVIE_MODEL)
    }

    @Test
    fun `verify state value when getMoviesUseCase returns an error`() {
        //given
        coEvery { getMoviesListUseCase.execute() } returns
                GetMoviesListUseCase.Result.Error(
                    Throwable()
                )

        // when
        moviesListViewModel.loadData()

        // then
        assert(moviesListViewModel.moviesListData.value is MoviesListState.Error)
    }

    companion object {
        private val EXAMPLE_MOVIE_MODEL = MovieDomainModel(
            1,
            MovieType.MOVIE,
            "Lion King",
            1993,
            1111
        )
    }
}
