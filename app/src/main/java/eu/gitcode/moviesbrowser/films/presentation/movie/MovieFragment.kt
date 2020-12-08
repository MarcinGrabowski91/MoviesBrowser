package eu.gitcode.moviesbrowser.films.presentation.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.databinding.DetailsFragmentBinding
import eu.gitcode.moviesbrowser.utils.ResourcesUtils
import eu.gitcode.moviesbrowser.utils.viewBinding
import org.koin.android.ext.android.inject

class MovieFragment : Fragment(R.layout.details_fragment) {

    private val viewModel: MovieViewModel by inject()
    private val binding: DetailsFragmentBinding by viewBinding(DetailsFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.movieId = arguments?.getLong(MOVIE_ID_KEY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewState()
        setupViews()
        viewModel.loadData()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViewState() {
        viewModel.movieData.observe(viewLifecycleOwner, { state ->
            binding.loadingLay.visibility = View.GONE
            when (state) {
                is MovieState.Success -> {
                    binding.detailsFragmentTitle.text = state.movie.title
                    binding.detailsFragmentOverview.text = state.movie.overview
                    binding.detailsFragmentRatingBar.rating = state.movie.rating
                    binding.detailsFragmentYear.text = state.movie.year.toString()
                    state.movie.genres?.forEachIndexed { index, s ->
                        binding.detailsFragmentGenres.text =
                            binding.detailsFragmentGenres.text.toString() + s
                        if (index != state.movie.genres.lastIndex) {
                            binding.detailsFragmentGenres.text =
                                binding.detailsFragmentGenres.text.toString() + ", "
                        }
                    }
                    binding.detailsFragmentCountry.text = state.movie.country
                    binding.detailsFragmentVotes.text = state.movie.votes.toString()
                }
                is MovieState.Error -> {
                    Toast.makeText(context, state.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupViews() {
        val bgColor = ResourcesUtils.getColorFromAttribute(binding.loadingLay, R.attr.colorMovie)
        binding.detailsMainLay.setBackgroundColor(bgColor)
        binding.loadingLay.setBackgroundColor(bgColor)
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
