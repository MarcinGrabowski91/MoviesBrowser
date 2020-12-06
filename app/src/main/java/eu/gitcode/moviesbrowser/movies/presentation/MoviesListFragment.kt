package eu.gitcode.moviesbrowser.movies.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.movies.domain.enum.MovieType
import eu.gitcode.moviesbrowser.movies.domain.model.MovieDomainModel
import kotlinx.android.synthetic.main.movies_list_fragment.*
import org.koin.android.ext.android.inject

class MoviesListFragment : Fragment(R.layout.movies_list_fragment),
    MoviesAdapter.MoviesAdapterListener {
    private val viewModel: MoviesListViewModel by inject()
    private val moviesAdapter = MoviesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesRecyclerView.apply {
            adapter = moviesAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        mockData() // TODO: 06/12/2020
    }

    override fun onMovieClicked(id: Long) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, MovieFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onShowClicked(id: Long) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, MovieFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun mockData() {
        moviesAdapter.setItems(
            listOf(
                MovieDomainModel(
                    1,
                    MovieType.MOVIE,
                    "Lion King",
                    1993,
                    1500
                ),
                MovieDomainModel(
                    3,
                    MovieType.SHOW,
                    "Matrix",
                    2000,
                    11111
                )
            )
        )
    }

    companion object {
        fun newInstance() = MoviesListFragment()
    }
}
