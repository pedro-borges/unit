package org.pcb.unit

import java.math.BigDecimal
import java.math.MathContext.DECIMAL128
import java.math.RoundingMode.HALF_EVEN

fun Number.toBigBigDecimal(): BigDecimal =
    when
    {
        this is BigDecimal -> setScale(33, HALF_EVEN)
        else               -> BigDecimal(toString()).setScale(33, HALF_EVEN)
    }

fun MutableMap<Unit<*>, Int>.putOrRemove(unit: Unit<*>, value: Int)
{
    if (value == 0) this.remove(unit)
    else this[unit] = value
}

fun BigDecimal.power(power: Int): BigDecimal
{
    var result = 1.toBigBigDecimal()
    for (a in 1..kotlin.math.abs(power))
    {
        if (power > 0) result = result.multiply(this, DECIMAL128)
        if (power < 0) result = result.divide(this, DECIMAL128)
    }
    return result
}

fun Collection<Pair<Unit<*>, Int>>.sumToMap(): Map<Unit<*>, Int>
{
    val result = HashMap<Unit<*>, Int>()

    forEach { result[it.first] = (result[it.first] ?: 0) + it.second }

    return result
}

fun convert(vararg units: Pair<Unit<*>, Unit<*>>): BigDecimal =
    units.fold(1.toBigBigDecimal()) { acc, unit ->
        (acc * unit.first.scale).toBigBigDecimal()
            .divide(unit.second.magnitude.scale.divide(unit.first.magnitude.scale), 42, HALF_EVEN)
            .divide(unit.second.scale, 42, HALF_EVEN).stripTrailingZeros()
    }

operator fun Number.times(other: BigDecimal): BigDecimal = toBigBigDecimal().multiply(other, DECIMAL128).stripTrailingZeros()
operator fun Number.div(other: BigDecimal): BigDecimal = toBigBigDecimal().divide(other, 42, HALF_EVEN).stripTrailingZeros()

val <A, B> Pair<A, B>.major: A get() = first
val <A, B> Pair<A, B>.minor get() = second


fun Int.toPowerString() =
    when
    {
        this == 1 -> ""
        this == 0 -> throw IllegalStateException("Unexpected power of 0")
        else      -> toSuperscript()
    }

fun Int.toSuperscript() =
    toString()
        .replace('-', '⁻')
        .replace('0', '⁰')
        .replace('1', '¹')
        .replace('2', '²')
        .replace('3', '³')
        .replace('4', '⁴')
        .replace('5', '⁵')
        .replace('6', '⁶')
        .replace('7', '⁷')
        .replace('8', '⁸')
        .replace('9', '⁹')
