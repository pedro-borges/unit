package org.pcb.unit.dimensions

import java.io.File
import kotlin.test.assertNotEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.pcb.unit.Composite
import org.pcb.unit.minus
import org.pcb.unit.plus

class DimensionTest
{
    companion object
    {
        @JvmStatic
        val dimensions =
            listOf("Acceleration" to Composite(distance = 1, time = -2),
                   "Angle" to Composite(angle = 1),
                   "AngularAcceleration" to Composite(angle = 1, time = -2),
                   "AngularMomentum" to Composite(mass = 2, distance = 1, time = -1),
                   "AngularVelocity" to Composite(angle = 1, time = -1),
                   "Area" to Composite(distance = 2),
                   "Capacitance" to Composite(time = 4, electricCurrent = 2, mass = -1, distance = -2),
                   "Currency" to Composite(currency = 1),
                   "Debt" to Composite(currency = 1, time = 1),
                   "Distance" to Composite(distance = 1),
                   "ElectricCharge" to Composite(electricCurrent = 1, time = 1),
                   "ElectricConductance" to Composite(time = 3, electricCurrent = 2, mass = -1, distance = -2),
                   "ElectricCurrent" to Composite(electricCurrent = 1),
                   "ElectricResistance" to Composite(distance = 2, mass = 1, electricCurrent = -2, time = -3),
                   "ElectricTension" to Composite(distance = 2, mass = 1, electricCurrent = -1, time = -3),
                   "Energy" to Composite(distance = 2, mass = 1, time = -2),
                   "EnergyDensity" to Composite(mass = 1, distance = -1, time = -2),
                   "Force" to Composite(distance = 1, mass = 1, time = -2),
                   "Frequency" to Composite(time = -1),
                   "Illuminance" to Composite(luminousIntensity = 1, angle = 2, distance = -2),
                   "Inductance" to Composite(distance = 2, mass = 1, electricCurrent = -2, time = -2),
                   "Luminance" to Composite(luminousIntensity = 1, distance = -2),
                   "LuminousEnergy" to Composite(angle = 2, luminousIntensity = 1, time = 1),
                   "LuminousFlux" to Composite(angle = 2, luminousIntensity = 1),
                   "LuminousIntensity" to Composite(luminousIntensity = 1),
                   "MagneticFlux" to Composite(distance = 2, mass = 1, electricCurrent = -1, time = -2),
                   "MagneticFluxDensity" to Composite(mass = 1, electricCurrent = -1, time = -2),
                   "Mass" to Composite(mass = 1),
                   "MassFlowRate" to Composite(mass = 1, time = -1),
                   "Momentum" to Composite(mass = 1, distance = 1, time = -1),
                   "Pace" to Composite(time = 1, distance = -1),
                   "Power" to Composite(distance = 2, mass = 1, time = -3),
                   "Rent" to Composite(currency = 1, time = -1),
                   "Scalar" to Composite(),
                   "SolidAngle" to Composite(angle = 2),
                   "SpecificVolume" to Composite(distance = 3, mass = -1),
                   "Speed" to Composite(distance = 1, time = -1),
                   "Temperature" to Composite(temperature = 1),
                   "Time" to Composite(time = 1),
                   "Volume" to Composite(distance = 3)
            )
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getDimensions")
    fun uniqueness(dimension: Pair<String, Composite>)
    {
        dimensions
            .filter { dimension.second != it.second }
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

//        source.writeText(contents)

        println(contents)
    }

    private fun Composite.print(): String
    {
        val result = ArrayList<String>()

        if (angle != 0) result += "angle = $angle"
        if (currency != 0) result += "currency = $currency"
        if (distance != 0) result += "distance = $distance"
        if (electricCurrent != 0) result += "electricCurrent = $electricCurrent"
        if (luminousIntensity != 0) result += "luminousIntensity = $luminousIntensity"
        if (mass != 0) result += "mass = $mass"
        if (temperature != 0) result += "temperature = $temperature"
        if (time != 0) result += "time = $time"

        return "@Composite(" + result.joinToString(",\n") + ")"
    }
}
