package org.pcb.unit

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.sign
import org.pcb.unit.config.Configuration.calculationMathContext

fun Number.toBigDecimal(): BigDecimal =
    when
    {
        this is BigDecimal -> this.stripTrailingZeros()
        else               -> BigDecimal(toString()).stripTrailingZeros()
    }

fun BigDecimal.setScale(mathContext: MathContext): BigDecimal =
    setScale(mathContext.precision, mathContext.roundingMode)

fun MutableMap<Unit<*>, Int>.putOrRemove(unit: Unit<*>, value: Int)
{
    if (value == 0) this.remove(unit)
    else this[unit] = value
}

fun BigDecimal.power(power: Int): BigDecimal
{
    var result = 1.toBigDecimal()

    if (power > 0)
        for (a in 1..kotlin.math.abs(power))
            result = result.multiply(this, calculationMathContext)

    if (power < 0)
        for (a in 1..kotlin.math.abs(power))
            result = result.divide(this, calculationMathContext)

    return result
}

fun Collection<Pair<Unit<*>, Int>>.sumToMap(): Map<Unit<*>, Int>
{
    val result = HashMap<Unit<*>, Int>()

    forEach { result[it.first] = (result[it.first] ?: 0) + it.second }

    return result
}

fun convert(vararg units: Pair<Unit<*>, Unit<*>>): BigDecimal =
    units.fold(1.toBigDecimal()) { acc, unit ->
        (acc * unit.first.scale).toBigDecimal()
            .movePointLeft(unit.second.magnitude.power - unit.first.magnitude.power)
            .divide(unit.second.scale, calculationMathContext)
            .stripTrailingZeros()
    }

fun convert(units: Pair<CompositeUnit<*>, CompositeUnit<*>>): BigDecimal
{
    var result = 1.toBigDecimal()

    val main = units.first
    val that = units.second

    that.units.keys.sortedByDescending { that.units[it]!! }
        .forEach { unit ->
            val first = main[unit.dimension]?.let { it to main.units[it]!! }
            val second = unit to that.units[unit]!!

            if (first == null)
            {

            } else if (first.second.sign == 1 && second.second.sign == 1)
            {
                for (i in 1..second.second)
                {
                    result /= convert(second.first to first.first)
                }
            } else if (first.second.sign == 1 && second.second.sign == -1)
            {
                for (i in 1 downTo second.second)
                {
                    result *= convert(second.first to first.first)
                }
            } else if (first.second.sign == -1 && second.second.sign == 1)
            {
                for (i in -1..second.second)
                {
                    result /= convert(second.first to first.first)
                }
            } else if (first.second.sign == -1 && second.second.sign == -1)
            {
                for (i in -1 downTo second.second)
                {
                    result *= convert(second.first to first.first)
                }
            }
        }

    return result
}

operator fun Number.times(other: BigDecimal): BigDecimal = toBigDecimal().multiply(other, calculationMathContext).stripTrailingZeros()
operator fun Number.div(other: BigDecimal): BigDecimal = toBigDecimal().divide(other, calculationMathContext).stripTrailingZeros()

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
