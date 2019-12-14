package concept.githubfavoriterepo.data

import concept.githubfavoriterepo.di.ApiModule
import concept.githubfavoriterepo.di.DaggerApiTestComponent
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import rx.observers.TestSubscriber
import javax.inject.Inject

/**
 * Test for [RemoteData]
 */
class RemoteDataTest {

    @Inject
    lateinit var remoteModel: RemoteData

    @Before
    fun setUp() = DaggerApiTestComponent.builder()
        .apiModule(ApiModule())
        .build()
        .inject(this)

    @Test
    fun getRepos() {
        val testSubscriber = TestSubscriber<List<RepoEntry>>()
        remoteModel.getRepos()
            .doOnNext { list ->
                Assert.assertTrue(list.isNotEmpty())
                Assert.assertTrue(list[0].name.isNotEmpty())
                list.forEach { println(it) }
            }
            .subscribe(testSubscriber)
        testSubscriber.assertNoErrors()
        testSubscriber.assertCompleted()
        testSubscriber.assertUnsubscribed()
    }

}