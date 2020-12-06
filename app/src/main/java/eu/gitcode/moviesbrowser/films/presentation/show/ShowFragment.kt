package eu.gitcode.moviesbrowser.films.presentation.show

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupViewState() {
        viewModel.showData.observe(viewLifecycleOwner, { state ->
            when (state) {
                is ShowState.Success -> {
                    // TODO: 07/12/2020 fill data
                }
                is ShowState.Error -> {
                    Toast.makeText(context, state.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        fun newInstance() = ShowFragment()
    }
}
