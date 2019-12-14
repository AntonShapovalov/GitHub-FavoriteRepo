package concept.githubfavoriterepo.app

import android.app.Application
import concept.githubfavoriterepo.di.ApiModule
import concept.githubfavoriterepo.di.AppComponent
import concept.githubfavoriterepo.di.AppModule
import concept.githubfavoriterepo.di.DaggerAppComponent

/**
 * Instance of Application, provide Singleton dependencies via [AppComponent]
 */
class FRApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .apiModule(ApiModule())
            .build()
    }

}