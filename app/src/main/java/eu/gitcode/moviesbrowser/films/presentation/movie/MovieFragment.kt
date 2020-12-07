package eu.gitcode.moviesbrowser.films.presentation.movie

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

    private fun setupViewState() {
        viewModel.movieData.observe(viewLifecycleOwner, { state ->
            when (state) {
                is MovieState.Success -> {
                    // TODO: 07/12/2020 fill data
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
