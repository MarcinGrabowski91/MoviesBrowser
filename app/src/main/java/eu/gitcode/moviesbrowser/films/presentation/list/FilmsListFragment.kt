package eu.gitcode.moviesbrowser.films.presentation.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.databinding.FilmsListFragmentBinding
import eu.gitcode.moviesbrowser.films.domain.enum.OrderType
import eu.gitcode.moviesbrowser.films.presentation.movie.MovieFragment
import eu.gitcode.moviesbrowser.films.presentation.show.ShowFragment
import eu.gitcode.moviesbrowser.utils.viewBinding
import org.koin.android.ext.android.inject

class FilmsListFragment : Fragment(R.layout.films_list_fragment),
    FilmsAdapter.MoviesAdapterListener {
    private val viewModel: FilmsListViewModel by inject()
    private val binding: FilmsListFragmentBinding by viewBinding(FilmsListFragmentBinding::bind)

    private val filmsAdapter = FilmsAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewState()
        viewModel.loadFilmsData()
    }

    override fun onMovieClicked(id: Long) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, MovieFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    override fun onShowClicked(id: Long) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, ShowFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    private fun setupViews() {
        binding.filmsRecyclerView.adapter = filmsAdapter
        binding.moviesListRefreshLay.setOnRefreshListener { viewModel.loadFilmsData() }
        binding.moviesListRefreshLay.isRefreshing = true
        binding.orderBtn.setOnClickListener { openOrderChooser() }
    }

    private fun setupViewState() {
        viewModel.filmsListData.observe(viewLifecycleOwner, { state ->
            binding.moviesListRefreshLay.isRefreshing = false
            when (state) {
                is FilmsListState.Success -> {
                    filmsAdapter.setItems(state.filmsList)
                }
                is FilmsListState.Error -> {
                    Toast.makeText(context, state.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun openOrderChooser() {
        val ordersArray =
            listOf(
                getString(OrderType.ASCENDING.getResId()),
                getString(OrderType.DESCENDING.getResId())
            ).toTypedArray()
        AlertDialog.Builder(requireContext())
            .setItems(ordersArray) { _, which ->
                val orderType = OrderType.values()[which]
                viewModel.orderType = orderType
                filmsAdapter.setOrder(orderType)
            }
            .create().show()
    }

    companion object {
        fun newInstance() = FilmsListFragment()
    }
}
