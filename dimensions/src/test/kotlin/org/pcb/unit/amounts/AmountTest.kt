package org.pcb.unit.amounts

import java.io.File
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

        val amounts = dimensions.associateWith { Class.forName("org.pcb.unit.amounts.${it.simpleName}Amount") }

        val returnAmounts = dimensions.associateWith {
            if (!it.isAssignableFrom(Scalar::class.java)) Class.forName("org.pcb.unit.amounts.${it.simpleName}Amount")
            else Class.forName("org.pcb.unit.amounts.base.Numeric")
        }

        val dimensionComposite = dimension.kotlin.annotations.first { it is Composite } as Composite
        val amount = returnAmounts[dimension]!!

        val regions = HashMap<String, String>()
        var code = regions[dimension.simpleName] ?: ""

        val major = dimensionComposite.positives()
        val minor = dimensionComposite.negatives()

        code += "\toverride operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =\n"
        code += "\t\t${amount.simpleName}(amount = amount * that.amount * convert(that.scalar to NUMERIC),"
        code += "\n\t\t\tunit = ${dimensionComposite.buildUnit(dimensionComposite)})\n\n"

        code += "\toverride operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =\n"
        code += "\t\t${amount.simpleName}(amount = amount / that.amount / convert(that.scalar to NUMERIC),"
        code += "\n\t\t\tunit = ${dimensionComposite.buildUnit(dimensionComposite)})\n\n"

        code += "\t@JvmName(\"plus${dimension.simpleName}\")\n"
        code += "\toperator fun ${dimensionComposite.buildGenericDeclaration()} plus(that: ${amount.simpleName}${dimensionComposite.buildGenericReceiver()}) =\n"
        code += "\t\t${amount.simpleName}(amount = (amount + that.amount)"
        if (major.count() > 0) code += "\n\t\t* convert(${major.buildConvert()})"
        if (minor.count() > 0) code += "\n\t\t/ convert(${minor.buildConvert()})"
        code += ",\n\t\t\tunit = ${dimensionComposite.buildUnit(dimensionComposite)})\n\n"

        code += "\toperator fun plus(that: ${amount.simpleName}${dimensionComposite.buildGenericReceiver()}) =\n"
        code += "\t\t${amount.simpleName}(amount = (amount + that.amount)"
        code += ",\n\t\t\tunit = ${dimensionComposite.buildUnit(dimensionComposite)})\n\n"

        code += "\t@JvmName(\"minus${dimension.simpleName}\")\n"
        code += "\toperator fun ${dimensionComposite.buildGenericDeclaration()} minus(that: ${amount.simpleName}${dimensionComposite.buildGenericReceiver()}) =\n"
        code += "\t\t${amount.simpleName}(amount = (amount - that.amount)"
        if (major.count() > 0) code += "\n\t\t* convert(${major.buildConvert()})"
        if (minor.count() > 0) code += "\n\t\t/ convert(${minor.buildConvert()})"
        code += ",\n\t\t\tunit = ${dimensionComposite.buildUnit(dimensionComposite)})\n\n"

        code += "\toperator fun  minus(that: ${amount.simpleName}${dimensionComposite.buildGenericReceiver()}) =\n"
        code += "\t\t${amount.simpleName}(amount = (amount - that.amount)"
        code += ",\n\t\t\tunit = ${dimensionComposite.buildUnit(dimensionComposite)})\n\n"

        regions[dimension.simpleName] = code

        dimensions
            .sortedBy { it.simpleName }
            .forEach { returnDimension ->
                val returnDimensionComposite = returnDimension.kotlin.annotations.first { it is Composite } as Composite
                val returnAmount = returnAmounts[returnDimension]!!

                dimensions
                    .filter { it != Scalar::class.java }
                    .sortedBy { it.simpleName }
                    .forEach { parameterDimension ->
                        val parameterDimensionComposite = parameterDimension.kotlin.annotations.first { it is Composite } as Composite

                        code = regions[returnDimension.simpleName] ?: ""

                        if (dimensionComposite - parameterDimensionComposite == returnDimensionComposite)
                        {
                            val keep = parameterDimensionComposite.keep(dimensionComposite)
                            if (keep.count() > 0)
                            {
                                code += "\t@JvmName(\"div${parameterDimension.simpleName}\")\n"
                                code += "\toperator fun ${parameterDimensionComposite.buildGenericDeclaration()} div(that: ${amount.simpleName}${parameterDimensionComposite.buildGenericReceiver()}) =\n"
                                code += "\t\t${returnAmount.simpleName}(amount = amount / that.amount"
                                if (keep.positives().count() > 0) code += "\n\t\t/ convert(${keep.positives().buildConvert()})"
                                if (keep.negatives().count() > 0) code += "\n\t\t* convert(${keep.negatives().buildConvert()})"
                                val unit = dimensionComposite.buildUnit(returnDimensionComposite)
                                code +=
                                    if (unit == null) ")\n\n"
                                    else ",\n\t\t\tunit = $unit)\n\n"
                            }

                            val drop = parameterDimensionComposite.drop(dimensionComposite)
                            code += "\toperator fun "
                            if (drop.count() > 0) code += drop.buildGenericDeclaration()
                            code += " div(that: ${amount.simpleName}${parameterDimensionComposite.buildGenericReceiver()}) =\n"
                            code += "\t\t${returnAmount.simpleName}(amount = amount / that.amount"
                            val unit = dimensionComposite.buildUnit(returnDimensionComposite)
                            code +=
                                if (unit == null) ")\n\n"
                                else ",\n\t\t\tunit = $unit)\n\n"
                        }
                        if (dimensionComposite + parameterDimensionComposite == returnDimensionComposite)
                        {
                            val keep = parameterDimensionComposite.keep(dimensionComposite)
                            if (keep.count() > 0)
                            {
                                code += "\t@JvmName(\"times${parameterDimension.simpleName}\")\n"
                                code += "\toperator fun ${parameterDimensionComposite.buildGenericDeclaration()} times(that: ${amount.simpleName}${parameterDimensionComposite.buildGenericReceiver()}) =\n"
                                code += "\t\t${returnAmount.simpleName}(amount = amount * that.amount"
                                if (keep.positives().count() > 0) code += "\n\t\t* convert(${keep.positives().buildConvert()})"
                                if (keep.negatives().count() > 0) code += "\n\t\t/ convert(${keep.negatives().buildConvert()})"
                                val unit = dimensionComposite.buildUnit(returnDimensionComposite)
                                code +=
                                    if (unit == null) ")\n\n"
                                    else ",\n\t\t\tunit = $unit)\n\n"
                            }

                            val drop = parameterDimensionComposite.drop(dimensionComposite)
                            code += "\toperator fun "
                            if (drop.count() > 0) code += drop.buildGenericDeclaration()
                            code += " times(that: ${amount.simpleName}${parameterDimensionComposite.buildGenericReceiver()}) =\n"
                            code += "\t\t${returnAmount.simpleName}(amount = amount * that.amount"
                            val unit = dimensionComposite.buildUnit(returnDimensionComposite)
                            code +=
                                if (unit == null) (")\n\n")
                                else ",\n\t\t\tunit = $unit)\n\n"
                        }

                        if (code != "") regions[returnDimension.simpleName] = code
                    }
            }

        val stub = this.javaClass.classLoader.getResource("org/pcb/unit/amounts/" + amounts[dimension]!!.simpleName + ".kt")

        println(stub)

        val contents = stub.readText()
            .replace(oldValue = "\$operators",
                     newValue = regions.keys
                         .asSequence()
                         .sorted()
                         .sortedBy { it != Scalar::class.simpleName }
                         .sortedBy { it != dimension.simpleName }
                         .map {
                             "\n\t// region -> ${it}\n\n" + regions[it] + "\t// endregion\n"
                         }
                         .joinToString())

        val source = File(stub.path.replace("target/test-classes/", "src/main/kotlin/"))

