package org.pcb.unit.amount

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal

open class MassAmount<M : Unit<Mass>>(amount: Number,
                                      val mass: M)
    : Amount<Mass>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Mass::class,
                       units = listOf(mass to 1)))
{
    // region Mass

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MassAmount(amount = amount * that.amount * that.unit.scale,
                   mass = mass)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MassAmount(amount = amount / that.amount / that.unit.scale,
                   mass = mass)

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

    operator fun <L : Unit<Length>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        MomentumAmount(amount = that.amount * that.amount,
                       mass = mass,
                       length = that.length,
                       time = that.time)

    // endregion

    // region Force

    operator fun <L : Unit<Length>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        ForceAmount(amount = that.amount * that.amount,
                    mass = mass,
                    length = that.length,
                    time = that.time,
                    time2 = that.time)

    // endregion
}