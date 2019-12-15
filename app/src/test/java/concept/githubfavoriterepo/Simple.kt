package concept.githubfavoriterepo

import org.junit.Assert
import org.junit.Test
import java.util.concurrent.CopyOnWriteArraySet

/**
 * Simple test to try something
 */
class Simple {

    @Test
    @Throws(Exception::class)
    fun test() {
        val s = CopyOnWriteArraySet<Int>()
        s.add(1)
        s.add(2)
        s.remove(3)
        Assert.assertEquals(2, s.size)
    }

}