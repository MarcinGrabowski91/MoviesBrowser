package eu.gitcode.moviesbrowser.films.presentation.show

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.databinding.DetailsFragmentBinding
import eu.gitcode.moviesbrowser.utils.ResourcesUtils
import eu.gitcode.moviesbrowser.utils.viewBinding
import org.koin.android.ext.android.inject

class ShowFragment : Fragment(R.layout.details_fragment) {

    private val viewModel: ShowViewModel by inject()
    private val binding: DetailsFragmentBinding by viewBinding(DetailsFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showId = arguments?.getLong(SHOW_ID_KEY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewState()
        viewModel.loadData()
        setupViews()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViewState() {
        viewModel.showData.observe(viewLifecycleOwner, { state ->
            binding.loadingLay.visibility = View.GONE
            when (state) {
                is ShowState.Success -> {
                    binding.detailsFragmentTitle.text = state.show.title
                    binding.detailsFragmentOverview.text = state.show.overview
                    binding.detailsFragmentRatingBar.rating = state.show.rating
                    binding.detailsFragmentYear.text = state.show.year.toString()
                    state.show.genres.forEachIndexed { index, s ->
                        binding.detailsFragmentGenres.text =
                            binding.detailsFragmentGenres.text.toString() + s
                        if (index != state.show.genres.lastIndex) {
                            binding.detailsFragmentGenres.text =
                                binding.detailsFragmentGenres.text.toString() + ", "
                        }
                    }
                    binding.detailsFragmentCountry.text = state.show.country
                    binding.detailsFragmentVotes.text = state.show.votes.toString()
                }
                is ShowState.Error -> {
                    Toast.makeText(context, state.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupViews() {
        binding.loadingLay.setBackgroundColor(
            ResourcesUtils.getColorFromAttribute(
                binding.loadingLay,
                R.attr.colorShow
            )
        )
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
