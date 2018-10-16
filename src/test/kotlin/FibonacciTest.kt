import com.voloshko.algorithms.dynamic.fibonacci
import junit.framework.TestCase

class FibonacciTest(testName: String) : TestCase(testName) {

    fun test0() {
        assertEquals(fibonacci(0), 0)
    }

    fun test1() {
        assertEquals(fibonacci(1), 1)
        assertEquals(fibonacci(2), 1)
    }

    fun test5() {
        assertEquals(fibonacci(5), 5)
    }

    fun test100() {
        assertEquals(fibonacci(50), 12586269025)
    }
}
