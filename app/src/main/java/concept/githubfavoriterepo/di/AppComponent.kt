package concept.githubfavoriterepo.di

import android.content.Context
import dagger.Component
import javax.inject.Singleton

/**
 * Provide Application scope dependencies
 */
@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    fun context(): Context

}