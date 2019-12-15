package concept.githubfavoriterepo.di

import concept.githubfavoriterepo.ui.list.ReposViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Provide Application scope dependencies
 */
@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    fun inject(reposViewModel: ReposViewModel)

}