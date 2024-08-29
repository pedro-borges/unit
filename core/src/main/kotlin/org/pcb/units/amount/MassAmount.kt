package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Mass
import org.pcb.units.toBigBigDecimal

open class MassAmount<M : Unit<Mass>>(amount: Number,
                                      val mass: M)
    : Amount<Mass>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Mass::class,
                       units = listOf(mass to 1)))
{
    // region Mass

    operator fun plus(that: MassAmount<*>) =
        MassAmount(amount = amount + that.amount * convert(that.mass to mass),
                   mass = mass)

    @JvmName("plusM")
    operator fun plus(that: MassAmount<M>) =
        MassAmount(amount = amount + that.amount,
                   mass = mass)

    operator fun minus(that: MassAmount<*>) =
        MassAmount(amount = amount - that.amount * convert(that.mass to mass),
                   mass = mass)

    @JvmName("minusM")
    operator fun minus(that: MassAmount<M>) =
        MassAmount(amount = amount - that.amount,
                   mass = mass)

    // endregion

    // region Scalar

    operator fun div(that: MassAmount<*>) =
        NaturalAmount(amount = amount / that.amount / convert(that.mass to mass))

    @JvmName("divM")
    operator fun div(that: MassAmount<M>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Momentum

    operator fun times(that: SpeedAmount<*, *>) =
        MomentumAmount(amount = that.amount * that.amount,
                       mass = mass,
                       space = that.space,
                       time = that.time)

    // endregion

    // region Force

    operator fun times(that: AccelerationAmount<*, *, *>) =
        ForceAmount(amount = that.amount * that.amount,
                    mass = mass,
                    space = that.space,
                    time1 = that.time1,
                    time2 = that.time2)

    // endregion
}