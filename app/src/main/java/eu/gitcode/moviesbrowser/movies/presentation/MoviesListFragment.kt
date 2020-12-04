package eu.gitcode.moviesbrowser.movies.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import eu.gitcode.moviesbrowser.R
import kotlinx.android.synthetic.main.movies_list_fragment.*
import org.koin.android.ext.android.inject

class MoviesListFragment : Fragment(R.layout.movies_list_fragment),
    MoviesAdapter.MoviesAdapterListener {
    private val viewModel: MoviesListViewModel by inject()
    private val moviesAdapter = MoviesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesRecyclerView.adapter = moviesAdapter
    }

    override fun onMovieClicked(id: Long) {
        TODO("Not yet implemented")
    }

    override fun onShowClicked(id: Long) {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance() = MoviesListFragment()
    }
}
