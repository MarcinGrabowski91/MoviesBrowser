package eu.gitcode.moviesbrowser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import eu.gitcode.moviesbrowser.base.domain.BaseUseCase
import eu.gitcode.moviesbrowser.films.domain.model.ShowDetailsDomainModel
import eu.gitcode.moviesbrowser.films.domain.usecase.GetShowDetailsUseCase
import eu.gitcode.moviesbrowser.films.presentation.show.ShowState
import eu.gitcode.moviesbrowser.films.presentation.show.ShowViewModel
import eu.gitcode.utils.CoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ShowViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    val coroutineTestRule = InstantTaskExecutorRule()

    private val getShowDetailsUseCase: GetShowDetailsUseCase = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()

    private lateinit var showViewModel: ShowViewModel

    @Before
    fun setUp() {
        showViewModel = ShowViewModel(getShowDetailsUseCase, savedStateHandle)
    }

    @Test
    fun `verify getMoviesUseCase execution`() {
        //given
        coEvery { getShowDetailsUseCase.execute(MOVIE_ID) } returns BaseUseCase.Result.Success(
            EXAMPLE_SHOW_MODEL
        )
        every { savedStateHandle.get<Long>(any()) } returns MOVIE_ID

        // when
        showViewModel.loadData()

        // then
        coVerify { getShowDetailsUseCase.execute(MOVIE_ID) }
    }

    @Test
    fun `verify state value when getMovieDetailsUseCase returns a value`() {
        //given
        coEvery { getShowDetailsUseCase.execute(MOVIE_ID) } returns BaseUseCase.Result.Success(
            EXAMPLE_SHOW_MODEL
        )
        every { savedStateHandle.get<Long>(any()) } returns MOVIE_ID

        // showViewModel
        showViewModel.loadData()

        // then
        val value = showViewModel.showData.value
        assert((value as ShowState.Success).show == EXAMPLE_SHOW_MODEL)
    }

    @Test
    fun `verify state value when getMoviesUseCase returns an error`() {
        //given
        coEvery { getShowDetailsUseCase.execute(MOVIE_ID) } returns BaseUseCase.Result.Error(
            Throwable()
        )
        every { savedStateHandle.get<Long>(any()) } returns MOVIE_ID

        // when
        showViewModel.loadData()

        // then
        assert(showViewModel.showData.value is ShowState.Error)
    }

    @Test
    fun `verify state value when movieId is null`() {
        //given
        coEvery { getShowDetailsUseCase.execute(MOVIE_ID) } returns BaseUseCase.Result.Success(
            EXAMPLE_SHOW_MODEL
        )
        every { savedStateHandle.get<Long>(any()) } returns null

        // when
        showViewModel.loadData()

        // then
        assert(showViewModel.showData.value is ShowState.Error)
    }

    companion object {
        private const val MOVIE_ID: Long = 1
        private val EXAMPLE_SHOW_MODEL = ShowDetailsDomainModel(
            traktId = 0,
            airedEpisodes = 0,
            airs = ShowDetailsDomainModel.Airs(
                day = "",
                time = "",
                timezone = ""
            ),
            availableTranslations = listOf(),
            certification = "",
            commentCount = 0,
            country = "",
            firstAired = "",
            genres = listOf(),
            homepage = "",
            language = "",
            network = "",
            overview = "",
            rating = 0F,
            runtime = 0,
            status = "",
            title = "",
            updatedAt = "",
            votes = 0,
            year = 0
        )
    }
}
