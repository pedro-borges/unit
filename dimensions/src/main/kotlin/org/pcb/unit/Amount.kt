package org.pcb.unit

import java.math.BigDecimal
import java.util.Objects
import org.pcb.unit.amounts.ScalarAmount
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.config.Configuration
import org.pcb.unit.config.Configuration.displayMathContext
import org.pcb.unit.dimensions.Dimension
import org.pcb.unit.dimensions.Scalar

open class Amount<D : Dimension>(val amount: BigDecimal,
                                 val unit: CompositeUnit<D>)
    : Comparable<Amount<D>>
{
    open operator fun <S : Unit<Scalar>> times(that: Number) =
        Amount(amount = amount * that.toBigDecimal(),
               unit = unit)

    open operator fun <S : Unit<Scalar>> div(that: Number) =
        Amount(amount = amount / that.toBigDecimal(),
               unit = unit)

    open operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        Amount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
               unit = unit)

    open operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        Amount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
               unit = unit)

    operator fun plus(that: Amount<D>) =
        Amount(amount = amount + that.amount * convert(that.unit to unit),
               unit = unit)

    operator fun minus(that: Amount<D>) =
        Amount(amount = amount - that.amount * convert(that.unit to unit),
               unit = unit)

    operator fun times(that: Amount<*>) =
        Amount(amount = amount * that.amount * convert(that.unit to unit),
               unit = unit * that.unit)

    operator fun div(that: Amount<*>) =
        Amount(amount = amount / that.amount / convert(that.unit to unit),
               unit = unit / that.unit)

    override
    fun equals(other: Any?) =
        when
        {
            this === other     -> true
            other is Amount<*> -> normalizedAmount() == other.normalizedAmount() &&
                                  unit.dimension == other.unit.dimension
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

    // region Comparable

    override fun compareTo(other: Amount<D>) =
        (this.amount - other.amount * convert(other.unit to this.unit)).signum()

    // endregion
}
