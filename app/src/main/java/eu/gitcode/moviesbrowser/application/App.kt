package eu.gitcode.moviesbrowser.application

import android.app.Application
import eu.gitcode.moviesbrowser.BuildConfig
import eu.gitcode.moviesbrowser.di.apiModule
import eu.gitcode.moviesbrowser.di.filmsModule
import eu.gitcode.moviesbrowser.di.networkModule
import eu.gitcode.moviesbrowser.di.viewModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

@ExperimentalSerializationApi
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                viewModule,
                filmsModule,
                networkModule,
                apiModule
            )
        }
    }
}
