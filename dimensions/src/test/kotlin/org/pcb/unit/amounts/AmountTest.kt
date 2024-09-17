package org.pcb.unit.amounts

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.pcb.unit.Composite
import org.pcb.unit.dimensions.Dimension
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.minus
import org.pcb.unit.plus
import org.reflections.Reflections

class AmountTest
{
    companion object
    {
        @JvmStatic
        fun getData() =
            Reflections("org.pcb.unit.dimensions")
                .getSubTypesOf(Dimension::class.java)
                .sortedBy { it.simpleName }
                .toList()
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getData")
    fun combinations(dimension: Class<out Dimension>)
    {
        val reflections = Reflections("org.pcb.unit.dimensions")

        val dimensions = reflections.getSubTypesOf(Dimension::class.java)

        val amounts = dimensions.associateWith {
            if (!it.isAssignableFrom(Scalar::class.java)) Class.forName("org.pcb.unit.amounts.${it.simpleName}Amount")
            else Class.forName("org.pcb.unit.amounts.base.Numeric")
        }

        val dimensionComposite = dimension.kotlin.annotations.first { it is Composite } as Composite

        dimensions
            .sortedBy { it.simpleName }
            .forEach { resultDimension ->
                val resultDimensionComposite = resultDimension.kotlin.annotations.first { it is Composite } as Composite
                val resultAmount = amounts[resultDimension]!!

                dimensions
                    .sortedBy { it.simpleName }
                    .forEach { operatorDimension ->
                        val operatorDimensionComposite = operatorDimension.kotlin.annotations.first { it is Composite } as Composite
                        val operatorAmount = amounts[operatorDimension]!!

                        if (dimensionComposite - operatorDimensionComposite == resultDimensionComposite ||
                            dimensionComposite + operatorDimensionComposite == resultDimensionComposite)
                        {
                            println("\n\t// region -> ${resultDimension.simpleName}\n")
                        }

                        if (dimensionComposite - operatorDimensionComposite == resultDimensionComposite)
                        {
                            val keep = operatorDimensionComposite.keep(dimensionComposite)
                            if (keep.count() > 0)
                            {
                                println("@JvmName(\"div${operatorDimension.simpleName}\")")
                                println("operator fun ${operatorDimensionComposite.buildGenericDeclaration()} div(that: ${operatorAmount.simpleName}${operatorDimensionComposite.buildGenericReceiver()}) =")
                                print("\t${resultAmount.simpleName}(amount = amount / that.amount")
                                if (keep.positives().count() > 0) print("\n\t\t/ convert(${keep.positives().buildConvert()})")
                                if (keep.negatives().count() > 0) print("\n\t\t* convert(${keep.negatives().buildConvert()})")
                                val unit = dimensionComposite.buildUnit(resultDimensionComposite)
                                if (unit == null) println(")")
                                else println(",\n\t\tunit = $unit)")
                                println()
                            }

                            val drop = operatorDimensionComposite.drop(dimensionComposite)
                            print("operator fun ")
                            if (drop.count() > 0) print(drop.buildGenericDeclaration())
                            println(" div(that: ${operatorAmount.simpleName}${operatorDimensionComposite.buildGenericReceiver()}) =")
                            print("\t${resultAmount.simpleName}(amount = amount / that.amount")
                            val unit = dimensionComposite.buildUnit(resultDimensionComposite)
                            if (unit == null) println(")")
                            else println(",\n\t\tunit = $unit)")
                            println()

//                                    assertTrue("${operatorDimension.simpleName} / ${resultDimension.simpleName}) = ${operatorDimension.simpleName}") {
//                                        operatorDimension.methods
//                                            .filter { method -> Dimension::class.java.isAssignableFrom(method.returnType) }
//                                            .any { method ->
//                                                method.name == "div" &&
//                                                method.parameters[0].type.isAssignableFrom(resultDimension) &&
//                                                method.returnType.isAssignableFrom(operatorDimension)
//                                            }
//                                    }
                        }
                        if (dimensionComposite + operatorDimensionComposite == resultDimensionComposite)
                        {
                            val keep = operatorDimensionComposite.keep(dimensionComposite)
                            if (keep.count() > 0)
                            {
                                println("@JvmName(\"times${operatorDimension.simpleName}\")")
                                println("operator fun ${operatorDimensionComposite.buildGenericDeclaration()} times(that: ${operatorAmount.simpleName}${operatorDimensionComposite.buildGenericReceiver()}) =")
                                print("\t${resultAmount.simpleName}(amount = amount * that.amount")
                                if (keep.positives().count() > 0) print("\n\t\t* convert(${keep.positives().buildConvert()})")
                                if (keep.negatives().count() > 0) print("\n\t\t/ convert(${keep.negatives().buildConvert()})")
                                val unit = dimensionComposite.buildUnit(resultDimensionComposite)
                                if (unit == null) println(")")
                                else println(",\n\t\tunit = $unit)")
                                println()
                            }

                            val drop = operatorDimensionComposite.drop(dimensionComposite)
                            print("operator fun ")
                            if (drop.count() > 0) print(drop.buildGenericDeclaration())
                            println(" times(that: ${operatorAmount.simpleName}${operatorDimensionComposite.buildGenericReceiver()}) =")
                            print("\t${resultAmount.simpleName}(amount = amount * that.amount")
                            val unit = dimensionComposite.buildUnit(resultDimensionComposite)
                            if (unit == null) println(")")
                            else println(",\n\t\tunit = $unit)")
                            println()

//                                    assertTrue("Missing ${operatorDimension.simpleName} * ${resultDimension.simpleName}) = ${operatorDimension.simpleName}") {
//                                        operatorDimension.methods
//                                            .filter { method -> Dimension::class.java.isAssignableFrom(method.returnType) }
//                                            .any { method ->
//                                                method.name == "times" &&
//                                                method.parameters[0].type.isAssignableFrom(resultDimension) &&
//                                                method.returnType.isAssignableFrom(operatorDimension)
//                                            }
//                                    }
                        }

                        if (dimensionComposite - operatorDimensionComposite == resultDimensionComposite ||
                            dimensionComposite + operatorDimensionComposite == resultDimensionComposite)
                        {
                            println("\t// endregion")
                        }
                    }
            }
    }

    fun Composite.buildGenericDeclaration(): String
    {
        val result = ArrayList<String>()

        if (angle != 0) result += "A : Unit<Angle>"
        if (currency != 0) result += "C : Unit<Currency>"
        if (distance != 0) result += "D : Unit<Distance>"
        if (electricCurrent != 0) result += "EC : Unit<ElectricCurrent>"
        if (luminousIntensity != 0) result += "LI : Unit<LuminousIntensity>"
        if (mass != 0) result += "M : Unit<Mass>"
        if (temperature != 0) result += "K : Unit<Temperature>"
        if (time != 0) result += "T : Unit<Time>"

        return if (result.isEmpty()) "" else "<" + result.joinToString(", ") + ">"
    }

    fun Composite.buildGenericReceiver(): String
    {
        val result = ArrayList<String>()

        if (angle != 0) result += "A"
        if (currency != 0) result += "C"
        if (distance != 0) result += "D"
        if (electricCurrent != 0) result += "EC"
        if (luminousIntensity != 0) result += "LI"
        if (mass != 0) result += "M"
        if (temperature != 0) result += "K"
        if (time != 0) result += "T"

        return if (result.isEmpty()) "" else "<" + result.joinToString(", ") + ">"
    }

    fun Composite.keep(that: Composite) =
        Composite(angle = if (that.angle != 0) angle else 0,
                  currency = if (that.currency != 0) currency else 0,
                  distance = if (that.distance != 0) distance else 0,
                  electricCurrent = if (that.electricCurrent != 0) electricCurrent else 0,
                  luminousIntensity = if (that.luminousIntensity != 0) luminousIntensity else 0,
                  mass = if (that.mass != 0) mass else 0,
                  temperature = if (that.temperature != 0) temperature else 0,
                  time = if (that.time != 0) time else 0)

    fun Composite.drop(that: Composite) =
        Composite(angle = if (that.angle != 0) 0 else angle,
                  currency = if (that.currency != 0) 0 else currency,
                  distance = if (that.distance != 0) 0 else distance,
                  electricCurrent = if (that.electricCurrent != 0) 0 else electricCurrent,
                  luminousIntensity = if (that.luminousIntensity != 0) 0 else luminousIntensity,
                  mass = if (that.mass != 0) 0 else mass,
                  temperature = if (that.temperature != 0) 0 else temperature,
                  time = if (that.time != 0) 0 else time)

    fun Composite.buildConvert(): String
    {
        var result = ArrayList<String>()

        for (a in 1..angle) result += "that.angle to angle"
        for (a in 1..currency) result += "that.currency to currency"
        for (a in 1..distance) result += "that.distance to distance"
        for (a in 1..electricCurrent) result += "that.electricCurrent to electricCurrent"
        for (a in 1..luminousIntensity) result += "that.luminousIntensity to luminousIntensity"
        for (a in 1..mass) result += "that.mass to mass"
        for (a in 1..temperature) result += "that.temperature to temperature"
        for (a in 1..time) result += "that.time to time"

        return result.joinToString(",\n\t\t\t\t");
    }

    fun Composite.buildUnit(that: Composite): String?
    {
        val positives = positives()
        val negatives = negatives()
        val thatPositives = that.positives()
        val thatNegatives = that.negatives()

        val major = when (thatPositives.count())
        {
            0    -> null
            1 -> units(thatPositives).sortedByDescending { it.second }.joinToString(", ") { it.first }
            2 -> "U2(${units(thatPositives).sortedByDescending { it.second }.joinToString(", ") { it.first }})"
            3 -> "U3(${units(thatPositives).sortedByDescending { it.second }.joinToString(", ") { it.first }})"
            4 -> "U4(${units(thatPositives).sortedByDescending { it.second }.joinToString(", ") { it.first }})"
            5 -> "U5(${units(thatPositives).sortedByDescending { it.second }.joinToString(", ") { it.first }})"
            6 -> "U6(${units(thatPositives).sortedByDescending { it.second }.joinToString(", ") { it.first }})"
            else -> throw IllegalArgumentException()
        }

        val minor = when (thatNegatives.count())
        {
            0    -> null
            1 -> units(thatNegatives).sortedBy { it.second }.joinToString(", ") { it.first }
            2 -> "U2(${units(thatNegatives).sortedBy { it.second }.joinToString(", ") { it.first }})"
            3 -> "U3(${units(thatNegatives).sortedBy { it.second }.joinToString(", ") { it.first }})"
            4 -> "U4(${units(thatNegatives).sortedBy { it.second }.joinToString(", ") { it.first }})"
            5 -> "U5(${units(thatNegatives).sortedBy { it.second }.joinToString(", ") { it.first }})"
            6 -> "U6(${units(thatNegatives).sortedBy { it.second }.joinToString(", ") { it.first }})"
            else -> throw IllegalArgumentException()
        }

        return when
        {
            major == null && minor == null -> null
            major != null && minor == null -> major
            major == null && minor != null -> "NUMERIC to " + minor
            else                           -> "$major to $minor"
        }
    }

    fun Composite.positives() =
        Composite(angle = if (angle > 0) angle else 0,
                  currency = if (currency > 0) currency else 0,
                  distance = if (distance > 0) distance else 0,
                  electricCurrent = if (electricCurrent > 0) electricCurrent else 0,
                  luminousIntensity = if (luminousIntensity > 0) luminousIntensity else 0,
                  mass = if (mass > 0) mass else 0,
                  temperature = if (temperature > 0) temperature else 0,
                  time = if (time > 0) time else 0)

    fun Composite.negatives() =
        Composite(angle = if (angle < 0) -angle else 0,
                  currency = if (currency < 0) -currency else 0,
                  distance = if (distance < 0) -distance else 0,
                  electricCurrent = if (electricCurrent < 0) -electricCurrent else 0,
                  luminousIntensity = if (luminousIntensity < 0) -luminousIntensity else 0,
                  mass = if (mass < 0) -mass else 0,
                  temperature = if (temperature < 0) -temperature else 0,
                  time = if (time < 0) -time else 0)

    fun Composite.count() =
        (if (angle != 0) 1 else 0) +
        (if (currency != 0) 1 else 0) +
        (if (distance != 0) 1 else 0) +
        (if (electricCurrent != 0) 1 else 0) +
        (if (luminousIntensity != 0) 1 else 0) +
        (if (mass != 0) 1 else 0) +
        (if (temperature != 0) 1 else 0) +
        (if (time != 0) 1 else 0)

    fun Composite.units(that: Composite): ArrayList<Pair<String, Int>>
    {
        val result = ArrayList<Pair<String, Int>>()

        if (angle != 0 && that.angle != 0) result += "angle" to that.angle else if (that.angle != 0) result += "that.angle" to that.angle
        if (currency != 0 && that.currency != 0) result += "currency" to that.currency else if (that.currency != 0) result += "that.currency" to that.currency
        if (distance != 0 && that.distance != 0) result += "distance" to that.distance else if (that.distance != 0) result += "that.distance" to that.distance
        if (electricCurrent != 0 && that.electricCurrent != 0) result += "electricCurrent" to that.electricCurrent else if (that.electricCurrent != 0) result += "that.electricCurrent" to that.electricCurrent
        if (luminousIntensity != 0 && that.luminousIntensity != 0) result += "luminousIntensity" to that.luminousIntensity else if (that.luminousIntensity != 0) result += "that.luminousIntensity" to that.luminousIntensity
        if (mass != 0 && that.mass != 0) result += "mass" to that.mass else if (that.mass != 0) result += "that.mass" to that.mass
        if (temperature != 0 && that.temperature != 0) result += "temperature" to that.temperature else if (that.temperature != 0) result += "that.temperature" to that.temperature
        if (time != 0 && that.time != 0) result += "time" to that.time else if (that.time != 0) result += "that.time" to that.time

        return result
    }
}