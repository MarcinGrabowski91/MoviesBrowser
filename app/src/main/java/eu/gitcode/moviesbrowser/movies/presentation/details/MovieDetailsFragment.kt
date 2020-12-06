package eu.gitcode.moviesbrowser.movies.presentation.details

import androidx.fragment.app.Fragment
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.movies.presentation.list.MoviesListViewModel
import org.koin.android.ext.android.inject

class MovieDetailsFragment : Fragment(R.layout.movie_fragment) {
    private val viewModel: MoviesListViewModel by inject()

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }
}
