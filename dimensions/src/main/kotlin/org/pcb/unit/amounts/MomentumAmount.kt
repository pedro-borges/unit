package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Momentum
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MomentumAmount<D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<D, M>, T>)
    : Amount<Momentum>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           domain = Momentum::class,
                           units = mapOf(unit.major.first to 1,
                                         unit.major.second to 1,
                                         unit.minor to -1)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Momentum>) =
            MomentumAmount(amount = amount * unit.scale,
                           unit = U2(METER, kilo(GRAM)) to SECOND)
    }

    // region -> Momentum

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                       unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                       unit = U2(distance, mass) to time)

    @JvmName("plusMomentum")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: MomentumAmount<D, M, T>) =
        MomentumAmount(amount = amount + that.amount
                                * convert(that.distance to distance,
                                          that.mass to mass)
                                / convert(that.time to time),
                       unit = U2(distance, mass) to time)

    operator fun plus(that: MomentumAmount<D, M, T>) =
        MomentumAmount(amount = amount + that.amount,
                       unit = U2(distance, mass) to time)

    @JvmName("minusMomentum")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: MomentumAmount<D, M, T>) =
        MomentumAmount(amount = amount - that.amount
                                * convert(that.distance to distance,
                                          that.mass to mass)
                                / convert(that.time to time),
                       unit = U2(distance, mass) to time)

    operator fun minus(that: MomentumAmount<D, M, T>) =
        MomentumAmount(amount = amount - that.amount,
                       unit = U2(distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divMomentum")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.mass to mass)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MomentumAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Action

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        ActionAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun times(that: DistanceAmount<D>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> AngularMomentum

    @JvmName("timesMass")
    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        AngularMomentumAmount(amount = amount * that.amount
                                       * convert(that.mass to mass),
                              unit = U2(mass, distance) to time)

    operator fun times(that: MassAmount<M>) =
        AngularMomentumAmount(amount = amount * that.amount,
                              unit = U2(mass, distance) to time)

    // endregion

    // region -> AreaDensity

    @JvmName("divVolumetricFlow")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<D, T>) =
        AreaDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance)
                                   * convert(that.time to time),
                          unit = mass to distance)

    operator fun div(that: VolumetricFlowAmount<D, T>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    // endregion

    // region -> Distance

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.time to time),
                       unit = distance)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> Energy

    @JvmName("divPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<D, T>) =
        EnergyAmount(amount = amount / that.amount
                              / convert(that.time to time)
                              * convert(that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun div(that: PaceAmount<D, T>) =
        EnergyAmount(amount = amount / that.amount,
                     unit = U2(distance, mass) to time)

    @JvmName("timesSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance)
                              / convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun times(that: SpeedAmount<D, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Force

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun times(that: FrequencyAmount<T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount
                             / convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Mass

    @JvmName("timesPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<D, T>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.time to time)
                            / convert(that.distance to distance),
                   unit = mass)

    operator fun times(that: PaceAmount<D, T>) =
        MassAmount(amount = amount * that.amount,
                   unit = mass)

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

    // region -> MassFlowRate

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance),
                           unit = mass to time)

    operator fun div(that: DistanceAmount<D>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    // endregion

    // region -> Pace

    @JvmName("divEnergy")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyAmount<D, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun div(that: EnergyAmount<D, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    // endregion

    // region -> Power

    @JvmName("timesAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<D, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun times(that: AccelerationAmount<D, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Speed

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass),
                    unit = distance to time)

    operator fun div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> Time

    @JvmName("divForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<D, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: ForceAmount<D, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> VolumetricFlow

    @JvmName("divAreaDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<D, M>) =
        VolumetricFlowAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.distance to distance,
                                                that.distance to distance),
                             unit = distance to time)

    operator fun div(that: AreaDensityAmount<D, M>) =
        VolumetricFlowAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion
}
