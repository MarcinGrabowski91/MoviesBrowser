package eu.gitcode.moviesbrowser.films.domain.enum

import eu.gitcode.moviesbrowser.R

enum class OrderType {
    ASCENDING {
        override fun getResId() = R.string.ascending
    },
    DESCENDING {
        override fun getResId() = R.string.descending
    };

    abstract fun getResId(): Int
}
