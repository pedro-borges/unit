package org.pcb.units

import java.math.BigDecimal
import java.util.Objects
import org.pcb.units.domain.Domain

open class Amount<D : Domain>(val amount: BigDecimal,
                              val unit: CompositeUnit<D>)
{
    fun convert(vararg units: Pair<Unit<*>, Unit<*>>): BigDecimal =
        units.fold(1.toBigBigDecimal()) { acc, unit ->
            acc * unit.first.cannon / unit.second.cannon
        }

    override fun equals(other: Any?) =
        when
        {
            this === other     -> true
            other is Amount<*> -> amount * unit.cannon == other.amount * other.unit.cannon &&
                                  unit.domain == other.unit.domain
            else               -> false
        }

    override fun hashCode() =
        Objects.hash(amount, unit)

    override fun toString(): String
    {
        return "${amount.stripTrailingZeros().toPlainString()}$unit (${unit.domain.simpleName})"
    }
}