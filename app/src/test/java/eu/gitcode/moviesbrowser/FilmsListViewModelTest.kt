package eu.gitcode.moviesbrowser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import eu.gitcode.moviesbrowser.films.domain.enum.MovieType
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel
import eu.gitcode.moviesbrowser.films.domain.usecase.GetMoviesListUseCase
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowsListUseCase
import eu.gitcode.moviesbrowser.films.presentation.list.FilmsListState
import eu.gitcode.moviesbrowser.films.presentation.list.FilmsListViewModel
import eu.gitcode.utils.CoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.Duration
import java.time.temporal.ChronoUnit

@RunWith(JUnit4::class)
class FilmsListViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    val coroutineTestRule = InstantTaskExecutorRule()

    private val getMoviesListUseCase: GetMoviesListUseCase = mockk()
    private val getShowsListUseCase: GetShowsListUseCase = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()

    private lateinit var filmsListViewModel: FilmsListViewModel

    @Before
    fun setUp() {
        filmsListViewModel =
            FilmsListViewModel(getMoviesListUseCase, getShowsListUseCase, savedStateHandle)
    }

    @Test
    fun `verify getMoviesUseCase execution`() {
        //given
        coEvery { getMoviesListUseCase.execute() } returns flow { emit(listOf<FilmDomainModel>()) }
        coEvery { getShowsListUseCase.execute() } returns flow { emit(listOf<FilmDomainModel>()) }
        every { savedStateHandle.get<String>(FilmsListViewModel.ORDER_TYPE_KEY) } returns ORDER_TYPE
        every { savedStateHandle.get<String>(FilmsListViewModel.START_YEAR_KEY) } returns ""
        every { savedStateHandle.get<String>(FilmsListViewModel.END_YEAR_KEY) } returns ""

        // when
        filmsListViewModel.loadFilmsData()

        // then
        coVerify { getMoviesListUseCase.execute() }
    }

    @Test
    fun `verify state value when getMoviesUseCase returns a value`() {
        val moviesList = listOf(EXAMPLE_FILM_MODEL)
        //given
        coEvery { getMoviesListUseCase.execute() } returns flow { emit(moviesList) }
        coEvery { getShowsListUseCase.execute() } returns flow { emit(listOf<FilmDomainModel>()) }
        every { savedStateHandle.get<String>(any()) } returns ORDER_TYPE
        every { savedStateHandle.get<String>(FilmsListViewModel.START_YEAR_KEY) } returns ""
        every { savedStateHandle.get<String>(FilmsListViewModel.END_YEAR_KEY) } returns ""

        runBlockingTest {
            // when
            filmsListViewModel.loadFilmsData()
            // then
            advanceTimeBy(Duration.of(1, ChronoUnit.MINUTES).toMillis())
            val value = filmsListViewModel.filmsListData.value
            assert((value as FilmsListState.Success).filmsList.size == moviesList.size)
            assert(value.filmsList[0] == EXAMPLE_FILM_MODEL)
        }
    }

    @Test
    fun `verify state value when getMoviesUseCase returns an error`() {
        //given
        coEvery { getMoviesListUseCase.execute() } returns flow { error(Throwable()) }
        coEvery { getShowsListUseCase.execute() } returns flow { emit(listOf<FilmDomainModel>()) }
        every { savedStateHandle.get<String>(any()) } returns ORDER_TYPE
        every { savedStateHandle.get<String>(FilmsListViewModel.START_YEAR_KEY) } returns ""
        every { savedStateHandle.get<String>(FilmsListViewModel.END_YEAR_KEY) } returns ""

        // when
        filmsListViewModel.loadFilmsData()

        // then
        assert(filmsListViewModel.filmsListData.value is FilmsListState.Error)
    }

    companion object {
        private val EXAMPLE_FILM_MODEL = FilmDomainModel(
            1,
            MovieType.MOVIE,
            "Lion King",
            1993,
            1111
        )
        private const val ORDER_TYPE = "ASCENDING"
    }
}
