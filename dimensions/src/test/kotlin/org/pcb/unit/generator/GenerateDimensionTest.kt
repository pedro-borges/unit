package org.pcb.unit.generator

import java.io.File
import kotlin.test.Ignore
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.pcb.unit.Composite
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.generator.Dimensions.Definition
import org.pcb.unit.generator.Dimensions.max
import org.pcb.unit.generator.Dimensions.min
import org.pcb.unit.minus
import org.pcb.unit.plus

@Ignore
class GenerateDimensionTest
{
    companion object
    {
        @JvmStatic
        val dimensions = Dimensions.DIMENSIONS
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getDimensions")
    fun generate(dimension: Definition)
    {
        val regions = HashMap<String, String>()

        dimensions
            .sortedBy { dimension.name }
            .forEach { resultDimension ->
                dimensions
                    .forEach { parameterDimension ->
                        val parameterDimensionComposite = parameterDimension.composite
                        val parameterName = parameterDimension.name

                        var code = regions[parameterName] ?: ""

                        if (dimension.composite - resultDimension.composite == parameterDimensionComposite)
                        {
                            code = "\toperator fun div(that: ${resultDimension.name}) = $parameterName\n" + code
                        }
                        if (dimension.composite + resultDimension.composite == parameterDimensionComposite)
                        {
                            code += "\toperator fun times(that: ${resultDimension.name}) = $parameterName\n"
                        }

                        if (code != "") regions[parameterName] = code
                    }
            }

        val stub = this.javaClass.classLoader.getResource("package")!!

        val contents = regions.keys
            .asSequence()
            .sorted()
            .sortedBy { it != Scalar::class.simpleName }
            .sortedBy { it != dimension.name }
            .map { regions[it] }
            .joinToString(separator = "\n")
            .let {
                "package org.pcb.unit.dimensions\n\n" +
                "import org.pcb.unit.Composite\n\n" +
                "@Suppress(\"UNUSED_PARAMETER\")\n" +
                "${dimension.composite.print()}\n" +
                "object ${dimension.name} : Dimension\n" +
                "{\n" +
                it +
                "}\n"
            }
            .replace("\t", "    ")

        val source = File(stub.path.replace("target/test-classes/package", "src/main/kotlin/org/pcb/unit/dimensions/") + "${dimension.name}.kt")

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
            if (substance == i) result += "substance = $substance"
            if (temperature == i) result += "temperature = $temperature"
            if (time == i) result += "time = $time"
        }

        return "@Composite(" + result.joinToString(",\n") + ")"
    }
}
