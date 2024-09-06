package org.pcb.unit

import java.math.BigDecimal
import java.math.MathContext.DECIMAL128
import java.math.RoundingMode.HALF_EVEN

fun String.toBigBigDecimal() =
    BigDecimal(this)

fun Number.toBigBigDecimal(): BigDecimal =
    when
    {
        this is BigDecimal -> this.setScale(33, HALF_EVEN)
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
        acc * unit.first.scale / unit.second.scale
    }

typealias Fraction<A, B> = Pair<A, B>

operator fun Number.div(other: BigDecimal): BigDecimal = toBigBigDecimal().divide(other, 42, HALF_EVEN).stripTrailingZeros()
operator fun Number.times(other: BigDecimal): BigDecimal = toBigBigDecimal().multiply(other, DECIMAL128).stripTrailingZeros()

val <A, B> Fraction<A, B>.major: A get() = first
val <A, B> Fraction<A, B>.minor get() = second
