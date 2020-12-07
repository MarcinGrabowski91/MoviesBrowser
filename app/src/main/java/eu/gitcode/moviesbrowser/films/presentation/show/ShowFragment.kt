package eu.gitcode.moviesbrowser.films.presentation.show

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.databinding.ShowFragmentBinding
import eu.gitcode.moviesbrowser.utils.viewBinding
import org.koin.android.ext.android.inject

class ShowFragment : Fragment(R.layout.show_fragment) {

    private val viewModel: ShowViewModel by inject()
    private val binding: ShowFragmentBinding by viewBinding(ShowFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showId = arguments?.getLong(SHOW_ID_KEY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewState()
        viewModel.loadData()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViewState() {
        viewModel.showData.observe(viewLifecycleOwner, { state ->
            when (state) {
                is ShowState.Success -> {
                    binding.showFragmentTitle.text = state.show.title
                    binding.showFragmentOverview.text = state.show.overview
                    binding.showFragmentRatingBar.rating =
                        state.show.rating / binding.showFragmentRatingBar.numStars
                    binding.showFragmentYear.text = state.show.year.toString()
                    state.show.genres.forEachIndexed { index, s ->
                        binding.showFragmentGenres.text =
                            binding.showFragmentGenres.text.toString() + s
                        if (index != state.show.genres.lastIndex) {
                            binding.showFragmentGenres.text =
                                binding.showFragmentGenres.text.toString() + ", "
                        }
                    }
                    binding.showFragmentCountry.text = state.show.country
                    binding.showFragmentVotes.text = state.show.votes.toString()
                }
                is ShowState.Error -> {
                    Toast.makeText(context, state.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        private const val SHOW_ID_KEY = "showId"

        fun newInstance(showId: Long) = ShowFragment().apply {
            arguments = Bundle().apply {
                putLong(SHOW_ID_KEY, showId)
            }
        }
    }
}
