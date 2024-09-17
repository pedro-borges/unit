package org.pcb.unit.amounts

import kotlin.test.Test
import org.pcb.unit.Composite
import org.pcb.unit.dimensions.Dimension
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.minus
import org.pcb.unit.plus
import org.reflections.Reflections

class AmountTest
{

    @Test
    fun combinations()
    {
        val reflections = Reflections("org.pcb.unit.dimensions")

        val dimensions = reflections.getSubTypesOf(Dimension::class.java)

        val amounts = dimensions.associateWith {
            if (!it.isAssignableFrom(Scalar::class.java)) Class.forName("org.pcb.unit.amounts.${it.simpleName}Amount")
            else Class.forName("org.pcb.unit.amounts.base.Numeric")
        }

        dimensions
            .sortedBy { it.simpleName }
            .forEach { dimension ->
                val dimension1Composite = dimension.kotlin.annotations.first { it is Composite } as Composite
                val amount = amounts[dimension]!!

                println()
                println(amount.simpleName + ":")
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

                                if (dimension1Composite - operatorDimensionComposite == resultDimensionComposite ||
                                    dimension1Composite + operatorDimensionComposite == resultDimensionComposite)
                                {
                                    println("\n\t// region -> ${resultDimension.simpleName}\n")
                                }


                                if (dimension1Composite - operatorDimensionComposite == resultDimensionComposite)
                                {
                                    val keep = operatorDimensionComposite.keep(dimension1Composite)
                                    if (keep.count() > 0)
                                    {
                                        println("\t@JvmName(\"div${operatorDimension.simpleName}\")")
                                        println("\toperator fun ${operatorDimensionComposite.buildGenericDeclaration()} div(that: ${operatorAmount.simpleName}${operatorDimensionComposite.buildGenericReceiver()}) =")
                                        print("\t\t${resultAmount.simpleName}(amount = amount / that.amount")
                                        if (keep.positives().count() > 0) print("\n\t\t\t/ convert(${keep.positives().buildConvert()})")
                                        if (keep.negatives().count() > 0) print("\n\t\t\t* convert(${keep.negatives().buildConvert()})")
                                        val unit = resultDimensionComposite.buildUnit(dimension1Composite, operatorDimensionComposite)
                                        if (unit == null) println(")")
                                        else println(",\n\t\t\tunit = $unit)")
                                        println()
                                    }

                                    val drop = operatorDimensionComposite.drop(dimension1Composite)
                                    print("\toperator fun ")
                                    if (drop.count() > 0) print(drop.buildGenericDeclaration())
                                    println(" div(that: ${operatorAmount.simpleName}${operatorDimensionComposite.buildGenericReceiver()}) =")
                                    print("\t\t${resultAmount.simpleName}(amount = amount / that.amount")
                                    val unit = resultDimensionComposite.buildUnit(dimension1Composite, operatorDimensionComposite)
                                    if (unit == null) println(")")
                                    else println(",\n\t\t\tunit = $unit)")
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
                                if (dimension1Composite + operatorDimensionComposite == resultDimensionComposite)
                                {
                                    val keep = operatorDimensionComposite.keep(dimension1Composite)
                                    if (keep.count() > 0)
                                    {
                                        println("\t@JvmName(\"times${operatorDimension.simpleName}\")")
                                        println("\toperator fun ${operatorDimensionComposite.buildGenericDeclaration()} times(that: ${operatorAmount.simpleName}${operatorDimensionComposite.buildGenericReceiver()}) =")
                                        print("\t\t${resultAmount.simpleName}(amount = amount * that.amount")
                                        if (keep.positives().count() > 0) print("\n\t\t\t* convert(${keep.positives().buildConvert()})")
                                        if (keep.negatives().count() > 0) print("\n\t\t\t/ convert(${keep.negatives().buildConvert()})")
                                        val unit = resultDimensionComposite.buildUnit(dimension1Composite, operatorDimensionComposite)
                                        if (unit == null) println(")")
                                        else println(",\n\t\t\tunit = $unit)")
                                        println()
                                    }

                                    val drop = operatorDimensionComposite.drop(dimension1Composite)
                                    print("\toperator fun ")
                                    if (drop.count() > 0) print(drop.buildGenericDeclaration())
                                    println(" times(that: ${operatorAmount.simpleName}${operatorDimensionComposite.buildGenericReceiver()}) =")
                                    print("\t\t${resultAmount.simpleName}(amount = amount * that.amount")
                                    val unit = resultDimensionComposite.buildUnit(dimension1Composite, operatorDimensionComposite)
                                    if (unit == null) println(")")
                                    else println(",\n\t\t\tunit = $unit)")
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

                                if (dimension1Composite - operatorDimensionComposite == resultDimensionComposite ||
                                    dimension1Composite + operatorDimensionComposite == resultDimensionComposite)
                                {
                                    println("\t// endregion")
                                }
                            }
                    }
            }
    }

    fun Composite.buildGenericDeclaration(): String
    {
        val result = ArrayList<Pair<String, Int>>()

        if (angle != 0) result += "A : Unit<Angle>" to angle
        if (distance != 0) result += "D : Unit<Distance>" to distance
        if (electricCurrent != 0) result += "EC : Unit<ElectricCurrent>" to electricCurrent
        if (luminousIntensity != 0) result += "LI : Unit<LuminousIntensity>" to luminousIntensity
        if (mass != 0) result += "M : Unit<Mass>" to mass
        if (temperature != 0) result += "K : Unit<Temperature>" to temperature
        if (time != 0) result += "T : Unit<Time>" to time

        return if (result.isEmpty()) "" else "<" + result.sortedByDescending { it.second }.map { it.first }.joinToString(", ") + ">"
    }

