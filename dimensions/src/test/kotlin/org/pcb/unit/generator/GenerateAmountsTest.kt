package org.pcb.unit.generator

import java.io.File
import kotlin.math.absoluteValue
import kotlin.test.Ignore
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.pcb.unit.Composite
import org.pcb.unit.generator.Dimensions.DIMENSIONS
import org.pcb.unit.generator.Dimensions.Definition
import org.pcb.unit.generator.Dimensions.max
import org.pcb.unit.generator.Dimensions.min
import org.pcb.unit.minus
import org.pcb.unit.plus

@Ignore
class GenerateAmountsTest
{
    companion object
    {
        @JvmStatic
        fun getData() = DIMENSIONS
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getData")
    fun generate(dimension: Definition)
    {
        if (dimension.name == "Scalar") return

        val regions = HashMap<String, String>()
        var code = regions[dimension.name] ?: ""

        val major = dimension.composite.positives()
        val minor = dimension.composite.negatives()

        code += "\toverride operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount * that.amount * convert(that.scalar to NUMERIC),"
        code += "\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        code += "\toverride operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount / that.amount / convert(that.scalar to NUMERIC),"
        code += "\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        code += "\t@JvmName(\"plus${dimension.name}\")\n"
        code += "\toperator fun ${dimension.composite.buildGenericDeclaration()} plus(that: ${dimension.name}Amount${dimension.composite.buildGenericReceiver()}) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount + that.amount"
        if (major.count() > 0) code += "\n\t\t* convert(${major.buildConvert()})"
        if (minor.count() > 0) code += "\n\t\t/ convert(${minor.buildConvert()})"
        code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        code += "\toperator fun plus(that: ${dimension.name}Amount${dimension.composite.buildGenericReceiver()}) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount + that.amount"
        code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        code += "\t@JvmName(\"minus${dimension.name}\")\n"
        code += "\toperator fun ${dimension.composite.buildGenericDeclaration()} minus(that: ${dimension.name}Amount${dimension.composite.buildGenericReceiver()}) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount - that.amount"
        if (major.count() > 0) code += "\n\t\t* convert(${major.buildConvert()})"
        if (minor.count() > 0) code += "\n\t\t/ convert(${minor.buildConvert()})"
        code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        code += "\toperator fun  minus(that: ${dimension.name}Amount${dimension.composite.buildGenericReceiver()}) =\n"
        code += "\t\t${dimension.name}Amount(amount = amount - that.amount"
        code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(dimension.composite)})\n\n"

        regions[dimension.name] = code

        DIMENSIONS
            .sortedBy { it.name }
            .forEach { resultDimension ->
                DIMENSIONS
                    .filter { it.name != "Scalar" }
                    .sortedBy { it.name }
                    .forEach { parameterDimension ->
                        code = regions[resultDimension.name] ?: ""

                        if (dimension.composite - parameterDimension.composite == resultDimension.composite)
                        {
                            val keep = parameterDimension.composite.keep(dimension.composite)
                            if (keep.count() > 0)
                            {
                                code += "\t@JvmName(\"div${parameterDimension.name}\")\n"
                                code += "\toperator fun ${parameterDimension.composite.buildGenericDeclaration()} div(that: ${parameterDimension.name}Amount${parameterDimension.composite.buildGenericReceiver()}) =\n"
                                code += "\t\t${resultDimension.name}Amount(amount = amount / that.amount"
                                if (keep.positives().count() > 0) code += "\n\t\t/ convert(${keep.positives().buildConvert()})"
                                if (keep.negatives().count() > 0) code += "\n\t\t* convert(${keep.negatives().buildConvert()})"
                                code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(resultDimension.composite)})\n\n"
                            }

                            val drop = parameterDimension.composite.drop(dimension.composite)
                            code += "\toperator fun "
                            if (drop.count() > 0) code += drop.buildGenericDeclaration()
                            code += " div(that: ${parameterDimension.name}Amount${parameterDimension.composite.buildGenericReceiver()}) =\n"
                            code += "\t\t${resultDimension.name}Amount(amount = amount / that.amount"
                            code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(resultDimension.composite)})\n\n"
                        }
                        if (dimension.composite + parameterDimension.composite == resultDimension.composite)
                        {
                            val keep = parameterDimension.composite.keep(dimension.composite)
                            if (keep.count() > 0)
                            {
                                code += "\t@JvmName(\"times${parameterDimension.name}\")\n"
                                code += "\toperator fun ${parameterDimension.composite.buildGenericDeclaration()} times(that: ${parameterDimension.name}Amount${parameterDimension.composite.buildGenericReceiver()}) =\n"
                                code += "\t\t${resultDimension.name}Amount(amount = amount * that.amount"
                                if (keep.positives().count() > 0) code += "\n\t\t* convert(${keep.positives().buildConvert()})"
                                if (keep.negatives().count() > 0) code += "\n\t\t/ convert(${keep.negatives().buildConvert()})"
                                code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(resultDimension.composite)})\n\n"
                            }

                            val drop = parameterDimension.composite.drop(dimension.composite)
                            code += "\toperator fun "
                            if (drop.count() > 0) code += drop.buildGenericDeclaration()
                            code += " times(that: ${parameterDimension.name}Amount${parameterDimension.composite.buildGenericReceiver()}) =\n"
                            code += "\t\t${resultDimension.name}Amount(amount = amount * that.amount"
                            code += ",\n\t\t\tunit = ${dimension.composite.buildUnit(resultDimension.composite)})\n\n"
                        }

                        if (code != "") regions[resultDimension.name] = code
                    }
            }

        val operatorCode = regions.keys
            .asSequence()
            .sorted()
            .sortedBy { it != "Scalar" }
            .sortedBy { it != dimension.name }
            .map {
                "\t// region -> ${it}\n\n" + regions[it] + "\t// endregion"
            }
            .joinToString(separator = "\n\n")

        val contents =
            "package org.pcb.unit.amounts\n" +
            "\n" +
            "import org.pcb.unit.Amount\n" +
            "import org.pcb.unit.CompositeUnit\n" +
            "import org.pcb.unit.U2\n" +
            "import org.pcb.unit.U3\n" +
            "import org.pcb.unit.Unit\n" +
            "import org.pcb.unit.convert\n" +
            DIMENSIONS.joinToString("") { "import org.pcb.unit.dimensions.${it.name}\n" } +
            "import org.pcb.unit.amounts.base.Radian.RADIAN\n" +
            "import org.pcb.unit.amounts.base.Mole.MOLE\n" +
            "import org.pcb.unit.amounts.base.Ampere.AMPERE\n" +
            "import org.pcb.unit.amounts.base.Candela.CANDELA\n" +
            "import org.pcb.unit.amounts.base.Gram.GRAM\n" +
            "import org.pcb.unit.amounts.base.Meter.METER\n" +
            "import org.pcb.unit.amounts.base.Numeric.NUMERIC\n" +
            "import org.pcb.unit.amounts.base.Second.SECOND\n" +
            "import org.pcb.unit.amounts.base.Kelvin.KELVIN\n" +
            "import org.pcb.unit.div\n" +
            "import org.pcb.unit.kilo\n" +
            "import org.pcb.unit.major\n" +
            "import org.pcb.unit.minor\n" +
            "import org.pcb.unit.times\n" +
            "import org.pcb.unit.toBigDecimal\n" +
            "\n" +
            "open class ${dimension.name}Amount${dimension.composite.buildGenericDeclaration()}(\n" +
            "\t\tamount: Number,\n" +
            "\t\tunit: ${dimension.composite.buildType()})\n" +
            "\t: Amount<${dimension.name}>(amount = amount.toBigDecimal(),\n" +
            "                           unit = CompositeUnit(\n" +
            "                               domain = ${dimension.name}::class,\n" +
            "                               units = mapOf(${dimension.composite.buildMap()})))\n" +
            "{\n" +
            dimension.composite.buildVariables() +
            dimension.composite.buildConstructor(dimension.name) +
            "\n" +
            "$operatorCode\n" +
            "}\n"

        val stub = this.javaClass.classLoader.getResource("package")!!

        val source = File(stub.path.replace("target/test-classes/package", "src/main/kotlin/org/pcb/unit/amounts/") + "${dimension.name}Amount.kt")

        source.writeText(contents.replace("\t", "    "))
    }

    private fun Composite.buildGenericDeclaration(): String
    {
        val result = ArrayList<String>()

        if (substance != 0) result += "N : Unit<Substance>"
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

        if (substance != 0) result += "N"
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
        Composite(substance = if (that.substance != 0) substance else 0,
                  angle = if (that.angle != 0) angle else 0,
                  currency = if (that.currency != 0) currency else 0,
                  distance = if (that.distance != 0) distance else 0,
                  electricCurrent = if (that.electricCurrent != 0) electricCurrent else 0,
                  luminousIntensity = if (that.luminousIntensity != 0) luminousIntensity else 0,
                  mass = if (that.mass != 0) mass else 0,
                  temperature = if (that.temperature != 0) temperature else 0,
                  time = if (that.time != 0) time else 0)

    private fun Composite.drop(that: Composite) =
        Composite(substance = if (that.substance != 0) 0 else substance,
                  angle = if (that.angle != 0) 0 else angle,
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

        for (a in 1..substance) result += "that.substance to substance"
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

    private fun Composite.buildUnit(that: Composite): String
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
            major == null && minor == null -> "NUMERIC"
            major != null && minor == null -> major
            major == null && minor != null -> "NUMERIC to $minor"
            else                           -> "$major to $minor"
        }
    }

    private fun Composite.buildCanonical(): String
    {
        val positives = positives()
        val negatives = negatives()

        val canons = mapOf("N" to "MOLE",
                           "A" to "RADIAN",
                           "C" to "EUR",
                           "D" to "METER",
                           "EC" to "AMPERE",
                           "LI" to "CANDELA",
                           "M" to "kilo(GRAM)",
                           "T" to "SECOND",
                           "K" to "KELVIN")

        val major = when (positives.count())
        {
            0    -> null
            1    -> positives.unitGenerics().sortedByDescending { it.second }.joinToString(", ") { canons[it.first]!! }
            2    -> "U2(${positives.unitGenerics().sortedByDescending { it.second }.joinToString(", ") { canons[it.first]!! }})"
            3    -> "U3(${positives.unitGenerics().sortedByDescending { it.second }.joinToString(", ") { canons[it.first]!! }})"
            4    -> "U4(${positives.unitGenerics().sortedByDescending { it.second }.joinToString(", ") { canons[it.first]!! }})"
            5    -> "U5(${positives.unitGenerics().sortedByDescending { it.second }.joinToString(", ") { canons[it.first]!! }})"
            6    -> "U6(${positives.unitGenerics().sortedByDescending { it.second }.joinToString(", ") { canons[it.first]!! }})"
            else -> throw IllegalArgumentException()
        }

        val minor = when (negatives.count())
        {
            0    -> null
            1    -> negatives.unitGenerics().sortedBy { it.second }.joinToString(", ") { canons[it.first]!! }
            2    -> "U2(${negatives.unitGenerics().sortedBy { it.second }.joinToString(", ") { canons[it.first]!! }})"
            3    -> "U3(${negatives.unitGenerics().sortedBy { it.second }.joinToString(", ") { canons[it.first]!! }})"
            4    -> "U4(${negatives.unitGenerics().sortedBy { it.second }.joinToString(", ") { canons[it.first]!! }})"
            5    -> "U5(${negatives.unitGenerics().sortedBy { it.second }.joinToString(", ") { canons[it.first]!! }})"
            6    -> "U6(${negatives.unitGenerics().sortedBy { it.second }.joinToString(", ") { canons[it.first]!! }})"
            else -> throw IllegalArgumentException()
        }

        return when
        {
            major == null && minor == null -> "NUMERIC"
            major != null && minor == null -> major
            major == null && minor != null -> "NUMERIC to $minor"
            else                           -> "$major to $minor"
        }
    }

    private fun Composite.buildType(): String
    {
        val positives = positives()
        val negatives = negatives()

        val major = when (positives.count())
        {
            0    -> null
            1    -> positives.unitGenerics().sortedByDescending { it.second }.joinToString(", ") { it.first }
            2    -> "U2<${positives.unitGenerics().sortedByDescending { it.second }.joinToString(", ") { it.first }}>"
            3    -> "U3<${positives.unitGenerics().sortedByDescending { it.second }.joinToString(", ") { it.first }}>"
            4    -> "U4<${positives.unitGenerics().sortedByDescending { it.second }.joinToString(", ") { it.first }}>"
            5    -> "U5<${positives.unitGenerics().sortedByDescending { it.second }.joinToString(", ") { it.first }}>"
            6    -> "U6<${positives.unitGenerics().sortedByDescending { it.second }.joinToString(", ") { it.first }}>"
            else -> throw IllegalArgumentException()
        }

        val minor = when (negatives.count())
        {
            0    -> null
            1    -> negatives.unitGenerics().sortedBy { it.second }.joinToString(", ") { it.first }
            2    -> "U2<${negatives.unitGenerics().sortedBy { it.second }.joinToString(", ") { it.first }}>"
            3    -> "U3<${negatives.unitGenerics().sortedBy { it.second }.joinToString(", ") { it.first }}>"
            4    -> "U4<${negatives.unitGenerics().sortedBy { it.second }.joinToString(", ") { it.first }}>"
            5    -> "U5<${negatives.unitGenerics().sortedBy { it.second }.joinToString(", ") { it.first }}>"
            6    -> "U6<${negatives.unitGenerics().sortedBy { it.second }.joinToString(", ") { it.first }}>"
            else -> throw IllegalArgumentException()
        }

        return when
        {
            major == null && minor == null -> "Unit<Scalar>"
            major != null && minor == null -> major
            major == null && minor != null -> "Pair<Unit<Scalar>, $minor>"
            else                           -> "Pair<$major, $minor>"
        }
    }

    private fun Composite.buildMap(): String
    {
        val positives = positives().map()
        val negatives = negatives().map()

        val positiveKeys = positives.keys.sortedByDescending { positives[it] }
        val negativeKeys = negatives.keys.sortedBy { negatives[it] }

        val minor = when (negatives.count())
        {
            0    -> null
            1    -> negativeKeys.map { negatives[it] }.mapIndexed { index, power -> "unit.minor${index.pair()} to ${-power!!}" }.joinToString(",\n")
            else -> negativeKeys.map { negatives[it] }.mapIndexed { index, power -> "unit.minor${(index + 1).pair()} to ${-power!!}" }.joinToString(",\n")
        }

        val unit =
            if (minor == null) "unit"
            else "unit.major"

        val major = when (positives.count())
        {
            0    -> null
            1    -> positiveKeys.map { positives[it] }.mapIndexed { index, power -> "$unit${index.pair()} to $power" }.joinToString(",\n")
            else -> positiveKeys.map { positives[it] }.mapIndexed { index, power -> "$unit${(index + 1).pair()} to $power" }.joinToString(",\n")
        }

        return sequenceOf(major, minor).filterNotNull().joinToString(",\n")
    }

    private fun Composite.buildConstructor(domainName: String): String
    {
        return if (domainName !in setOf("Debt", "Rent") && isComposite())
        {
            "\n" +
            "\tcompanion object\n" +
            "\t{\n" +
            "\t\toperator fun invoke(amount: Number, unit: Unit<$domainName>) =\n" +
            "\t\t\t\t${domainName}Amount(amount = amount * unit.scale,\n" +
            "\t\t\t\t\t\tunit = ${buildCanonical()})\n" +
            "\t}\n"
        } else
        {
            ""
        }
    }

    private fun Composite.buildVariables(): String
    {
        val positives = positives().map()
        val negatives = negatives().map()

        val positiveKeys = positives.keys.sortedByDescending { positives[it] }
        val negativeKeys = negatives.keys.sortedBy { negatives[it] }

        val minor = when (negatives.count())
        {
            0    -> null
            1    -> negativeKeys.mapIndexed { index, unit -> "\tval $unit = unit.minor${index.pair()}\n" }.joinToString("")
            else -> negativeKeys.mapIndexed { index, unit -> "\tval $unit = unit.minor${(index + 1).pair()}\n" }.joinToString("")
        }

        val unitType =
            if (minor == null) "unit"
            else "unit.major"

        val major = when (positives.count())
        {
            0    -> null
            1    -> positiveKeys.mapIndexed { index, unit -> "\tval $unit = $unitType${index.pair()}\n" }.joinToString("")
            else -> positiveKeys.mapIndexed { index, unit -> "\tval $unit = $unitType${(index + 1).pair()}\n" }.joinToString("")
        }

        return (major ?: "") + (minor ?: "")
    }

    private fun Int.pair() =
        when (this)
        {
            0    -> ""
            1    -> ".first"
            2    -> ".second"
            3    -> ".third"
            4    -> ".fourth"
            5    -> ".fifth"
            6    -> ".sixth"
            else -> throw IllegalArgumentException()
        }

    private fun Composite.map(): Map<String, Int>
    {
        val result = HashMap<String, Int>()

        for (i in max() downTo min())
        {
            if (i == 0) continue

            if (angle == i) result["angle"] = angle
            if (currency == i) result["currency"] = currency
            if (distance == i) result["distance"] = distance
            if (electricCurrent == i) result["electricCurrent"] = electricCurrent
            if (luminousIntensity == i) result["luminousIntensity"] = luminousIntensity
            if (mass == i) result["mass"] = mass
            if (substance == i) result["substance"] = substance
            if (temperature == i) result["temperature"] = temperature
            if (time == i) result["time"] = time
        }

        return result
    }

    private fun Composite.positives() =
        Composite(angle = if (angle > 0) angle else 0,
                  currency = if (currency > 0) currency else 0,
                  distance = if (distance > 0) distance else 0,
                  electricCurrent = if (electricCurrent > 0) electricCurrent else 0,
                  luminousIntensity = if (luminousIntensity > 0) luminousIntensity else 0,
                  mass = if (mass > 0) mass else 0,
                  substance = if (substance > 0) substance else 0,
                  temperature = if (temperature > 0) temperature else 0,
                  time = if (time > 0) time else 0)

    private fun Composite.negatives() =
        Composite(angle = if (angle < 0) -angle else 0,
                  currency = if (currency < 0) -currency else 0,
                  distance = if (distance < 0) -distance else 0,
                  electricCurrent = if (electricCurrent < 0) -electricCurrent else 0,
                  luminousIntensity = if (luminousIntensity < 0) -luminousIntensity else 0,
                  mass = if (mass < 0) -mass else 0,
                  substance = if (substance < 0) -substance else 0,
                  temperature = if (temperature < 0) -temperature else 0,
                  time = if (time < 0) -time else 0)

    private fun Composite.count() =
        (if (angle != 0) 1 else 0) +
        (if (currency != 0) 1 else 0) +
        (if (distance != 0) 1 else 0) +
        (if (electricCurrent != 0) 1 else 0) +
        (if (luminousIntensity != 0) 1 else 0) +
        (if (mass != 0) 1 else 0) +
        (if (substance != 0) 1 else 0) +
        (if (temperature != 0) 1 else 0) +
        (if (time != 0) 1 else 0)

    private fun Composite.isComposite() =
        (angle.absoluteValue + currency.absoluteValue + distance.absoluteValue + electricCurrent.absoluteValue +
         luminousIntensity.absoluteValue + mass.absoluteValue + substance.absoluteValue + temperature.absoluteValue + time.absoluteValue) > 1 ||
        negatives().count() > 0

    private fun Composite.units(that: Composite): ArrayList<Pair<String, Int>>
    {
        val result = ArrayList<Pair<String, Int>>()

        if (angle != 0 && that.angle != 0) result += "angle" to that.angle else if (that.angle != 0) result += "that.angle" to that.angle
        if (currency != 0 && that.currency != 0) result += "currency" to that.currency else if (that.currency != 0) result += "that.currency" to that.currency
        if (distance != 0 && that.distance != 0) result += "distance" to that.distance else if (that.distance != 0) result += "that.distance" to that.distance
        if (electricCurrent != 0 && that.electricCurrent != 0) result += "electricCurrent" to that.electricCurrent else if (that.electricCurrent != 0) result += "that.electricCurrent" to that.electricCurrent
        if (luminousIntensity != 0 && that.luminousIntensity != 0) result += "luminousIntensity" to that.luminousIntensity else if (that.luminousIntensity != 0) result += "that.luminousIntensity" to that.luminousIntensity
        if (mass != 0 && that.mass != 0) result += "mass" to that.mass else if (that.mass != 0) result += "that.mass" to that.mass
        if (substance != 0 && that.substance != 0) result += "substance" to that.substance else if (that.substance != 0) result += "that.substance" to that.substance
        if (temperature != 0 && that.temperature != 0) result += "temperature" to that.temperature else if (that.temperature != 0) result += "that.temperature" to that.temperature
        if (time != 0 && that.time != 0) result += "time" to that.time else if (that.time != 0) result += "that.time" to that.time

        return result
    }

    private fun Composite.unitGenerics(): ArrayList<Pair<String, Int>>
    {
        val result = ArrayList<Pair<String, Int>>()

        if (angle != 0) result += "A" to angle
        if (currency != 0) result += "C" to currency
        if (distance != 0) result += "D" to distance
        if (electricCurrent != 0) result += "EC" to electricCurrent
        if (luminousIntensity != 0) result += "LI" to luminousIntensity
        if (mass != 0) result += "M" to mass
        if (substance != 0) result += "N" to substance
        if (temperature != 0) result += "K" to temperature
        if (time != 0) result += "T" to time

        return result
    }
}