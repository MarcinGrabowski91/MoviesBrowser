package eu.gitcode.moviesbrowser.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import eu.gitcode.moviesbrowser.R
import org.koin.android.ext.android.inject

class MoviesListFragment : Fragment() {
    private val viewModel: MoviesListViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.movies_list_fragment, container, false)
    }

    companion object {
        fun newInstance() = MoviesListFragment()
    }
}
