package eu.gitcode.moviesbrowser.films.presentation.list

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.gitcode.moviesbrowser.R
import eu.gitcode.moviesbrowser.films.domain.enum.MovieType
import eu.gitcode.moviesbrowser.films.domain.model.FilmDomainModel

internal class FilmsAdapter(private val movieAdapterListener: MoviesAdapterListener) :
    RecyclerView.Adapter<FilmsAdapter.MovieViewHolder>() {

    private val moviesList = mutableListOf<FilmDomainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bind(movie, movieAdapterListener)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun setItems(moviesList: List<FilmDomainModel>) {
        this.moviesList.clear()
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
    }

    internal class MovieViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(film: FilmDomainModel, movieAdapterListener: MoviesAdapterListener) {
            view.findViewById<TextView>(R.id.movieItemTitle).text = film.title
            view.findViewById<TextView>(R.id.movieItemYear).text = film.year.toString()
            view.findViewById<TextView>(R.id.movieItemWatchers).text = film.watchers.toString()

            val typedValue = TypedValue()
            val theme = view.context.theme
            if (film.type == MovieType.MOVIE) {
                view.findViewById<TextView>(R.id.filmType).text =
                    view.context.getString(R.string.movie)
                view.setOnClickListener { movieAdapterListener.onMovieClicked(film.id) }
                theme.resolveAttribute(R.attr.colorMovie, typedValue, true)
            } else if (film.type == MovieType.SHOW) {
                view.findViewById<TextView>(R.id.filmType).text =
                    view.context.getString(R.string.show)
                view.setOnClickListener { movieAdapterListener.onShowClicked(film.id) }
                theme.resolveAttribute(R.attr.colorShow, typedValue, true)
            }
            view.setBackgroundColor(typedValue.data)
        }
    }

    interface MoviesAdapterListener {
        fun onMovieClicked(id: Long)

        fun onShowClicked(id: Long)
    }
}
