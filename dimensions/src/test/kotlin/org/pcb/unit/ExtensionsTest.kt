package org.pcb.unit

import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ExtensionsTest
{
    companion object
    {
        @JvmStatic
        val compensations = listOf(9 to 9,
                                   8 to 6,
                                   7 to 6,
                                   6 to 6,
                                   5 to 3,
                                   4 to 3,
                                   3 to 3,
                                   2 to 0,
                                   1 to 0,
                                   0 to 0,
                                   -1 to -3,
                                   -2 to -3,
                                   -3 to -3,
                                   -4 to -6,
                                   -5 to -6,
                                   -6 to -6,
                                   -7 to -9)
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getCompensations")
    fun testCompensation(pair: Pair<Int, Int>)
    {
        assertEquals(pair.second, pair.first.compensation())
    }
}