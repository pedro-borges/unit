package org.pcb.unit

import java.math.BigDecimal
import java.util.Objects
import org.pcb.unit.config.Configuration
import org.pcb.unit.config.Configuration.displayMathContext
import org.pcb.unit.dimension.Dimension
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.units.ScalarAmount

abstract class Amount<D : Dimension>(val amount: BigDecimal,
                                     val unit: CompositeUnit<D>)
{
    abstract operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>): Amount<D>
    abstract operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>): Amount<D>

    override fun equals(other: Any?) =
        when
        {
            this === other     -> true
            other is Amount<*> -> amount.movePointRight(unit.magnitude.power).setScale(displayMathContext) ==
                                  other.amount.movePointRight(other.unit.magnitude.power).setScale(displayMathContext) &&
                                  unit.domain == other.unit.domain
            else               -> false
        }

    override fun hashCode() =
        Objects.hash(amount.movePointRight(unit.magnitude.power).setScale(displayMathContext), unit)

    override fun toString() =
        Configuration.renderer.render(this)
}
