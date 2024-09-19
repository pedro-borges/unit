package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MassAmount<M : Unit<Mass>>(
        amount: Number,
        unit: M)
    : Amount<Mass>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Mass::class,
                       units = mapOf(unit to 1)))
{
    val mass = unit

    // region -> Mass

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MassAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                   unit = mass)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MassAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                   unit = mass)

    @JvmName("plusMass")
    operator fun <M : Unit<Mass>> plus(that: MassAmount<M>) =
        MassAmount(amount = amount + that.amount
                            * convert(that.mass to mass),
                   unit = mass)

    operator fun plus(that: MassAmount<M>) =
        MassAmount(amount = amount + that.amount,
                   unit = mass)

    @JvmName("minusMass")
    operator fun <M : Unit<Mass>> minus(that: MassAmount<M>) =
        MassAmount(amount = amount - that.amount
                            * convert(that.mass to mass),
                   unit = mass)

    operator fun minus(that: MassAmount<M>) =
        MassAmount(amount = amount - that.amount,
                   unit = mass)

    // endregion

    // region -> Scalar

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass),
                     unit = NUMERIC)

    operator fun div(that: MassAmount<M>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> AngularMomentum

    @JvmName("timesMomentum")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<D, M, T>) =
        AngularMomentumAmount(amount = amount * that.amount
                                       * convert(that.mass to mass),
                              unit = U2(mass, that.distance) to that.time)

    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: MomentumAmount<D, M, T>) =
        AngularMomentumAmount(amount = amount * that.amount,
                              unit = U2(mass, that.distance) to that.time)

    // endregion

    // region -> Force

    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<D, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.distance, mass) to that.time)

    // endregion

    // region -> MassFlowRate

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to that.time)

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to that.time)

    // endregion

    // region -> Momentum

    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<D, T>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(that.distance, mass) to that.time)

    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(that.distance, mass) to that.time)

    // endregion

    // region -> Pace

    @JvmName("divMomentum")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<D, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.mass to mass),
                   unit = that.time to that.distance)

    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: MomentumAmount<D, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = that.time to that.distance)

    // endregion

    // region -> Time

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.mass to mass),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    // endregion

    // region -> Volume

    @JvmName("timesSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<D, M>) =
        VolumeAmount(amount = amount * that.amount
                              / convert(that.mass to mass),
                     unit = that.distance)

    operator fun <D : Unit<Distance>> times(that: SpecificVolumeAmount<D, M>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = that.distance)

    // endregion
}
