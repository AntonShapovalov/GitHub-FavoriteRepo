package concept.githubfavoriterepo.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import concept.githubfavoriterepo.R
import concept.githubfavoriterepo.data.RepoEntry
import concept.githubfavoriterepo.ui.activity.appComponent
import kotlinx.android.synthetic.main.fragment_repo_details.*

/**
 * Repository details screen
 */
class RepoDetailsFragment : Fragment() {

    private lateinit var viewModel: RepoDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val act = activity ?: return
        viewModel = ViewModelProviders.of(this)
            .get(RepoDetailsViewModel::class.java)
            .also { act.appComponent.inject(it) }
            .also { it.getData(arguments) }
            .also { updateUI(it.repo) }
    }

    private fun updateUI(repo: RepoEntry?) {
        if (repo == null) return
        textViewRepositoryName.text = repo.name
        textViewStarCount.text = repo.stringStarCount
        textViewDescription.text = repo.description
        imageButtonFavorite.setImageResource(getFavoriteImage(repo.isFavorite))
        imageButtonFavorite.setOnClickListener { updateFavorites() }
    }

    private fun updateFavorites() {
        val repo = viewModel.repo ?: return
        val inverted = !repo.isFavorite
        imageButtonFavorite.setImageResource(getFavoriteImage(inverted))
        repo.isFavorite = inverted
        viewModel.updateFavorites(repo)
    }

    private fun getFavoriteImage(isFavorite: Boolean): Int = if (isFavorite) {
        R.drawable.ic_favorite_filled
    } else {
        R.drawable.ic_favorite_border
    }

}