package eu.gitcode.moviesbrowser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import eu.gitcode.moviesbrowser.films.domain.model.MovieDetailDomainModel
import eu.gitcode.moviesbrowser.films.domain.usecase.GetMovieDetailsUseCase
import eu.gitcode.moviesbrowser.films.presentation.movie.MovieState
import eu.gitcode.moviesbrowser.films.presentation.movie.MovieViewModel
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
class MovieViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    val coroutineTestRule = InstantTaskExecutorRule()

    private val getMovieDetailsUseCase: GetMovieDetailsUseCase = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(getMovieDetailsUseCase, savedStateHandle)
    }

    @Test
    fun `verify getMoviesUseCase execution`() {
        //given
        coEvery { getMovieDetailsUseCase.execute(MOVIE_ID) } returns flow { emit(EXAMPLE_MOVIE_MODEL) }
        every { savedStateHandle.get<Long>(any()) } returns MOVIE_ID

        // when
        movieViewModel.loadData()

        // then
        coVerify { getMovieDetailsUseCase.execute(MOVIE_ID) }
    }

    @Test
    fun `verify state value when getMovieDetailsUseCase returns a value`() {
        //given
        coEvery { getMovieDetailsUseCase.execute(MOVIE_ID) } returns flow { emit(EXAMPLE_MOVIE_MODEL) }
        every { savedStateHandle.get<Long>(any()) } returns MOVIE_ID

        runBlockingTest {
            // when
            movieViewModel.loadData()

            // then
            advanceTimeBy(Duration.of(1, ChronoUnit.MINUTES).toMillis())
            val value = movieViewModel.movieData.value
            assert((value as MovieState.Success).movie == EXAMPLE_MOVIE_MODEL)
        }
    }

    @Test
    fun `verify state value when getMoviesUseCase returns an error`() {
        //given
        coEvery { getMovieDetailsUseCase.execute(MOVIE_ID) } returns flow { error(Throwable()) }
        every { savedStateHandle.get<Long>(any()) } returns MOVIE_ID
        // when
        movieViewModel.loadData()

        // then
        assert(movieViewModel.movieData.value is MovieState.Error)

    }

    @Test
    fun `verify state value when movieId is null`() {
        //given
        coEvery { getMovieDetailsUseCase.execute(MOVIE_ID) } returns flow { emit(EXAMPLE_MOVIE_MODEL) }
        every { savedStateHandle.get<Long>(any()) } returns null

        // when
        movieViewModel.loadData()

        // then
        assert(movieViewModel.movieData.value is MovieState.Error)
    }

    companion object {
        private const val MOVIE_ID: Long = 1
        private val EXAMPLE_MOVIE_MODEL = MovieDetailDomainModel(
            id = 0,
            availableTranslations = listOf(),
            certification = "",
            commentCount = 0,
            country = "",
            genres = listOf(),
            homepage = "",
            language = "",
            overview = "",
            rating = 0F,
            released = "",
            runtime = 0,
            status = "",
            tagline = "",
            title = "",
            updatedAt = "",
            votes = 0,
            year = 0
        )
    }
}
