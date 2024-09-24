package org.pcb.unit.generator

import kotlin.math.absoluteValue
import org.pcb.unit.Composite
import org.pcb.unit.generator.Dimensions.max
import org.pcb.unit.generator.Dimensions.min

fun Composite.buildGenericDeclaration(): String
{
    val result = ArrayList<String>()

    if (angle != 0) result += "BAC : Unit<Angle>"
    if (currency != 0) result += "C : Unit<Currency>"
    if (distance != 0) result += "L : Unit<Distance>"
    if (electricCurrent != 0) result += "I : Unit<ElectricCurrent>"
    if (luminousIntensity != 0) result += "J : Unit<LuminousIntensity>"
    if (mass != 0) result += "M : Unit<Mass>"
    if (substance != 0) result += "N : Unit<Substance>"
    if (temperature != 0) result += "K : Unit<Temperature>"
    if (time != 0) result += "T : Unit<Time>"

    return if (result.isEmpty()) "" else "<" + result.joinToString(", ") + ">"
}

fun Composite.buildGenericReceiver(): String
{
    val result = ArrayList<String>()

    if (angle != 0) result += "BAC"
    if (currency != 0) result += "C"
    if (distance != 0) result += "L"
    if (electricCurrent != 0) result += "I"
    if (luminousIntensity != 0) result += "J"
    if (mass != 0) result += "M"
    if (substance != 0) result += "N"
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
              substance = if (that.substance != 0) substance else 0,
              temperature = if (that.temperature != 0) temperature else 0,
              time = if (that.time != 0) time else 0)

fun Composite.drop(that: Composite) =
    Composite(angle = if (that.angle != 0) 0 else angle,
              currency = if (that.currency != 0) 0 else currency,
              distance = if (that.distance != 0) 0 else distance,
              electricCurrent = if (that.electricCurrent != 0) 0 else electricCurrent,
              luminousIntensity = if (that.luminousIntensity != 0) 0 else luminousIntensity,
              mass = if (that.mass != 0) 0 else mass,
              substance = if (that.substance != 0) 0 else substance,
              temperature = if (that.temperature != 0) 0 else temperature,
              time = if (that.time != 0) 0 else time)

fun Composite.buildConvert(): String
{
    val result = ArrayList<String>()

    for (a in 1..angle) result += "that.angle to angle"
    for (a in 1..currency) result += "that.currency to currency"
    for (a in 1..distance) result += "that.distance to distance"
    for (a in 1..electricCurrent) result += "that.electricCurrent to electricCurrent"
    for (a in 1..luminousIntensity) result += "that.luminousIntensity to luminousIntensity"
    for (a in 1..mass) result += "that.mass to mass"
    for (a in 1..substance) result += "that.substance to substance"
    for (a in 1..temperature) result += "that.temperature to temperature"
    for (a in 1..time) result += "that.time to time"

    return result.joinToString(",\n\t\t\t\t")
}

