package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Pace
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class PaceAmount<L : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<T, L>)
    : Amount<Pace>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Pace::class,
                       units = mapOf(unit.major to 1,
                                     unit.minor to -1)))
{
    val time = unit.major
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Pace>) =
            PaceAmount(amount = amount * unit.scale,
                       unit = SECOND to METER)
    }

    // region -> Pace

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PaceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                   unit = time to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PaceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                   unit = time to distance)

    @JvmName("plusPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> plus(that: PaceAmount<L, T>) =
        PaceAmount(amount = amount + that.amount
                            * convert(that.time to time)
                            / convert(that.distance to distance),
                   unit = time to distance)

    operator fun plus(that: PaceAmount<L, T>) =
        PaceAmount(amount = amount + that.amount,
                   unit = time to distance)

    @JvmName("minusPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> minus(that: PaceAmount<L, T>) =
        PaceAmount(amount = amount - that.amount
                            * convert(that.time to time)
                            / convert(that.distance to distance),
                   unit = time to distance)

    operator fun minus(that: PaceAmount<L, T>) =
        PaceAmount(amount = amount - that.amount,
                   unit = time to distance)

    // endregion

    // region -> Scalar

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.time to time)
                              * convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: PaceAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.distance to distance)
                              / convert(that.time to time),
                     unit = NUMERIC)

    operator fun times(that: SpeedAmount<L, T>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Area

    @JvmName("timesVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        AreaAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance)
                            / convert(that.time to time),
                   unit = distance)

    operator fun times(that: VolumetricFlowAmount<L, T>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesEnergyFluxDensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyFluxDensityAmount<M, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     / convert(that.time to time,
                                               that.time to time,
                                               that.time to time),
                            unit = that.mass to U2(distance, time))

    operator fun <M : Unit<Mass>> times(that: EnergyFluxDensityAmount<M, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(distance, time))

    // endregion

    // region -> Force

    @JvmName("timesPower")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerAmount<L, M, T>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: PowerAmount<L, M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("timesAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        FrequencyAmount(amount = amount * that.amount
                                 * convert(that.distance to distance)
                                 / convert(that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun times(that: AccelerationAmount<L, T>) =
        FrequencyAmount(amount = amount * that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Mass

    @JvmName("timesMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<L, M, T>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.distance to distance)
                            / convert(that.time to time),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> times(that: MomentumAmount<L, M, T>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<L, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.distance to distance)
                                    / convert(that.time to time,
                                              that.time to time),
                           unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: ForceAmount<L, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to time)

    // endregion

    // region -> Momentum

    @JvmName("timesEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<L, M, T>) =
        MomentumAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance)
                                / convert(that.time to time,
                                          that.time to time),
                       unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: EnergyAmount<L, M, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, that.mass) to time)

    // endregion

    // region -> RadiantExposure

    @JvmName("timesYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<L, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance)
                                       / convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: YankAmount<L, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        SpacialFrequencyAmount(amount = amount * that.amount
                                        / convert(that.time to time),
                               unit = NUMERIC to distance)

    operator fun times(that: FrequencyAmount<T>) =
        SpacialFrequencyAmount(amount = amount * that.amount,
                               unit = NUMERIC to distance)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.time to time),
                               unit = NUMERIC to distance)

    operator fun div(that: TimeAmount<T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Speed

    @JvmName("timesSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        SpeedAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = distance to time)

    operator fun times(that: SpecificEnergyAmount<L, T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = distance to time)

    // endregion

    // region -> Time

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        TimeAmount(amount = amount * that.amount
                            * convert(that.distance to distance),
                   unit = time)

    operator fun times(that: DistanceAmount<L>) =
        TimeAmount(amount = amount * that.amount,
                   unit = time)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        TimeAmount(amount = amount / that.amount
                            * convert(that.distance to distance),
                   unit = time)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}
