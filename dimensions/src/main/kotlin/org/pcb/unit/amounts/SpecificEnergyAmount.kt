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
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.SpecificEnergy
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SpecificEnergyAmount<L : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<L, T>)
    : Amount<SpecificEnergy>(amount = amount.toBigDecimal(),
                             unit = CompositeUnit(
                                 domain = SpecificEnergy::class,
                                 units = mapOf(unit.major to 2,
                                               unit.minor to -2)))
{
    val distance = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<SpecificEnergy>) =
            SpecificEnergyAmount(amount = amount * unit.scale,
                                 unit = METER to SECOND)
    }

    // region -> SpecificEnergy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpecificEnergyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                             unit = distance to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpecificEnergyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                             unit = distance to time)

    @JvmName("plusSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> plus(that: SpecificEnergyAmount<L, T>) =
        SpecificEnergyAmount(amount = amount + that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.time to time,
                                                that.time to time),
                             unit = distance to time)

    operator fun plus(that: SpecificEnergyAmount<L, T>) =
        SpecificEnergyAmount(amount = amount + that.amount,
                             unit = distance to time)

    @JvmName("minusSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> minus(that: SpecificEnergyAmount<L, T>) =
        SpecificEnergyAmount(amount = amount - that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.time to time,
                                                that.time to time),
                             unit = distance to time)

    operator fun minus(that: SpecificEnergyAmount<L, T>) =
        SpecificEnergyAmount(amount = amount - that.amount,
                             unit = distance to time)

    // endregion

    // region -> Scalar

    @JvmName("divSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: SpecificEnergyAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divJerk")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: JerkAmount<L, T>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.distance to distance)
                                * convert(that.time to time,
                                          that.time to time,
                                          that.time to time),
                       unit = U2(distance, time))

    operator fun div(that: JerkAmount<L, T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(distance, time))

    // endregion

    // region -> Acceleration

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.distance to distance),
                           unit = distance to time)

    operator fun div(that: DistanceAmount<L>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        AccelerationAmount(amount = amount * that.amount
                                    / convert(that.distance to distance),
                           unit = distance to time)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        AccelerationAmount(amount = amount * that.amount,
                           unit = distance to time)

    // endregion

    // region -> Distance

    @JvmName("divAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = distance)

    operator fun div(that: AccelerationAmount<L, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> Energy

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to time)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.distance to distance),
                            unit = that.mass to U2(distance, time))

    operator fun <M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(distance, time))

    @JvmName("divSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.distance to distance),
                            unit = that.mass to U2(distance, time))

    operator fun <M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = that.mass to U2(distance, time))

    // endregion

    // region -> Force

    @JvmName("timesLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.distance to distance),
                    unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Jerk

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        JerkAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.time to time),
                   unit = distance to time)

    operator fun div(that: AbsementAmount<L, T>) =
        JerkAmount(amount = amount / that.amount,
                   unit = distance to time)

    // endregion

    // region -> Molality

    @JvmName("divMolarEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> div(that: MolarEnergyAmount<L, M, N, T>) =
        MolalityAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = that.substance to that.mass)

    operator fun <M : Unit<Mass>, N : Unit<Substance>> div(that: MolarEnergyAmount<L, M, N, T>) =
        MolalityAmount(amount = amount / that.amount,
                       unit = that.substance to that.mass)

    // endregion

    // region -> MolarEnergy

    operator fun <M : Unit<Mass>, N : Unit<Substance>> div(that: MolalityAmount<M, N>) =
        MolarEnergyAmount(amount = amount / that.amount,
                          unit = U2(distance, that.mass) to U2(that.substance, time))

    operator fun <M : Unit<Mass>, N : Unit<Substance>> times(that: MolarMassAmount<M, N>) =
        MolarEnergyAmount(amount = amount * that.amount,
                          unit = U2(distance, that.mass) to U2(that.substance, time))

    // endregion

    // region -> Power

    @JvmName("timesMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.time to time),
                    unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: MassFlowRateAmount<M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to time)

    // endregion

    // region -> RadiantExposure

    @JvmName("timesAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       / convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to time)

    // endregion

    // region -> SpecificHeatCapacity

    operator fun <K : Unit<Temperature>> div(that: TemperatureAmount<K>) =
        SpecificHeatCapacityAmount(amount = amount / that.amount,
                                   unit = distance to U2(that.temperature, time))

    operator fun <K : Unit<Temperature>> times(that: ThermalExpansionCoefficientAmount<K>) =
        SpecificHeatCapacityAmount(amount = amount * that.amount,
                                   unit = distance to U2(that.temperature, time))

    // endregion

    // region -> SpecificVolume

    @JvmName("divEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<L, M, T>) =
        SpecificVolumeAmount(amount = amount / that.amount
                                      * convert(that.distance to distance,
                                                that.time to time,
                                                that.time to time),
                             unit = distance to that.mass)

    operator fun <M : Unit<Mass>> div(that: EnergyDensityAmount<L, M, T>) =
        SpecificVolumeAmount(amount = amount / that.amount,
                             unit = distance to that.mass)

    // endregion

    // region -> Speed

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        SpeedAmount(amount = amount * that.amount
                             * convert(that.time to time)
                             / convert(that.distance to distance),
                    unit = distance to time)

    operator fun times(that: PaceAmount<L, T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = distance to time)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.distance to distance)
                             * convert(that.time to time),
                    unit = distance to time)

    operator fun div(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> Temperature

    @JvmName("divSpecificHeatCapacity")
    operator fun <L : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> div(that: SpecificHeatCapacityAmount<L, K, T>) =
        TemperatureAmount(amount = amount / that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance)
                                   * convert(that.time to time,
                                             that.time to time),
                          unit = that.temperature)

    operator fun <K : Unit<Temperature>> div(that: SpecificHeatCapacityAmount<L, K, T>) =
        TemperatureAmount(amount = amount / that.amount,
                          unit = that.temperature)

    // endregion

    // region -> VolumetricFlow

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        VolumetricFlowAmount(amount = amount * that.amount
                                      * convert(that.distance to distance,
                                                that.time to time),
                             unit = distance to time)

    operator fun times(that: AbsementAmount<L, T>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = distance to time)

    // endregion

    // region -> Yank

    @JvmName("timesViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ViscosityAmount<L, M, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.distance to distance,
                                      that.time to time),
                   unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: ViscosityAmount<L, M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, that.mass) to time)

    // endregion
}
