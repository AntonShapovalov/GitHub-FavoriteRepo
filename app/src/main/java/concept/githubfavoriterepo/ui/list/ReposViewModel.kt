package concept.githubfavoriterepo.ui.list

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import concept.githubfavoriterepo.data.LocalData
import concept.githubfavoriterepo.data.RemoteData
import concept.githubfavoriterepo.ui.activity.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ReposViewModel : ViewModel() {

    @Inject lateinit var localData: LocalData
    @Inject lateinit var remoteData: RemoteData

    val state = StateLiveData()
    val progress = MutableLiveData<Boolean>()
    val favoritesUpdate = MutableLiveData<Int>()

    private var initDisposable = Disposables.empty()
    private var dataDisposable = Disposables.disposed()
    private var favoritesDisposable = Disposables.empty()

    /**
     * Initialization of Dagger in background
     */
    fun initAppComponent(activity: FragmentActivity) {
        initDisposable = Completable.fromCallable { activity.appComponent.inject(this) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progress.postValue(true) }
            .subscribe({ state.value = InitCompleted }, { state.value = StateError(it) })
    }

    /**
     * Load repositories from local cache or remote api
     */
    fun loadData() {
        if (state.value is ReposLoaded || !dataDisposable.isDisposed) return
        dataDisposable = localData.getReposObservable()
            .filter { it.isNotEmpty() }
            .concatWith(remoteData.getRepos())
            .doOnNext { localData.saveRepos(it) }
            .map { localData.getRepos() }
            .take(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progress.postValue(true) }
            .doFinally { progress.postValue(false) }
            .subscribe({
                state.value = ReposLoaded(it)
                subscribeToFavoritesUpdate()
            }, { state.value = StateError(it) })
    }

    private fun subscribeToFavoritesUpdate() {
        favoritesDisposable = localData.favoritesUpdate()
            .switchMap { Flowable.just(it) }
            .subscribe({ favoritesUpdate.value = it }, Timber::e)
    }

    override fun onCleared() {
        super.onCleared()
        initDisposable.dispose()
        dataDisposable.dispose()
        favoritesDisposable.dispose()
    }

}