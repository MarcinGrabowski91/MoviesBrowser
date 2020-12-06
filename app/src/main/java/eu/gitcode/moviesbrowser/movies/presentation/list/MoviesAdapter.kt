package eu.gitcode.moviesbrowser.movies.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.movies.domain.enum.MovieType
import eu.gitcode.moviesbrowser.movies.domain.model.MovieDomainModel

internal class MoviesAdapter(private val movieAdapterListener: MoviesAdapterListener) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val moviesList = mutableListOf<MovieDomainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bind(movie, movieAdapterListener)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun setItems(moviesList: List<MovieDomainModel>) {
        this.moviesList.clear()
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
    }

    internal class MovieViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(movie: MovieDomainModel, movieAdapterListener: MoviesAdapterListener) {
            view.findViewById<TextView>(R.id.movieItemTitle).text = movie.title
            view.findViewById<TextView>(R.id.movieItemYear).text = movie.year.toString()
            view.findViewById<TextView>(R.id.movieItemWatchers).text = movie.watchers.toString()

            if (movie.type == MovieType.MOVIE) {
                view.setOnClickListener { movieAdapterListener.onMovieClicked(movie.id) }
            } else if (movie.type == MovieType.SHOW) {
                view.setOnClickListener { movieAdapterListener.onShowClicked(movie.id) }
            }
        }
    }

    interface MoviesAdapterListener {
        fun onMovieClicked(id: Long)

        fun onShowClicked(id: Long)
    }
}
