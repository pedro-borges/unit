package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.NaturalAmount

open class MassAmount<M : Unit<Mass>>(
        amount: Number,
        major: M)
    : Amount<Mass>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Mass::class,
                       units = listOf(major to 1)))
{
    val mass = major

    // region Mass

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MassAmount(amount = amount * that.amount * that.unit.scale,
                   major = mass)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MassAmount(amount = amount / that.amount / that.unit.scale,
                   major = mass)

    operator fun <M : Unit<Mass>> plus(that: MassAmount<M>) =
        MassAmount(amount = amount + that.amount * convert(that.mass to mass),
                   major = mass)

    @JvmName("plusMass")
    operator fun plus(that: MassAmount<M>) =
        MassAmount(amount = amount + that.amount,
                   major = mass)

    operator fun <M : Unit<Mass>> minus(that: MassAmount<M>) =
        MassAmount(amount = amount - that.amount * convert(that.mass to mass),
                   major = mass)

    @JvmName("minusMass")
    operator fun minus(that: MassAmount<M>) =
        MassAmount(amount = amount - that.amount,
                   major = mass)

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
                       major = mass * that.length,
                       minor = that.time)

    // endregion

    // region Force

    operator fun <L : Unit<Length>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        ForceAmount(amount = that.amount * that.amount,
                    major = mass * that.length,
                    minor = that.time * that.time)

    // endregion
}