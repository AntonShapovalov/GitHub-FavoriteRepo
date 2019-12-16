package concept.githubfavoriterepo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import concept.githubfavoriterepo.R
import concept.githubfavoriterepo.ui.list.ReposViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

/**
 * Navigation router, handles app initialisation, loading progress and errors as well
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
        viewModel = ViewModelProviders.of(this).get(ReposViewModel::class.java)
            .also { vm -> vm.progress.observe(this, Observer { updateProgress(it) }) }
            .also { vm -> vm.state.observe(this, Observer { onStateChanged(it) }) }
            .also { it.initAppComponent(this) }
    }

    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.nav_host_fragment).navigateUp()

    private fun updateProgress(isProcess: Boolean) {
        if (isProcess) progress.show() else progress.gone()
    }

    private fun onStateChanged(state: ViewModelState?) {
        Timber.d(state?.name())
        if (state is StateError) showError(state.throwable.localizedMessage)
    }

    private fun showError(text: String?) {
        Snackbar.make(coordinatorLayout, text ?: "Unknown error", Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.text_retry) { viewModel.loadData() }
            .show()
    }


}
