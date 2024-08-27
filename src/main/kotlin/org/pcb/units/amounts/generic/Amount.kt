package org.pcb.units.amounts.generic

import java.math.BigDecimal
import java.math.RoundingMode.HALF_EVEN
import java.util.Objects
import org.pcb.units.domain.Domain
import org.pcb.units.unit.CompositeUnit

open class Amount<D : Domain>(val amount: BigDecimal,
                              val unit: CompositeUnit<D>)
{
    operator fun plus(that: Amount<D>) =
        Amount(amount = amount + that.amount * that.unit.cannon / unit.cannon,
               unit = unit)

    operator fun minus(that: Amount<D>) =
        Amount(amount = amount - that.amount * that.unit.cannon / unit.cannon,
               unit = unit)

    operator fun times(that: Amount<*>) =
        Amount(amount = amount * that.amount,
               unit = unit * that.unit)

    operator fun div(that: Amount<D>) =
        ScalarAmount(amount = amount * unit.cannon / that.amount / that.unit.cannon)

    fun using(unit: CompositeUnit<D>) =
        Amount(amount = amount * unit.cannon / unit.cannon,
               unit = unit)

    override fun equals(other: Any?) =
        when
        {
            this === other     -> true
            other is Amount<*> -> amount == other.amount &&
                                  unit == other.unit
            else               -> false
        }

    override fun hashCode() =
        Objects.hash(amount, unit)

    override fun toString(): String
    {
        return "${amount.setScale(33, HALF_EVEN).stripTrailingZeros().toPlainString()}$unit (${unit.domain.simpleName})"
    }
}