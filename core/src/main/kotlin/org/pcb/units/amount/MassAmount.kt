package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

open class MassAmount<M : Unit<Mass>>(amount: Number,
                                      val mass: M)
    : Amount<Mass>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Mass::class,
                       units = listOf(mass to 1)))
{
    // region Mass

    operator fun <M : Unit<Mass>> plus(that: MassAmount<M>) =
        MassAmount(amount = amount + that.amount * convert(that.mass to mass),
                   mass = mass)

    @JvmName("plusMass")
    operator fun plus(that: MassAmount<M>) =
        MassAmount(amount = amount + that.amount,
                   mass = mass)

    operator fun <M : Unit<Mass>> minus(that: MassAmount<M>) =
        MassAmount(amount = amount - that.amount * convert(that.mass to mass),
                   mass = mass)

    @JvmName("minusMass")
    operator fun minus(that: MassAmount<M>) =
        MassAmount(amount = amount - that.amount,
                   mass = mass)

    // endregion

    // region Scalar

    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        NaturalAmount(amount = amount / that.amount / convert(that.mass to mass))

    @JvmName("divMass")
    operator fun div(that: MassAmount<M>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Momentum

    operator fun <S : Unit<Space>, T : Unit<Time>> times(that: SpeedAmount<S, T>) =
        MomentumAmount(amount = that.amount * that.amount,
                       mass = mass,
                       space = that.space,
                       time = that.time)

    // endregion

    // region Force

    operator fun <S : Unit<Space>, T : Unit<Time>> times(that: AccelerationAmount<S, T>) =
        ForceAmount(amount = that.amount * that.amount,
                    mass = mass,
                    space = that.space,
                    time = that.time)

    // endregion
}