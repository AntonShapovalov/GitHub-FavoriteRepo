package concept.githubfavoriterepo.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import concept.githubfavoriterepo.R
import concept.githubfavoriterepo.ui.activity.ReposLoaded
import concept.githubfavoriterepo.ui.activity.ViewModelState
import concept.githubfavoriterepo.ui.activity.name
import kotlinx.android.synthetic.main.fragment_repos_list.*
import timber.log.Timber

/**
 * Repositories list fragment
 */
class ReposListFragment : Fragment() {

    private lateinit var viewModel: ReposViewModel
    private val adapter = ReposListAdapter { showRepoDetails(it) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repos_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        layoutManager.isSmoothScrollbarEnabled = true
        reposList.layoutManager = layoutManager
        reposList.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val act = activity ?: return
        viewModel = ViewModelProviders.of(act)
            .get(ReposViewModel::class.java)
            .also { vm -> vm.state.observe(this, Observer { onStateChanged(it) }) }
            .also { vm -> vm.favoritesUpdate.observe(this, Observer { onFavoritesUpdate(it) }) }
            .also { it.loadData() }
    }

    private fun onStateChanged(state: ViewModelState?) {
        Timber.d(state?.name())
        if (state is ReposLoaded) adapter.setItems(state.repos)
    }

    private fun showRepoDetails(position: Int) {
        val direction = ReposListFragmentDirections.actionOpenRepoDetails(position)
        findNavController().navigate(direction)
    }

    private fun onFavoritesUpdate(position: Int) {
        adapter.notifyItemChanged(position)
    }

}