//        source.writeText(contents)
    }

    private fun Composite.buildGenericDeclaration(): String
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

    private fun Composite.buildGenericReceiver(): String
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

    private fun Composite.keep(that: Composite) =
        Composite(angle = if (that.angle != 0) angle else 0,
                  currency = if (that.currency != 0) currency else 0,
                  distance = if (that.distance != 0) distance else 0,
                  electricCurrent = if (that.electricCurrent != 0) electricCurrent else 0,
                  luminousIntensity = if (that.luminousIntensity != 0) luminousIntensity else 0,
                  mass = if (that.mass != 0) mass else 0,
                  temperature = if (that.temperature != 0) temperature else 0,
                  time = if (that.time != 0) time else 0)

    private fun Composite.drop(that: Composite) =
        Composite(angle = if (that.angle != 0) 0 else angle,
                  currency = if (that.currency != 0) 0 else currency,
                  distance = if (that.distance != 0) 0 else distance,
                  electricCurrent = if (that.electricCurrent != 0) 0 else electricCurrent,
                  luminousIntensity = if (that.luminousIntensity != 0) 0 else luminousIntensity,
                  mass = if (that.mass != 0) 0 else mass,
                  temperature = if (that.temperature != 0) 0 else temperature,
                  time = if (that.time != 0) 0 else time)

    private fun Composite.buildConvert(): String
    {
        val result = ArrayList<String>()

        for (a in 1..angle) result += "that.angle to angle"
        for (a in 1..currency) result += "that.currency to currency"
        for (a in 1..distance) result += "that.distance to distance"
        for (a in 1..electricCurrent) result += "that.electricCurrent to electricCurrent"
        for (a in 1..luminousIntensity) result += "that.luminousIntensity to luminousIntensity"
        for (a in 1..mass) result += "that.mass to mass"
        for (a in 1..temperature) result += "that.temperature to temperature"
        for (a in 1..time) result += "that.time to time"

        return result.joinToString(",\n\t\t\t\t")
    }

    private fun Composite.buildUnit(that: Composite): String?
    {
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

    private fun Composite.positives() =
        Composite(angle = if (angle > 0) angle else 0,
                  currency = if (currency > 0) currency else 0,
                  distance = if (distance > 0) distance else 0,
                  electricCurrent = if (electricCurrent > 0) electricCurrent else 0,
                  luminousIntensity = if (luminousIntensity > 0) luminousIntensity else 0,
                  mass = if (mass > 0) mass else 0,
                  temperature = if (temperature > 0) temperature else 0,
                  time = if (time > 0) time else 0)

    private fun Composite.negatives() =
        Composite(angle = if (angle < 0) -angle else 0,
                  currency = if (currency < 0) -currency else 0,
                  distance = if (distance < 0) -distance else 0,
                  electricCurrent = if (electricCurrent < 0) -electricCurrent else 0,
                  luminousIntensity = if (luminousIntensity < 0) -luminousIntensity else 0,
                  mass = if (mass < 0) -mass else 0,
                  temperature = if (temperature < 0) -temperature else 0,
                  time = if (time < 0) -time else 0)

    private fun Composite.count() =
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