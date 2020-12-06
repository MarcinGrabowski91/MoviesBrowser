package eu.gitcode.moviesbrowser.movies.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.databinding.MoviesListFragmentBinding
import eu.gitcode.moviesbrowser.movies.presentation.details.MovieDetailsFragment
import org.koin.android.ext.android.inject

class FilmsListFragment : Fragment(R.layout.movies_list_fragment),
    FilmsAdapter.MoviesAdapterListener {
    private val viewModel: FilmsListViewModel by inject()
    private var _binding: MoviesListFragmentBinding? = null
    private val binding get() = _binding!!

    private val moviesAdapter = FilmsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MoviesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupViewState()
        viewModel.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
                is FilmsListState.Success -> {
                    moviesAdapter.setItems(state.moviesList)
                }
                is FilmsListState.Error -> {
                    Toast.makeText(context, state.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupAdapter() {
        binding.moviesRecyclerView.apply {
            adapter = moviesAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    companion object {
        fun newInstance() = FilmsListFragment()
    }
}
