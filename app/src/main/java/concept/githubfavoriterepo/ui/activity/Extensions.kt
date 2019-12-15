package concept.githubfavoriterepo.ui.activity

import android.view.View
import androidx.fragment.app.FragmentActivity
import concept.githubfavoriterepo.app.FRApplication
import concept.githubfavoriterepo.di.AppComponent

val FragmentActivity.appComponent: AppComponent get() = (application as FRApplication).appComponent

fun View.show() = let { visibility = View.VISIBLE }

fun View.gone() = let { visibility = View.GONE }
