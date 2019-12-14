package concept.githubfavoriterepo.di

import concept.githubfavoriterepo.data.RemoteDataTest
import dagger.Component
import javax.inject.Singleton

/**
 * Provide dependencies for [RemoteDataTest]
 */
@Singleton
@Component(modules = [ApiModule::class])
interface ApiTestComponent {

    fun inject(test: RemoteDataTest)

}