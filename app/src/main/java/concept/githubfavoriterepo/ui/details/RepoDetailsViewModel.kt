package concept.githubfavoriterepo.ui.details

import android.os.Bundle
import androidx.lifecycle.ViewModel
import concept.githubfavoriterepo.data.LocalData
import concept.githubfavoriterepo.data.RepoEntry
import javax.inject.Inject

class RepoDetailsViewModel : ViewModel() {

    @Inject lateinit var localData: LocalData

    private var position = -1
    var repo: RepoEntry? = null

    fun getData(arguments: Bundle?) {
        if (arguments == null) return
        val pos = RepoDetailsFragmentArgs.fromBundle(arguments).position
        val repos = localData.getRepos()
        if (pos in 0..repos.size) {
            position = pos
            repo = repos[position]
        }
    }

    fun updateFavorites(repoEntry: RepoEntry) {
        localData.updateFavorites(repoEntry, position)
    }

}