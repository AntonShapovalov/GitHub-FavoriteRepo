package concept.githubfavoriterepo.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import concept.githubfavoriterepo.di.AppModule
import concept.githubfavoriterepo.di.DaggerAppTestComponent
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Test for [LocalData]
 */
@RunWith(AndroidJUnit4::class)
class LocalDataTest {

    @Inject lateinit var localData: LocalData

    private val dummy = "dummy"
    private val dummyRepos: ArrayList<RepoEntry> get() = arrayListOf(
        RepoEntry(1, dummy, 1),
        RepoEntry(2, dummy, 2)
    )

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        DaggerAppTestComponent.builder()
            .appModule(AppModule(context))
            .build()
            .inject(this)
        localData.clear()
    }

    @Test
    fun saveRemoteData() {
        val repos = dummyRepos
        localData.saveRepos(repos)
        val localRepos = localData.getRepos()
        Assert.assertEquals(repos.size, localRepos.size)
        val favorites = localData.getFavoritesIds()
        Assert.assertTrue(favorites?.isEmpty() ?: true)
    }

    @Test
    fun saveFavorites() {
        val repos = dummyRepos
        localData.saveRepos(repos)
        val repo = repos[0]
        // simulation of too often button click
        arrayOf(true, false, true, false, true).forEach {
            repo.isFavorite = it
            localData.updateFavorites(repo)
        }
        CountDownLatch(1).await(1, TimeUnit.SECONDS)
        val favorites = localData.getFavoritesIds()
        Assert.assertTrue(favorites?.isNotEmpty() ?: false)
        Assert.assertEquals(1, favorites?.size)
        Assert.assertTrue(favorites?.contains(repo.stringId) ?: false)
    }

    @After
    fun tearDown() = localData.clear()

}