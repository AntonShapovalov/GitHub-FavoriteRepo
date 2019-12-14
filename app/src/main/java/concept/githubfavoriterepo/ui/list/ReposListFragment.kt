package concept.githubfavoriterepo.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import concept.githubfavoriterepo.R
import kotlinx.android.synthetic.main.fragment_repos_list.*

/**
 * Repositories list fragment
 */
class ReposListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repos_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        layoutManager.isSmoothScrollbarEnabled = true
        reposList.layoutManager = layoutManager
        reposList.adapter =
            ReposListAdapter { findNavController().navigate(R.id.dest_action_repo_details) }
    }

}