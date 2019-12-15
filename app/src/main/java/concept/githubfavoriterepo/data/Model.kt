package concept.githubfavoriterepo.data

/**
 * Response model of [ApiService.getRepos]
 */
data class RepoEntry(
    val id: Int,
    val name: String,
    val stargazers_count: Int
) {
    var isFavorite: Boolean = false
    val stringId = id.toString()
}
