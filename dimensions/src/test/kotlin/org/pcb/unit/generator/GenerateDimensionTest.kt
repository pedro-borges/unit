package org.pcb.unit.generator

import java.io.File
import kotlin.test.assertNotEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.pcb.unit.Composite
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.minus
import org.pcb.unit.plus

class GenerateDimensionTest
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

    @ParameterizedTest(name = "{0}")
    @MethodSource("getDimensions")
    fun combinations(dimension: Pair<String, Composite>)
    {
        val dimensionComposite = dimension.second
        val name = dimension.first

        val regions = HashMap<String, String>()

        dimensions
            .sortedBy { name }
            .forEach { resultDimension ->
                val resultDimensionComposite = resultDimension.second

                dimensions
                    .forEach { parameterDimension ->
                        val parameterDimensionComposite = parameterDimension.second
                        val parameterName = parameterDimension.first

                        var code = regions[parameterName] ?: ""

                        if (dimensionComposite - resultDimensionComposite == parameterDimensionComposite)
                        {
                            code = "\toperator fun div(that: ${resultDimension.first}) = $parameterName\n" + code
                        }
                        if (dimensionComposite + resultDimensionComposite == parameterDimensionComposite)
                        {
                            code += "\toperator fun times(that: ${resultDimension.first}) = $parameterName\n"
                        }

                        if (code != "") regions[parameterName] = code
                    }

            }

        val stub = this.javaClass.classLoader.getResource("package")!!

        val contents = regions.keys
            .asSequence()
            .sorted()
            .sortedBy { it != Scalar::class.simpleName }
            .sortedBy { it != name }
            .map { regions[it] }
            .joinToString(separator = "\n")
            .let {
                "package org.pcb.unit.dimensions\n\n" +
                "import org.pcb.unit.Composite\n\n" +
                "@Suppress(\"UNUSED_PARAMETER\")\n" +
                "${dimensionComposite.print()}\n" +
                "object $name : Dimension\n" +
                "{\n" +
                it +
                "}\n"
            }

        val source = File(stub.path.replace("target/test-classes/package", "src/main/kotlin/org/pcb/unit/dimensions/") + "$name.kt")

        source.writeText(contents)

        println(contents)
    }

    private fun Composite.print(): String
    {
        val result = ArrayList<String>()

        for (i in max() downTo min())
        {
            if (i == 0) continue

            if (angle == i) result += "angle = $angle"
            if (currency == i) result += "currency = $currency"
            if (distance == i) result += "distance = $distance"
            if (electricCurrent == i) result += "electricCurrent = $electricCurrent"
            if (luminousIntensity == i) result += "luminousIntensity = $luminousIntensity"
            if (mass == i) result += "mass = $mass"
            if (temperature == i) result += "temperature = $temperature"
            if (time == i) result += "time = $time"
        }

        return "@Composite(" + result.joinToString(",\n") + ")"
    }

    private fun Composite.max() =
        max(angle, currency, distance, electricCurrent, luminousIntensity, mass, temperature, time)

    private fun Composite.min() =
        min(angle, currency, distance, electricCurrent, luminousIntensity, mass, temperature, time)

    private fun min(value: Int, vararg values: Int): Int
    {
        var result = value

        for (v in values)
        {
            result = kotlin.math.min(result, v)
        }

        return result
    }

    private fun max(value: Int, vararg values: Int): Int
    {
        var result = value

        for (v in values)
        {
            result = kotlin.math.max(result, v)
        }

        return result
    }
}
