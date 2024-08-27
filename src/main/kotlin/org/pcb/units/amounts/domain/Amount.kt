package org.pcb.units.amounts.domain

import java.math.BigDecimal
import java.util.Objects
import org.pcb.units.domain.Domain
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class Amount<D : Domain>(val amount: BigDecimal,
                              val unit: CompositeUnit<D>)
{
    operator fun plus(that: Amount<D>) =
        Amount(amount = this.amount + that.amount * that.unit.cannon / this.unit.cannon,
               unit = unit)

    operator fun minus(that: Amount<D>) =
        Amount(amount = this.amount - that.amount * that.unit.cannon / this.unit.cannon,
               unit = unit)

    operator fun times(that: Amount<*>) =
        Amount(amount = this.amount * that.amount,
               unit = unit * that.unit)

    operator fun div(that: Amount<D>) =
        ScalarAmount(amount = amount * unit.cannon / that.amount / that.unit.cannon)

    fun using(unit: Unit<D>) =
        Amount(amount = this.amount * this.unit.cannon / unit.cannon,
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
        return "${amount.stripTrailingZeros().toPlainString()}$unit"
    }
}