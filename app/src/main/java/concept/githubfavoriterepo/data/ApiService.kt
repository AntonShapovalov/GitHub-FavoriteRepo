package concept.githubfavoriterepo.data

import retrofit2.Response
import retrofit2.http.GET
import rx.Observable

/**
 * GitHub API retrofit service
 */
interface ApiService {

    @GET("orgs/square/repos")
    fun getRepos(): Observable<Response<List<RepoEntry>>>

}