package eu.gitcode.moviesbrowser.movies.presentation

import androidx.fragment.app.Fragment
import eu.gitcode.moviesbrowser.R
import org.koin.android.ext.android.inject

class MovieFragment : Fragment(R.layout.movie_fragment) {
    private val viewModel: MoviesListViewModel by inject()

    companion object {
        fun newInstance() = MovieFragment()
    }
}
