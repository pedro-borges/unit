package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Momentum
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MomentumAmount<D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<M, D>, T>)
    : Amount<Momentum>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           domain = Momentum::class,
                           units = mapOf(unit.major.first to 1,
                                         unit.major.second to 1,
                                         unit.minor to -1)))
{
    val mass = unit.major.first
    val distance = unit.major.second
    val time = unit.minor

    // region -> Momentum

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                       unit = U2(mass, distance) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                       unit = U2(mass, distance) to time)

    @JvmName("plusMomentum")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> plus(that: MomentumAmount<D, M, T>) =
        MomentumAmount(amount = amount + that.amount
                                * convert(that.mass to mass,
                                          that.distance to distance)
                                / convert(that.time to time),
                       unit = U2(mass, distance) to time)

    operator fun plus(that: MomentumAmount<D, M, T>) =
        MomentumAmount(amount = amount + that.amount,
                       unit = U2(mass, distance) to time)

    @JvmName("minusMomentum")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> minus(that: MomentumAmount<D, M, T>) =
        MomentumAmount(amount = amount - that.amount
                                * convert(that.mass to mass,
                                          that.distance to distance)
                                / convert(that.time to time),
                       unit = U2(mass, distance) to time)

    operator fun minus(that: MomentumAmount<D, M, T>) =
        MomentumAmount(amount = amount - that.amount,
                       unit = U2(mass, distance) to time)

    // endregion

    // region -> Numeric

    @JvmName("divMomentum")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: MomentumAmount<D, M, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.mass to mass,
                                   that.distance to distance)
                         * convert(that.time to time))

    operator fun div(that: MomentumAmount<D, M, T>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Speed

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount / convert(that.mass to mass),
                    unit = distance to time)

    operator fun div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> Mass

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time),
                   unit = mass)

    operator fun div(that: SpeedAmount<D, T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> Force

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount / convert(that.time to time),
                    unit = U2(mass, distance) to time)

    operator fun div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(mass, distance) to time)

    // endregion

    // region -> Energy

    @JvmName("timesSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance)
                              / convert(that.time to time),
                     unit = U2(mass, distance) to time)

    operator fun times(that: SpeedAmount<D, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(mass, distance) to time)

    // endregion

    // region -> MassFlowRate

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        MassFlowRateAmount(amount = amount / that.amount / convert(that.distance to distance),
                           unit = mass to time)

    operator fun div(that: DistanceAmount<D>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    // endregion

    // region -> Distance

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert((that.time to time)),
                       unit = distance)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion
}
