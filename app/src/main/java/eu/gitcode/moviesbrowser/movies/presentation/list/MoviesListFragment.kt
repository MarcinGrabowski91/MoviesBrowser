package eu.gitcode.moviesbrowser.movies.presentation.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.movies.presentation.details.MovieDetailsFragment
import kotlinx.android.synthetic.main.movies_list_fragment.*
import org.koin.android.ext.android.inject

class MoviesListFragment : Fragment(R.layout.movies_list_fragment),
    MoviesAdapter.MoviesAdapterListener {
    private val viewModel: MoviesListViewModel by inject()
    private val moviesAdapter = MoviesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupViewState()
        viewModel.loadData()
    }

    override fun onMovieClicked(id: Long) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, MovieDetailsFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onShowClicked(id: Long) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, MovieDetailsFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun setupViewState() {
        viewModel.moviesListData.observe(viewLifecycleOwner, { state ->
            when (state) {
                is MoviesListState.Success -> {
                    moviesAdapter.setItems(state.moviesList)
                }
                is MoviesListState.Error -> {
                    Toast.makeText(context, state.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupAdapter() {
        moviesRecyclerView.apply {
            adapter = moviesAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    companion object {
        fun newInstance() = MoviesListFragment()
    }
}
