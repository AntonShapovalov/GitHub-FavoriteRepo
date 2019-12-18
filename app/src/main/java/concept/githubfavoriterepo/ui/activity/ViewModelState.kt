package concept.githubfavoriterepo.ui.activity

import androidx.lifecycle.MutableLiveData
import concept.githubfavoriterepo.data.RepoEntry

/**
 * General State of all ViewModel: Idle, Data or Error
 */
sealed class ViewModelState
object StateIdle : ViewModelState()
data class StateError(val throwable: Throwable) : ViewModelState()
data class ReposLoaded(val repos: List<RepoEntry>) : ViewModelState()

/**
 * ViewModelState LiveData - to init default state value
 */
class StateLiveData(state: ViewModelState = StateIdle) : MutableLiveData<ViewModelState>() {
    init {
        value = state
    }
}

fun ViewModelState.name() = "state = ${this.javaClass.simpleName}"