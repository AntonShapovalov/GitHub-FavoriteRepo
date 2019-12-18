package concept.githubfavoriterepo

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Simple test to try something
 */
class Simple {

    @Test
    @Throws(Exception::class)
    fun test() {
        val relay: BehaviorRelay<Long> = BehaviorRelay.create()
        relay.toFlowable(BackpressureStrategy.LATEST)
            .observeOn(Schedulers.newThread())
            .doOnEach { println("subscription on ${Thread.currentThread().name}") }
//            .subscribeOn(Schedulers.newThread())
            .subscribe()
        Observable.interval(100, TimeUnit.MILLISECONDS, Schedulers.newThread())
            .doOnEach { println("relay accept on ${Thread.currentThread().name}") }
            .take(5)
            .subscribe { relay.accept(it) }
        CountDownLatch(1).await(1, TimeUnit.SECONDS)
    }

}