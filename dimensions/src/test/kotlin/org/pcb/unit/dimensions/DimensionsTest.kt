package org.pcb.unit.dimensions

import kotlin.test.assertNotEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.pcb.unit.generator.Dimensions
import org.pcb.unit.generator.Dimensions.Definition

class DimensionsTest
{
    companion object
    {
        @JvmStatic
        val dimensions = Dimensions.DIMENSIONS
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getDimensions")
    fun uniqueness(dimension: Definition)
    {
        dimensions
            .filter { dimension.name != it.name }
            .forEach { other ->
                assertNotEquals(
                    illegal = dimension.composite,
                    actual = other.composite,
                    message = "${dimension.name} == ${other.name}")
            }
    }
}