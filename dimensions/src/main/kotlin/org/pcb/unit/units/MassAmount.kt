package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class MassAmount<M : Unit<Mass>>(
        amount: Number,
        unit: M)
    : Amount<Mass>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Mass::class,
                       units = listOf(unit to 1)))
{
    val mass = unit

    // region -> Mass

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MassAmount(amount = amount * that.amount * that.unit.scale,
                   unit = mass)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MassAmount(amount = amount / that.amount / that.unit.scale,
                   unit = mass)

    @JvmName("plusMass")
    operator fun <M : Unit<Mass>> plus(that: MassAmount<M>) =
        MassAmount(amount = amount + that.amount * convert(that.mass to mass),
                   unit = mass)

    operator fun plus(that: MassAmount<M>) =
        MassAmount(amount = amount + that.amount,
                   unit = mass)

    @JvmName("minusMass")
    operator fun <M : Unit<Mass>> minus(that: MassAmount<M>) =
        MassAmount(amount = amount - that.amount * convert(that.mass to mass),
                   unit = mass)

    operator fun minus(that: MassAmount<M>) =
        MassAmount(amount = amount - that.amount,
                   unit = mass)

    // endregion

    // region -> Natural

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        ScalarAmount(amount = amount / that.amount / convert(that.mass to mass),
                     unit = NUMERIC)

    operator fun div(that: MassAmount<M>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Momentum

    operator fun <L : Unit<Length>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(mass, that.length) to that.time)

    // endregion

    // region -> Force

    operator fun <L : Unit<Length>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(mass, that.length) to that.time)

    // endregion
}