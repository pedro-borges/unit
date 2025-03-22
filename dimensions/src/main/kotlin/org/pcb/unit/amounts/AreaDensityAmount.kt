package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.AreaDensity
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AreaDensityAmount<L : Unit<Distance>, M : Unit<Mass>>(
        amount: Number,
        unit: Pair<M, L>)
    : Amount<AreaDensity>(amount = amount.toBigDecimal(),
                          unit = CompositeUnit(
                              domain = AreaDensity::class,
                              units = mapOf(unit.major to 1,
                                            unit.minor to -2)))
{
    val mass = unit.major
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<AreaDensity>) =
            AreaDensityAmount(amount = amount * unit.scale,
                              unit = kilo(GRAM) to METER)
    }

    // region -> Convert

    fun convertTo(unit: Unit<AreaDensity>) =
        AreaDensityAmount(amount = amount * convert(this.unit to unit),
                          unit = unit)

    // endregion

    // region -> AreaDensity

    override operator fun times(that: Number) =
        AreaDensityAmount(amount = amount * that.toBigDecimal(),
                          unit = mass to distance)

    override operator fun div(that: Number) =
        AreaDensityAmount(amount = amount / that.toBigDecimal(),
                          unit = mass to distance)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AreaDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                          unit = mass to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AreaDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                          unit = mass to distance)

    @JvmName("plusAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> plus(that: AreaDensityAmount<L, M>) =
        AreaDensityAmount(amount = amount + that.amount
                                   * convert(that.mass to mass)
                                   / convert(that.distance to distance,
                                             that.distance to distance),
                          unit = mass to distance)

    operator fun plus(that: AreaDensityAmount<L, M>) =
        AreaDensityAmount(amount = amount + that.amount,
                          unit = mass to distance)

    @JvmName("minusAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> minus(that: AreaDensityAmount<L, M>) =
        AreaDensityAmount(amount = amount - that.amount
                                   * convert(that.mass to mass)
                                   / convert(that.distance to distance,
                                             that.distance to distance),
                          unit = mass to distance)

    operator fun minus(that: AreaDensityAmount<L, M>) =
        AreaDensityAmount(amount = amount - that.amount,
                          unit = mass to distance)

    // endregion

    // region -> Scalar

    @JvmName("divAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<L, M>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: AreaDensityAmount<L, M>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Distance

    @JvmName("divMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MassDensityAmount<L, M>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun div(that: MassDensityAmount<L, M>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    @JvmName("timesSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<L, M>) =
        DistanceAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance)
                                / convert(that.mass to mass),
                       unit = distance)

    operator fun times(that: SpecificVolumeAmount<L, M>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.distance to distance),
                            unit = mass to U2(distance, that.time))

    operator fun <T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(distance, that.time))

    // endregion

    // region -> FuelEfficiency

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass),
                             unit = NUMERIC to distance)

    operator fun div(that: MassAmount<M>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> LinearDensity

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        LinearDensityAmount(amount = amount * that.amount
                                     * convert(that.distance to distance),
                            unit = mass to distance)

    operator fun times(that: DistanceAmount<L>) =
        LinearDensityAmount(amount = amount * that.amount,
                            unit = mass to distance)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        LinearDensityAmount(amount = amount / that.amount
                                     * convert(that.distance to distance),
                            unit = mass to distance)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        LinearDensityAmount(amount = amount / that.amount,
                            unit = mass to distance)

    // endregion

    // region -> Mass

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = mass)

    operator fun times(that: AreaAmount<L>) =
        MassAmount(amount = amount * that.amount,
                   unit = mass)

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        MassAmount(amount = amount / that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = mass)

    operator fun div(that: FuelEfficiencyAmount<L>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> MassDensity

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        MassDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance),
                          unit = mass to distance)

    operator fun div(that: DistanceAmount<L>) =
        MassDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        MassDensityAmount(amount = amount * that.amount
                                   / convert(that.distance to distance),
                          unit = mass to distance)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        MassDensityAmount(amount = amount * that.amount,
                          unit = mass to distance)

    // endregion

    // region -> Momentum

    @JvmName("timesVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        MomentumAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = U2(distance, mass) to that.time)

    operator fun <T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, mass) to that.time)

    // endregion

    // region -> Pace

    @JvmName("divViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance),
                   unit = that.time to distance)

    operator fun <T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = that.time to distance)

    // endregion

    // region -> PowerDensity

    @JvmName("timesJerk")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: JerkAmount<L, T>) =
        PowerDensityAmount(amount = amount * that.amount
                                    * convert(that.distance to distance),
                           unit = mass to U2(distance, that.time))

    operator fun <T : Unit<Time>> times(that: JerkAmount<L, T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = mass to U2(distance, that.time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = mass to that.time)

    operator fun <T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to that.time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass)
                                        * convert(that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun div(that: LinearDensityAmount<L, M>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Viscosity

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        ViscosityAmount(amount = amount / that.amount
                                 * convert(that.distance to distance),
                        unit = mass to U2(distance, that.time))

    operator fun <T : Unit<Time>> div(that: PaceAmount<L, T>) =
        ViscosityAmount(amount = amount / that.amount,
                        unit = mass to U2(distance, that.time))

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        ViscosityAmount(amount = amount * that.amount
                                 * convert(that.distance to distance),
                        unit = mass to U2(distance, that.time))

    operator fun <T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        ViscosityAmount(amount = amount * that.amount,
                        unit = mass to U2(distance, that.time))

    // endregion
}
