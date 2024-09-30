package org.pcb.unit

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.sign
import org.pcb.unit.config.Configuration.calculationMathContext
import org.pcb.unit.dimensions.Dimension

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

            when
            {
                first == null                                       ->
                {
                }
                first.second.sign == 1 && second.second.sign == 1   ->
                {
                    for (i in 1..second.second)
                    {
                        result /= convert(second.first to first.first)
                    }
                }
                first.second.sign == 1 && second.second.sign == -1  ->
                {
                    for (i in 1 downTo second.second)
                    {
                        result *= convert(second.first to first.first)
                    }
                }
                first.second.sign == -1 && second.second.sign == 1  ->
                {
                    for (i in -1..second.second)
                    {
                        result /= convert(second.first to first.first)
                    }
                }
                first.second.sign == -1 && second.second.sign == -1 ->
                {
                    for (i in -1 downTo second.second)
                    {
                        result *= convert(second.first to first.first)
                    }
                }
            }
        }

    return result
}

operator fun Number.times(other: Number): BigDecimal = toBigDecimal().multiply(other.toBigDecimal(), calculationMathContext).stripTrailingZeros()
operator fun Number.div(other: Number): BigDecimal = toBigDecimal().divide(other.toBigDecimal(), calculationMathContext).stripTrailingZeros()
fun max(value: BigDecimal, vararg values: BigDecimal) = (values.toList() + value).maxOf { it }
fun min(value: BigDecimal, vararg values: BigDecimal) = (values.toList() + value).minOf { it }

fun <A : Amount<D>, D : Dimension> max(value: A, vararg values: A) = (values.toList() + value).maxOf { it }
fun <A : Amount<D>, D : Dimension> min(value: A, vararg values: A) = (values.toList() + value).minOf { it }

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

fun Int.toSubscript() =
    toString()
        .replace('-', '₋')
        .replace('0', '₀')
        .replace('1', '₁')
        .replace('2', '₂')
        .replace('3', '₃')
        .replace('4', '₄')
        .replace('5', '₅')
        .replace('6', '₆')
        .replace('7', '₇')
        .replace('8', '₈')
        .replace('9', '₉')
