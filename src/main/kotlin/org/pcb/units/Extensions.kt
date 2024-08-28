package org.pcb.units

import java.math.BigDecimal
import java.math.MathContext.DECIMAL128
import org.pcb.units.domain.Domain
import org.pcb.units.unit.Unit

fun Number.toBigDecimal() =
    when
    {
        this is BigDecimal -> this
        else               -> BigDecimal(toString())
    }

fun MutableMap<Unit<*>, Int>.putOrRemove(unit: Unit<*>, value: Int)
{
    if (value == 0) this.remove(unit)
    else this.put(unit, value)
}

fun BigDecimal.power(power: Int): BigDecimal
{
    var result = 1.0.toBigDecimal()
    for (a in 1..kotlin.math.abs(power))
    {
        if (power > 0) result = result.multiply(this, DECIMAL128)
        if (power < 0) result = result.divide(this, DECIMAL128)
    }
    return result
}

infix fun <D : Domain> Unit<D>.or(other: Unit<D>) =
    if (this == other) this
    else throw IllegalArgumentException("$this and $other are not the same")