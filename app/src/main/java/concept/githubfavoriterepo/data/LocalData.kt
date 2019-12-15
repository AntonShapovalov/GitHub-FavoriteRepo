package concept.githubfavoriterepo.data

import android.content.SharedPreferences
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.Disposables
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

    @Inject lateinit var preferences: SharedPreferences

    private val reposList = ArrayList<RepoEntry>()
    private val favorites = CopyOnWriteArraySet<String>()
    private var relay: BehaviorRelay<Boolean> = BehaviorRelay.create()
    private var disposable = Disposables.disposed()

    fun saveRepos(repos: List<RepoEntry>) {
        getFavoritesIds()?.let { favorites.addAll(it) }
        repos.forEach {
            it.isFavorite = favorites.contains(it.stringId)
            reposList.add(it)
        }
    }

    fun getRepos() = reposList

    fun updateFavorites(item: RepoEntry) {
        if (item.isFavorite) {
            favorites.add(item.stringId)
        } else {
            favorites.remove(item.stringId)
        }
        if (disposable.isDisposed) {
            disposable = saveFavoritesIds()
        }
        relay.accept(item.isFavorite)
    }

    fun getFavoritesIds(): Set<String>? = preferences.getStringSet(FAVORITES_KEY, emptySet())

    private fun saveFavoritesIds() = relay.toFlowable(BackpressureStrategy.LATEST)
        .switchMap { Flowable.just(favorites) }
        .doOnNext { preferences.edit().putStringSet(FAVORITES_KEY, it).apply() }
        .subscribeOn(Schedulers.io())
        .subscribe({ Timber.d("Favorites saved") }, Timber::e)

    fun clear() {
        reposList.clear()
        favorites.clear()
        preferences.edit().putStringSet(FAVORITES_KEY, emptySet()).apply()
    }

}