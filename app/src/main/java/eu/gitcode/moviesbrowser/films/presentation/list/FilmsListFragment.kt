package eu.gitcode.moviesbrowser.films.presentation.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.databinding.FilmsListFragmentBinding
import eu.gitcode.moviesbrowser.films.presentation.movie.MovieFragment
import eu.gitcode.moviesbrowser.utils.viewBinding
import org.koin.android.ext.android.inject

class FilmsListFragment : Fragment(R.layout.films_list_fragment),
    FilmsAdapter.MoviesAdapterListener {
    private val viewModel: FilmsListViewModel by inject()
    private val binding: FilmsListFragmentBinding by viewBinding(FilmsListFragmentBinding::bind)

    private val filmsAdapter = FilmsAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupViewState()
        viewModel.loadData()
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

    private fun setupViewState() {
        viewModel.filmsListData.observe(viewLifecycleOwner, { state ->
            when (state) {
                is FilmsListState.Success -> {
                    filmsAdapter.setItems(state.moviesList)
                }
                is FilmsListState.Error -> {
                    Toast.makeText(context, state.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupAdapter() {
        binding.filmsRecyclerView.apply {
            adapter = filmsAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    companion object {
        fun newInstance() = FilmsListFragment()
    }
}
