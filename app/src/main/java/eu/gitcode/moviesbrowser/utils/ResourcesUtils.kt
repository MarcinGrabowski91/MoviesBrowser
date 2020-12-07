package eu.gitcode.moviesbrowser.utils

import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes

object ResourcesUtils {
    fun getColorFromAttribute(view: View, @AttrRes attrRes: Int): Int {
        val typedValue = TypedValue()
        val theme = view.context.theme
        theme.resolveAttribute(attrRes, typedValue, true)
        return typedValue.data
    }
}
