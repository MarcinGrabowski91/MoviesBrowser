package eu.gitcode.moviesbrowser.movies.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.movies.presentation.list.MoviesListFragment

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MoviesListFragment.newInstance())
                .commitNow()
        }
    }
}
