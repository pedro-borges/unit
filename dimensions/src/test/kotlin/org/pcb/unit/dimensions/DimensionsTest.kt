package org.pcb.unit.dimensions

import kotlin.math.absoluteValue
import kotlin.test.assertNotEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.pcb.unit.config.Color
import org.pcb.unit.generator.Dimensions
import org.pcb.unit.generator.Dimensions.Definition
import org.pcb.unit.generator.map

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

    @ParameterizedTest(name = "{0}")
    @MethodSource("getDimensions")
    fun print(dimension: Definition)
    {
        val map = dimension.composite.map()
        val colors = mapOf(
            "angle" to Color.CYAN,
            "currency" to Color.NONE,
            "distance" to Color.RED,
            "electricCurrent" to Color.YELLOW,
            "luminousIntensity" to Color.NONE,
            "mass" to Color.BLUE,
            "substance" to Color.MAGENTA,
            "temperature" to Color.GREEN,
            "time" to Color.WHITE,
        )

        print(dimension.name + "\t")
        map.keys.sortedByDescending { map[it] }
            .map { colors[it]!!.iso + (if (map[it]!! > 0) "▀ " else "▄ ").repeat(map[it]!!.absoluteValue) + Color.NONE.iso }
            .forEach { print(it) }
        println()
        println()
    }
}