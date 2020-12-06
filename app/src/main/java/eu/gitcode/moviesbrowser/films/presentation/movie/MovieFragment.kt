package eu.gitcode.moviesbrowser.films.presentation.movie

import androidx.fragment.app.Fragment
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.databinding.MovieFragmentBinding
import eu.gitcode.moviesbrowser.utils.viewBinding
import org.koin.android.ext.android.inject

class MovieFragment : Fragment(R.layout.movie_fragment) {

    private val viewModel: MovieViewModel by inject()
    private val binding: MovieFragmentBinding by viewBinding(MovieFragmentBinding::bind)

    companion object {
        fun newInstance() = MovieFragment()
    }
}
