import com.voloshko.algorithms.dynamic.longestSubsequence
import junit.framework.TestCase

class LongestSubsequenceTest(testName: String) : TestCase(testName) {

    fun test0() {
        assertEquals(longestSubsequence(intArrayOf(1, 11, 2, 10, 4, 5, 2, 1)), 6)
    }
}