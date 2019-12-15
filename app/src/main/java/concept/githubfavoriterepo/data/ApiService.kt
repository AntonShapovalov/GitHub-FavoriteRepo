package concept.githubfavoriterepo.data

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

/**
 * GitHub API retrofit service
 */
interface ApiService {

    @GET("orgs/square/repos")
    fun getRepos(): Observable<Response<List<RepoEntry>>>

}