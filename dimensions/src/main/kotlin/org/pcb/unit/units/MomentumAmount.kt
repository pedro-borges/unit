package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Momentum
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class MomentumAmount<M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<M, D>, T>)
    : Amount<Momentum>(amount = amount.toBigBigDecimal(),
                       unit = CompositeUnit(
                           domain = Momentum::class,
                           units = listOf(unit.major.first to 1,
                                          unit.major.second to 1,
                                          unit.minor to -1)))
{
    val mass = unit.major.first
    val distance = unit.major.second
    val time = unit.minor

    // region -> Momentum

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount * that.amount * that.unit.scale,
                       unit = U2(mass, distance) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount / that.amount / that.unit.scale,
                       unit = U2(mass, distance) to time)

    @JvmName("plusMomentum")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> plus(that: MomentumAmount<M, D, T>) =
        MomentumAmount(amount = amount + that.amount
                                * convert(that.mass to mass,
                                          that.distance to distance)
                                / convert(that.time to time),
                       unit = U2(mass, distance) to time)

    operator fun plus(that: MomentumAmount<M, D, T>) =
        MomentumAmount(amount = amount + that.amount,
                       unit = U2(mass, distance) to time)

    @JvmName("minusMomentum")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> minus(that: MomentumAmount<M, D, T>) =
        MomentumAmount(amount = amount - that.amount
                                * convert(that.mass to mass,
                                          that.distance to distance)
                                / convert(that.time to time),
                       unit = U2(mass, distance) to time)

    operator fun minus(that: MomentumAmount<M, D, T>) =
        MomentumAmount(amount = amount - that.amount,
                       unit = U2(mass, distance) to time)

    // endregion

    // region -> Natural

    @JvmName("divMomentum")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: MomentumAmount<M, D, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.distance to distance)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MomentumAmount<M, D, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

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

    // region -> MassFlux

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        MassFluxAmount(amount = amount / that.amount / convert(that.distance to distance),
                       unit = mass to time)

    operator fun div(that: DistanceAmount<D>) =
        MassFluxAmount(amount = amount / that.amount,
                       unit = mass to time)

    // endregion

    // region -> Distance

    @JvmName("divMassFlux")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFluxAmount<M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert((that.time to time)),
                       unit = distance)

    operator fun div(that: MassFluxAmount<M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion
}
