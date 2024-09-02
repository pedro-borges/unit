package org.pcb.units

import java.math.BigDecimal
import java.math.MathContext.DECIMAL128
import java.math.RoundingMode.HALF_EVEN
import org.pcb.units.dimension.Domain

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

infix fun <D : Domain, U: Unit<D>> U.or(that: U): U =
    if (scale == that.scale) this
    else throw IllegalStateException("$this and $that differ")

fun convert(vararg units: Pair<Unit<*>, Unit<*>>): BigDecimal =
    units.fold(1.toBigBigDecimal()) { acc, unit ->
        acc * unit.first.scale / unit.second.scale
    }

operator fun BigDecimal.div(other: BigDecimal): BigDecimal = divide(other, 42, HALF_EVEN)

operator fun BigDecimal.times(other: BigDecimal): BigDecimal = multiply(other, DECIMAL128)