fun Composite.buildUnit(that: Composite): String
{
    val thatPositives = that.positives()
    val thatNegatives = that.negatives()

    val major = when (thatPositives.count())
    {
        0    -> null
        1    -> units(thatPositives).sortedByDescending { it.second }.joinToString(", ") { it.first }
        2    -> "U2(${units(thatPositives).sortedByDescending { it.second }.joinToString(", ") { it.first }})"
        3    -> "U3(${units(thatPositives).sortedByDescending { it.second }.joinToString(", ") { it.first }})"
        4    -> "U4(${units(thatPositives).sortedByDescending { it.second }.joinToString(", ") { it.first }})"
        5    -> "U5(${units(thatPositives).sortedByDescending { it.second }.joinToString(", ") { it.first }})"
        6    -> "U6(${units(thatPositives).sortedByDescending { it.second }.joinToString(", ") { it.first }})"
        else -> throw IllegalArgumentException()
    }

    val minor = when (thatNegatives.count())
    {
        0    -> null
        1    -> units(thatNegatives).sortedBy { it.second }.joinToString(", ") { it.first }
        2    -> "U2(${units(thatNegatives).sortedBy { it.second }.joinToString(", ") { it.first }})"
        3    -> "U3(${units(thatNegatives).sortedBy { it.second }.joinToString(", ") { it.first }})"
        4    -> "U4(${units(thatNegatives).sortedBy { it.second }.joinToString(", ") { it.first }})"
        5    -> "U5(${units(thatNegatives).sortedBy { it.second }.joinToString(", ") { it.first }})"
        6    -> "U6(${units(thatNegatives).sortedBy { it.second }.joinToString(", ") { it.first }})"
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

fun Composite.buildCanonical(): String
{
    val positives = positives()
    val negatives = negatives()

    val canons = mapOf("BAC" to "RADIAN",
                       "C" to "EUR",
                       "L" to "METER",
                       "I" to "AMPERE",
                       "J" to "CANDELA",
                       "K" to "KELVIN",
                       "M" to "kilo(GRAM)",
                       "N" to "MOLE",
                       "T" to "SECOND")

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

fun Composite.buildType(): String
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

fun Composite.buildMap(): String
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

fun Composite.buildCanonMap(): String
{
    val result = ArrayList<String>()

    for (i in max() downTo min())
    {
        if (i == 0) continue

        if (angle == i) result += "Angle to $i"
        if (currency == i) result += "Currency to $i"
        if (distance == i) result += "Distance to $i"
        if (electricCurrent == i) result += "ElectricCurrent to $i"
        if (luminousIntensity == i) result += "LuminousIntensity to $i"
        if (mass == i) result += "Mass to $i"
        if (substance == i) result += "Substance to $i"
        if (temperature == i) result += "Temperature to $i"
        if (time == i) result += "Time to $i"
    }

    return if (result.isEmpty()) "mapOf<Dimension, Int>()"
    else "mapOf(${result.joinToString(",\n")})"
}

fun Composite.buildConstructor(domainName: String) =
    if (domainName !in setOf("Debt", "Rent") && isComposite())
    {
        "\n" +
        "\tcompanion object\n" +
        "\t{\n" +
        "\t\toperator fun invoke(amount: Number, unit: Unit<$domainName>) =\n" +
        "\t\t\t\t${domainName}Amount(amount = amount * unit.scale,\n" +
        "\t\t\t\t\t\tunit = ${buildCanonical()})\n" +
        "\t}\n"
    } else ""

fun Composite.buildVariables(): String
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

fun Int.pair() =
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

fun Composite.map(): Map<String, Int>
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

fun Composite.positives() =
    Composite(angle = if (angle > 0) angle else 0,
              currency = if (currency > 0) currency else 0,
              distance = if (distance > 0) distance else 0,
              electricCurrent = if (electricCurrent > 0) electricCurrent else 0,
              luminousIntensity = if (luminousIntensity > 0) luminousIntensity else 0,
              mass = if (mass > 0) mass else 0,
              substance = if (substance > 0) substance else 0,
              temperature = if (temperature > 0) temperature else 0,
              time = if (time > 0) time else 0)

fun Composite.negatives() =
    Composite(angle = if (angle < 0) -angle else 0,
              currency = if (currency < 0) -currency else 0,
              distance = if (distance < 0) -distance else 0,
              electricCurrent = if (electricCurrent < 0) -electricCurrent else 0,
              luminousIntensity = if (luminousIntensity < 0) -luminousIntensity else 0,
              mass = if (mass < 0) -mass else 0,
              substance = if (substance < 0) -substance else 0,
              temperature = if (temperature < 0) -temperature else 0,
              time = if (time < 0) -time else 0)

fun Composite.count() =
    (if (angle != 0) 1 else 0) +
    (if (currency != 0) 1 else 0) +
    (if (distance != 0) 1 else 0) +
    (if (electricCurrent != 0) 1 else 0) +
    (if (luminousIntensity != 0) 1 else 0) +
    (if (mass != 0) 1 else 0) +
    (if (substance != 0) 1 else 0) +
    (if (temperature != 0) 1 else 0) +
    (if (time != 0) 1 else 0)

fun Composite.isComposite() =
    (angle.absoluteValue + currency.absoluteValue + distance.absoluteValue + electricCurrent.absoluteValue +
     luminousIntensity.absoluteValue + mass.absoluteValue + substance.absoluteValue + temperature.absoluteValue + time.absoluteValue) > 1 ||
    negatives().count() > 0

fun Composite.units(that: Composite): ArrayList<Pair<String, Int>>
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

fun Composite.unitGenerics(): ArrayList<Pair<String, Int>>
{
    val result = ArrayList<Pair<String, Int>>()

    if (angle != 0) result += "BAC" to angle
    if (currency != 0) result += "C" to currency
    if (distance != 0) result += "L" to distance
    if (electricCurrent != 0) result += "I" to electricCurrent
    if (luminousIntensity != 0) result += "J" to luminousIntensity
    if (mass != 0) result += "M" to mass
    if (substance != 0) result += "N" to substance
    if (temperature != 0) result += "K" to temperature
    if (time != 0) result += "T" to time

    return result
}