    fun Composite.buildGenericReceiver(): String
    {
        val result = ArrayList<Pair<String, Int>>()

        if (angle != 0) result += "A" to angle
        if (distance != 0) result += "D" to distance
        if (electricCurrent != 0) result += "EC" to electricCurrent
        if (luminousIntensity != 0) result += "LI" to luminousIntensity
        if (mass != 0) result += "M" to mass
        if (temperature != 0) result += "K" to temperature
        if (time != 0) result += "T" to time

        return if (result.isEmpty()) "" else "<" + result.sortedByDescending { it.second }.map { it.first }.joinToString(", ") + ">"
    }

    fun Composite.keep(that: Composite) =
        Composite(angle = if (angle != 0 && that.angle != 0) angle else 0,
                  currency = if (currency != 0 && that.currency != 0) currency else 0,
                  distance = if (distance != 0 && that.distance != 0) distance else 0,
                  electricCurrent = if (electricCurrent != 0 && that.electricCurrent != 0) electricCurrent else 0,
                  luminousIntensity = if (luminousIntensity != 0 && that.luminousIntensity != 0) luminousIntensity else 0,
                  mass = if (mass != 0 && that.mass != 0) mass else 0,
                  temperature = if (temperature != 0 && that.temperature != 0) temperature else 0,
                  time = if (time != 0 && that.time != 0) time else 0)

    fun Composite.drop(that: Composite) =
        Composite(angle = if (angle != 0 && that.angle != 0) 0 else angle,
                  currency = if (currency != 0 && that.currency != 0) 0 else currency,
                  distance = if (distance != 0 && that.distance != 0) 0 else distance,
                  electricCurrent = if (electricCurrent != 0 && that.electricCurrent != 0) 0 else electricCurrent,
                  luminousIntensity = if (luminousIntensity != 0 && that.luminousIntensity != 0) 0 else luminousIntensity,
                  mass = if (mass != 0 && that.mass != 0) 0 else mass,
                  temperature = if (temperature != 0 && that.temperature != 0) 0 else temperature,
                  time = if (time != 0 && that.time != 0) 0 else time)

    fun Composite.buildConvert(): String
    {
        var result = ArrayList<String>()

        for (a in 1..currency) result += "that.currency to currency"
        for (a in 1..distance) result += "that.distance to distance"
        for (a in 1..electricCurrent) result += "that.electricCurrent to electricCurrent"
        for (a in 1..luminousIntensity) result += "that.luminousIntensity to luminousIntensity"
        for (a in 1..mass) result += "that.mass to mass"
        for (a in 1..temperature) result += "that.temperature to temperature"
        for (a in 1..angle) result += "that.angle to angleangle"
        for (a in 1..time) result += "that.time to time"

        return result.joinToString(",\n\t\t\t\t");
    }

    fun Composite.buildUnit(main: Composite, operator: Composite): String?
    {
        val positives = positives()
        val negatives = negatives()
        val mainPositives = main.positives()
        val operatorPositives = operator.positives()
        val mainNegatives = main.negatives()
        val operatorNegatives = operator.negatives()

        val major = when (positives.count())
        {
            0    -> null
            1    -> positives.units(mainPositives, operatorPositives)
            2    -> "U2(${positives.units(mainPositives, operatorPositives)})"
            3    -> "U3(${positives.units(mainPositives, operatorPositives)})"
            4    -> "U4(${positives.units(mainPositives, operatorPositives)})"
            5    -> "U5(${positives.units(mainPositives, operatorPositives)})"
            6    -> "U6(${positives.units(mainPositives, operatorPositives)})"
            else -> throw IllegalArgumentException()
        }

        val minor = when (negatives.count())
        {
            0    -> null
            1    -> negatives.units(mainNegatives, operatorNegatives)
            2    -> "U2(${negatives.units(mainNegatives, operatorNegatives)})"
            3    -> "U3(${negatives.units(mainNegatives, operatorNegatives)})"
            4    -> "U4(${negatives.units(mainNegatives, operatorNegatives)})"
            5    -> "U5(${negatives.units(mainNegatives, operatorNegatives)})"
            6    -> "U6(${negatives.units(mainNegatives, operatorNegatives)})"
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
        (if (angle > 0) 1 else 0) +
        (if (currency > 0) 1 else 0) +
        (if (distance > 0) 1 else 0) +
        (if (electricCurrent > 0) 1 else 0) +
        (if (luminousIntensity > 0) 1 else 0) +
        (if (mass > 0) 1 else 0) +
        (if (temperature > 0) 1 else 0) +
        (if (time > 0) 1 else 0)

    fun Composite.units(main: Composite, operator: Composite): String
    {
        val result = ArrayList<String>()

        if (angle > 0)
            if (main.angle > 0) result += "angle"
            else result += "that.angle"
        if (currency > 0)
            if (main.currency > 0) result += "currency"
            else result += "that.currency"
        if (distance > 0)
            if (main.distance > 0) result += "distance"
            else result += "that.distance"
        if (electricCurrent > 0)
            if (main.electricCurrent > 0) result += "electricCurrent"
            else result += "that.electricCurrent"
        if (luminousIntensity > 0)
            if (main.luminousIntensity > 0) result += "luminousIntensity"
            else result += "that.luminousIntensity"
        if (mass > 0)
            if (main.mass > 0) result += "mass"
            else result += "that.mass"
        if (temperature > 0)
            if (main.temperature > 0) result += "temperature"
            else result += "that.temperature"
        if (time > 0)
            if (main.time > 0) result += "time"
            else result += "that.time"


        return result.joinToString(", ")
    }
}