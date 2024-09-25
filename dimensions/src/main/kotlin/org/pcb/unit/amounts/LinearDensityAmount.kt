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
import org.pcb.unit.dimensions.LinearDensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class LinearDensityAmount<L : Unit<Distance>, M : Unit<Mass>>(
        amount: Number,
        unit: Pair<M, L>)
    : Amount<LinearDensity>(amount = amount.toBigDecimal(),
                            unit = CompositeUnit(
                                domain = LinearDensity::class,
                                units = mapOf(unit.major to 1,
                                              unit.minor to -1)))
{
    val mass = unit.major
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<LinearDensity>) =
            LinearDensityAmount(amount = amount * unit.scale,
                                unit = kilo(GRAM) to METER)
    }

    // region -> LinearDensity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        LinearDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                            unit = mass to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        LinearDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                            unit = mass to distance)

    @JvmName("plusLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> plus(that: LinearDensityAmount<L, M>) =
        LinearDensityAmount(amount = amount + that.amount
                                     * convert(that.mass to mass)
                                     / convert(that.distance to distance),
                            unit = mass to distance)

    operator fun plus(that: LinearDensityAmount<L, M>) =
        LinearDensityAmount(amount = amount + that.amount,
                            unit = mass to distance)

    @JvmName("minusLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> minus(that: LinearDensityAmount<L, M>) =
        LinearDensityAmount(amount = amount - that.amount
                                     * convert(that.mass to mass)
                                     / convert(that.distance to distance),
                            unit = mass to distance)

    operator fun minus(that: LinearDensityAmount<L, M>) =
        LinearDensityAmount(amount = amount - that.amount,
                            unit = mass to distance)

    // endregion

    // region -> Scalar

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: LinearDensityAmount<L, M>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Action

    @JvmName("timesVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        ActionAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = U2(distance, mass) to that.time)

    operator fun <T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to that.time)

    // endregion

    // region -> AngularMomentum

    @JvmName("timesAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ActionAmount<L, M, T>) =
        AngularMomentumAmount(amount = amount * that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.mass to mass),
                              unit = U2(mass, distance) to that.time)

    operator fun <T : Unit<Time>> times(that: ActionAmount<L, M, T>) =
        AngularMomentumAmount(amount = amount * that.amount,
                              unit = U2(mass, distance) to that.time)

    // endregion

    // region -> Area

    @JvmName("divMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MassDensityAmount<L, M>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = distance)

    operator fun div(that: MassDensityAmount<L, M>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    @JvmName("timesSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<L, M>) =
        AreaAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance)
                            / convert(that.mass to mass),
                   unit = distance)

    operator fun times(that: SpecificVolumeAmount<L, M>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    // endregion

    // region -> AreaDensity

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        AreaDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance),
                          unit = mass to distance)

    operator fun div(that: DistanceAmount<L>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        AreaDensityAmount(amount = amount * that.amount
                                   / convert(that.distance to distance),
                          unit = mass to distance)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        AreaDensityAmount(amount = amount * that.amount,
                          unit = mass to distance)

    // endregion

    // region -> Distance

    @JvmName("divAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<L, M>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun div(that: AreaDensityAmount<L, M>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> Force

    @JvmName("timesSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance),
                    unit = U2(distance, mass) to that.time)

    operator fun <T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to that.time)

    // endregion

    // region -> Intensity

    @JvmName("timesJerk")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: JerkAmount<L, T>) =
        IntensityAmount(amount = amount * that.amount
                                 * convert(that.distance to distance),
                        unit = mass to that.time)

    operator fun <T : Unit<Time>> times(that: JerkAmount<L, T>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = mass to that.time)

    // endregion

    // region -> Mass

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.distance to distance),
                   unit = mass)

    operator fun times(that: DistanceAmount<L>) =
        MassAmount(amount = amount * that.amount,
                   unit = mass)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        MassAmount(amount = amount / that.amount
                            * convert(that.distance to distance),
                   unit = mass)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> MassDensity

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        MassDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance),
                          unit = mass to distance)

    operator fun div(that: AreaAmount<L>) =
        MassDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        MassDensityAmount(amount = amount * that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance),
                          unit = mass to distance)

    operator fun times(that: FuelEfficiencyAmount<L>) =
        MassDensityAmount(amount = amount * that.amount,
                          unit = mass to distance)

    // endregion

    // region -> MassFlowRate

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    * convert(that.distance to distance),
                           unit = mass to that.time)

    operator fun <T : Unit<Time>> div(that: PaceAmount<L, T>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to that.time)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.distance to distance),
                           unit = mass to that.time)

    operator fun <T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to that.time)

    // endregion

    // region -> MomentOfInertia

    @JvmName("timesVolume")
    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        MomentOfInertiaAmount(amount = amount * that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.distance to distance),
                              unit = U2(distance, mass))

    operator fun times(that: VolumeAmount<L>) =
        MomentOfInertiaAmount(amount = amount * that.amount,
                              unit = U2(distance, mass))

    // endregion

    // region -> Pace

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.mass to mass),
                   unit = that.time to distance)

    operator fun <T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = that.time to distance)

    // endregion

    // region -> RadiantExposure

    @JvmName("timesAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance),
                              unit = mass to that.time)

    operator fun <T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to that.time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass),
                               unit = NUMERIC to distance)

    operator fun div(that: MassAmount<M>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Time

    @JvmName("divViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    // endregion

    // region -> Viscosity

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        ViscosityAmount(amount = amount * that.amount,
                        unit = mass to U2(distance, that.time))

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ViscosityAmount(amount = amount / that.amount,
                        unit = mass to U2(distance, that.time))

    // endregion
}
