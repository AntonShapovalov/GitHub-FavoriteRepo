package concept.githubfavoriterepo.data

import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Provides GitHub repositories data from [ApiService]
 */
@Singleton
class RemoteData @Inject constructor() {

    @Inject lateinit var apiService: ApiService

    fun getRepos(): Observable<List<RepoEntry>> = apiService
        .getRepos()
        .map { if (it.isSuccessful) it.body() else throw RuntimeException("Unable to get Repositories info") }

}