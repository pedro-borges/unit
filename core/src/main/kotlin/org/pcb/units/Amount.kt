package org.pcb.units

import java.math.BigDecimal
import java.math.MathContext.DECIMAL128
import java.util.Objects
import org.pcb.units.domain.Domain

open class Amount<D : Domain>(val amount: BigDecimal,
                              val unit: CompositeUnit<D>)
{
    fun using(unit: CompositeUnit<D>) =
        Amount(amount = amount * unit.cannon / unit.cannon,
               unit = unit)

    fun convert(vararg units: Pair<Unit<*>, Unit<*>>): BigDecimal =
        units.fold(1.toBigBigDecimal()) { acc, unit ->
            acc.multiply(unit.first.cannon, DECIMAL128).divide(unit.second.cannon, DECIMAL128)
        }

    override fun equals(other: Any?) =
        when
        {
            this === other     -> true
            other is Amount<*> -> amount.stripTrailingZeros().toPlainString() == other.amount.stripTrailingZeros().toPlainString() &&
                                  unit == other.unit
            else               -> false
        }

    override fun hashCode() =
        Objects.hash(amount, unit)

    override fun toString(): String
    {
        return "${amount.stripTrailingZeros().toPlainString()}$unit (${unit.domain.simpleName})"
    }
}