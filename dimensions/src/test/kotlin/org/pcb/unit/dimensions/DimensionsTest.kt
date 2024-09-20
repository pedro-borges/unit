package org.pcb.unit.dimensions

import kotlin.test.assertNotEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.pcb.unit.Composite
import org.pcb.unit.generator.Dimensions

class DimensionsTest
{
    companion object
    {
        @JvmStatic
        val dimensions = Dimensions.DIMENSIONS
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getDimensions")
    fun uniqueness(dimension: Pair<String, Composite>)
    {
        dimensions
            .filter { dimension.first != it.first }
            .forEach { other ->
                assertNotEquals(
                    illegal = dimension.second,
                    actual = other.second)
            }
    }
}