package concept.githubfavoriterepo.di

import concept.githubfavoriterepo.data.LocalDataTest
import dagger.Component
import javax.inject.Singleton

/**
 * Provides dependencies for [LocalDataTest]
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppTestComponent {

    fun inject(test: LocalDataTest)

}