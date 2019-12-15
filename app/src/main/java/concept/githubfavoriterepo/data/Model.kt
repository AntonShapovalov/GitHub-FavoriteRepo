package concept.githubfavoriterepo.data

/**
 * Response model of [ApiService.getRepos]
 */
data class RepoEntry(
    val id: Int,
    val name: String,
    val stargazers_count: Int,
    val description: String? = null
) {
    var isFavorite: Boolean = false
    val stringId: String get() = id.toString()
    val stringStarCount: String get() = stargazers_count.toString()
}
