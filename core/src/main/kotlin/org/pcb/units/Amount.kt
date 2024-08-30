package org.pcb.units

import java.math.BigDecimal
import java.util.Objects
import org.pcb.units.amount.ScalarAmount
import org.pcb.units.domain.Domain
import org.pcb.units.domain.Scalar

abstract class Amount<D : Domain>(val amount: BigDecimal,
                              val unit: CompositeUnit<D>)
{
    abstract operator fun<S : Unit<Scalar>> times(that: ScalarAmount<S>) : Amount<D>

    abstract operator fun<S : Unit<Scalar>> div(that: ScalarAmount<S>) : Amount<D>

    override fun equals(other: Any?) =
        when
        {
            this === other     -> true
            other is Amount<*> -> amount * unit.scale == other.amount * other.unit.scale &&
                                  unit.domain == other.unit.domain
            else               -> false
        }

    override fun hashCode() =
        Objects.hash(amount, unit)

    override fun toString(): String
    {
        return "${amount.stripTrailingZeros().toPlainString()}$unit (${unit.domain.simpleName})"
    }

    companion object
    {
        fun convert(vararg units: Pair<Unit<*>, Unit<*>>): BigDecimal =
            units.fold(1.toBigBigDecimal()) { acc, unit ->
                acc * unit.first.scale / unit.second.scale
            }
    }
}