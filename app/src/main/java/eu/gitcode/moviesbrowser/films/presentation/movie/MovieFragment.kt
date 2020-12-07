package eu.gitcode.moviesbrowser.films.presentation.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.databinding.MovieFragmentBinding
import eu.gitcode.moviesbrowser.utils.viewBinding
import org.koin.android.ext.android.inject

class MovieFragment : Fragment(R.layout.movie_fragment) {

    private val viewModel: MovieViewModel by inject()
    private val binding: MovieFragmentBinding by viewBinding(MovieFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.movieId = arguments?.getLong(MOVIE_ID_KEY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewState()
        viewModel.loadData()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViewState() {
        viewModel.movieData.observe(viewLifecycleOwner, { state ->
            when (state) {
                is MovieState.Success -> {
                    binding.movieFragmentTitle.text = state.movie.title
                    binding.movieFragmentOverview.text = state.movie.overview
                    binding.movieFragmentRatingBar.rating =
                        state.movie.rating / binding.movieFragmentRatingBar.numStars
                    binding.movieFragmentYear.text = state.movie.year.toString()
                    state.movie.genres.forEachIndexed { index, s ->
                        binding.movieFragmentGenres.text =
                            binding.movieFragmentGenres.text.toString() + s
                        if (index != state.movie.genres.lastIndex) {
                            binding.movieFragmentGenres.text =
                                binding.movieFragmentGenres.text.toString() + ", "
                        }
                    }
                    binding.movieFragmentCountry.text = state.movie.country
                    binding.movieFragmentVotes.text = state.movie.votes.toString()
                }
                is MovieState.Error -> {
                    Toast.makeText(context, state.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        private const val MOVIE_ID_KEY = "movieId"

        fun newInstance(movieId: Long) = MovieFragment().apply {
            arguments = Bundle().apply {
                putLong(MOVIE_ID_KEY, movieId)
            }
        }
    }
}
