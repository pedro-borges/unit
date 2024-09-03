package org.pcb.unit

import java.math.BigDecimal
import java.util.Objects
import org.pcb.unit.dimension.Domain
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.units.ScalarAmount

abstract class Amount<D : Domain>(val amount: BigDecimal,
                                  val unit: CompositeUnit<D>)
{
    abstract operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>): Amount<D>

    abstract operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>): Amount<D>

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
}
