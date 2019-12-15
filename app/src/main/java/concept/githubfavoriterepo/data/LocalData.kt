package concept.githubfavoriterepo.data

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.CopyOnWriteArraySet
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Stores repos data to memory cache or save favorites to [SharedPreferences]
 */
@Singleton
class LocalData @Inject constructor() {

    companion object {
        const val FAVORITES_KEY = "FAVORITES_KEY"
    }

    @Inject
    lateinit var preferences: SharedPreferences

    private val reposList = ArrayList<RepoEntry>()
    private val favorites = CopyOnWriteArraySet<String>()

    fun saveRepos(repos: List<RepoEntry>) {
        getFavoritesIds()
        repos.forEach {
            it.isFavorite = favorites.contains(it.stringId)
            reposList.add(it)
        }
    }

    fun updateFavorites(item: RepoEntry) {
        if (item.isFavorite) {
            favorites.add(item.stringId)
        } else {
            favorites.remove(item.stringId)
        }
        val message = "Favorites saving"
        val s = saveFavoritesIds()
            .subscribeOn(Schedulers.io())
            .subscribe({ Timber.d("%s done", message) }, Timber::e)
        Timber.d("%s%s", message, if (s.isDisposed) "" else "...")
    }

    fun getRepo(repoId: Int): RepoEntry? = reposList.firstOrNull { it.id == repoId }

    private fun getFavoritesIds() {
        preferences.getStringSet(FAVORITES_KEY, emptySet())?.forEach {
            favorites.add(it.toString())
        }
    }

    private fun saveFavoritesIds() = Completable.fromCallable {
        preferences.edit().putStringSet(FAVORITES_KEY, favorites).apply()
    }

}