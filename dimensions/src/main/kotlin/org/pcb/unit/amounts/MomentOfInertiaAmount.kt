package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.MomentOfInertia
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MomentOfInertiaAmount<L : Unit<Distance>, M : Unit<Mass>>(
        amount: Number,
        unit: U2<L, M>)
    : Amount<MomentOfInertia>(amount = amount.toBigDecimal(),
                              unit = CompositeUnit(
                                  domain = MomentOfInertia::class,
                                  units = mapOf(unit.first to 2,
                                                unit.second to 1)))
{
    val distance = unit.first
    val mass = unit.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MomentOfInertia>) =
            MomentOfInertiaAmount(amount = amount * unit.scale,
                                  unit = U2(METER, kilo(GRAM)))
    }

    // region -> MomentOfInertia

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MomentOfInertiaAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                              unit = U2(distance, mass))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MomentOfInertiaAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                              unit = U2(distance, mass))

    @JvmName("plusMomentOfInertia")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> plus(that: MomentOfInertiaAmount<L, M>) =
        MomentOfInertiaAmount(amount = amount + that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.mass to mass),
                              unit = U2(distance, mass))

    operator fun plus(that: MomentOfInertiaAmount<L, M>) =
        MomentOfInertiaAmount(amount = amount + that.amount,
                              unit = U2(distance, mass))

    @JvmName("minusMomentOfInertia")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> minus(that: MomentOfInertiaAmount<L, M>) =
        MomentOfInertiaAmount(amount = amount - that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.mass to mass),
                              unit = U2(distance, mass))

    operator fun minus(that: MomentOfInertiaAmount<L, M>) =
        MomentOfInertiaAmount(amount = amount - that.amount,
                              unit = U2(distance, mass))

    // endregion

    // region -> Scalar

    @JvmName("divMomentOfInertia")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MomentOfInertiaAmount<L, M>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass),
                     unit = NUMERIC)

    operator fun div(that: MomentOfInertiaAmount<L, M>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.mass to mass),
                       unit = U2(distance, that.time))

    operator fun <T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(distance, that.time))

    // endregion

    // region -> Action

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to that.time)

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ActionAmount(amount = amount / that.amount,
                     unit = U2(distance, mass) to that.time)

    // endregion

    // region -> AngularMomentum

    @JvmName("timesViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ViscosityAmount<L, M, T>) =
        AngularMomentumAmount(amount = amount * that.amount
                                       * convert(that.mass to mass)
                                       / convert(that.distance to distance),
                              unit = U2(mass, distance) to that.time)

    operator fun <T : Unit<Time>> times(that: ViscosityAmount<L, M, T>) =
        AngularMomentumAmount(amount = amount * that.amount,
                              unit = U2(mass, distance) to that.time)

    // endregion

    // region -> Area

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass),
                   unit = distance)

    operator fun div(that: MassAmount<M>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> LinearDensity

    @JvmName("divVolume")
    operator fun <L : Unit<Distance>> div(that: VolumeAmount<L>) =
        LinearDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.distance to distance),
                            unit = mass to distance)

    operator fun div(that: VolumeAmount<L>) =
        LinearDensityAmount(amount = amount / that.amount,
                            unit = mass to distance)

    // endregion

    // region -> Mass

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance),
                   unit = mass)

    operator fun div(that: AreaAmount<L>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        MassAmount(amount = amount * that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance),
                   unit = mass)

    operator fun times(that: FuelEfficiencyAmount<L>) =
        MassAmount(amount = amount * that.amount,
                   unit = mass)

    // endregion

    // region -> Momentum

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        MomentumAmount(amount = amount / that.amount
                                / convert(that.distance to distance),
                       unit = U2(distance, mass) to that.time)

    operator fun <T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(distance, mass) to that.time)

    // endregion

    // region -> Time

    @JvmName("divAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ActionAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: ActionAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    // endregion

    // region -> Volume

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        VolumeAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance),
                     unit = distance)

    operator fun div(that: LinearDensityAmount<L, M>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = distance)

    // endregion
}
