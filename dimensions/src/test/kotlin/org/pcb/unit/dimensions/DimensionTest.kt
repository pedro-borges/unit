package org.pcb.unit.dimensions

import java.io.File
import kotlin.test.assertNotEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.pcb.unit.Composite
import org.pcb.unit.minus
import org.pcb.unit.plus
import org.reflections.Reflections

class DimensionTest
{
    companion object
    {
        @JvmStatic
        val dimensions =
            Reflections("org.pcb.unit.dimensions")
                .getSubTypesOf(Dimension::class.java)
                .sortedBy { it.simpleName }
                .toList()
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getDimensions")
    fun uniqueness(dimension: Class<out Dimension>)
    {
        dimensions
            .filter { !it.isAssignableFrom(dimension) }
            .forEach { other ->
                assertNotEquals(
                    illegal = dimension.kotlin.annotations.first { it is Composite } as Composite,
                    actual = other.kotlin.annotations.first { it is Composite } as Composite)
            }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getDimensions")
    fun combinations(dimension: Class<out Dimension>)
    {
        val dimensionComposite = dimension.kotlin.annotations.first { it is Composite } as Composite

        val regions = HashMap<String, String>()

        dimensions
            .sortedBy { it.simpleName }
            .forEach { resultDimension ->
                val resultDimensionComposite = resultDimension.kotlin.annotations.first { it is Composite } as Composite

                dimensions
                    .forEach { parameterDimension ->
                        val parameterDimensionComposite = parameterDimension.kotlin.annotations.first { it is Composite } as Composite
                        var code = regions[parameterDimension.simpleName] ?: ""

                        if (dimensionComposite - resultDimensionComposite == parameterDimensionComposite)
                        {
                            code = "\toperator fun div(that: ${resultDimension.simpleName}) = ${parameterDimension.simpleName}\n" + code
                        }
                        if (dimensionComposite + resultDimensionComposite == parameterDimensionComposite)
                        {
                            code += "\toperator fun times(that: ${resultDimension.simpleName}) = ${parameterDimension.simpleName}\n"
                        }

                        if (code != "") regions[parameterDimension.simpleName] = code
                    }

            }

        val stub = this.javaClass.classLoader.getResource("org/pcb/unit/dimensions/" + dimension.simpleName)!!

        val contents = stub.readText()
            .replace(oldValue = "\$operators",
                     newValue = regions.keys
                         .asSequence()
                         .sorted()
                         .sortedBy { it != Scalar::class.simpleName }
                         .sortedBy { it != dimension.simpleName }
                         .map { regions[it] }
                         .joinToString(separator = "\n")
                         .dropLast(1))

        val source = File(stub.path.replace("target/test-classes/", "src/main/kotlin/") + ".kt")

        source.writeText(contents)

        println(contents)
    }
}