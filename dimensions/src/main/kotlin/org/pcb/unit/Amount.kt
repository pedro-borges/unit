package org.pcb.unit

import java.math.BigDecimal
import java.util.Objects
import org.pcb.unit.amounts.ScalarAmount
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.config.Configuration
import org.pcb.unit.config.Configuration.displayMathContext
import org.pcb.unit.dimensions.Dimension
import org.pcb.unit.dimensions.Scalar

open class Amount<D : Dimension>(val amount: BigDecimal,
                                 val unit: CompositeUnit<D>)
{
    open operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        Amount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
               unit = unit)

    open operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        Amount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
               unit = unit)

    override
    fun equals(other: Any?) =
        when
        {
            this === other     -> true
            other is Amount<*> -> normalizedAmount() == other.normalizedAmount() &&
                                  unit.domain == other.unit.domain
            else               -> false
        }

    override fun hashCode() =
        Objects.hash((amount / unit.scale).movePointRight(unit.magnitude.power).setScale(displayMathContext), unit)

    override fun toString() =
        Configuration.renderer.render(this)

    private fun normalizedAmount() =
        (amount * unit.scale)
            .movePointRight(unit.magnitude.power)
            .setScale(displayMathContext)
}
