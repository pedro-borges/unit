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
import org.pcb.unit.dimensions.EnergyDensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class EnergyDensityAmount<D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, U2<D, T>>)
    : Amount<EnergyDensity>(amount = amount.toBigDecimal(),
                            unit = CompositeUnit(
                                domain = EnergyDensity::class,
                                units = mapOf(unit.major to 1,
                                              unit.minor.first to -1,
                                              unit.minor.second to -2)))
{
    val mass = unit.major
    val distance = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<EnergyDensity>) =
            EnergyDensityAmount(amount = amount * unit.scale,
                                unit = kilo(GRAM) to U2(METER, SECOND))
    }

    // region -> EnergyDensity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        EnergyDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                            unit = mass to U2(distance, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        EnergyDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                            unit = mass to U2(distance, time))

    @JvmName("plusEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: EnergyDensityAmount<D, M, T>) =
        EnergyDensityAmount(amount = amount + that.amount
                                     * convert(that.mass to mass)
                                     / convert(that.distance to distance,
                                               that.time to time,
                                               that.time to time),
                            unit = mass to U2(distance, time))

    operator fun plus(that: EnergyDensityAmount<D, M, T>) =
        EnergyDensityAmount(amount = amount + that.amount,
                            unit = mass to U2(distance, time))

    @JvmName("minusEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: EnergyDensityAmount<D, M, T>) =
        EnergyDensityAmount(amount = amount - that.amount
                                     * convert(that.mass to mass)
                                     / convert(that.distance to distance,
                                               that.time to time,
                                               that.time to time),
                            unit = mass to U2(distance, time))

    operator fun minus(that: EnergyDensityAmount<D, M, T>) =
        EnergyDensityAmount(amount = amount - that.amount,
                            unit = mass to U2(distance, time))

    // endregion

    // region -> Scalar

    @JvmName("divEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: EnergyDensityAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divAreaDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<D, M>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.mass to mass)
                                    * convert(that.distance to distance,
                                              that.distance to distance),
                           unit = distance to time)

    operator fun div(that: AreaDensityAmount<D, M>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    // endregion

    // region -> AreaDensity

    @JvmName("divAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<D, T>) =
        AreaDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance)
                                   * convert(that.time to time,
                                             that.time to time),
                          unit = mass to distance)

    operator fun div(that: AccelerationAmount<D, T>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    // endregion

    // region -> Energy

    @JvmName("timesVolume")
    operator fun <D : Unit<Distance>> times(that: VolumeAmount<D>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun times(that: VolumeAmount<D>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> EnergyFluxDensity

    @JvmName("divPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<D, T>) =
        EnergyFluxDensityAmount(amount = amount / that.amount
                                         / convert(that.time to time)
                                         * convert(that.distance to distance),
                                unit = mass to time)

    operator fun div(that: PaceAmount<D, T>) =
        EnergyFluxDensityAmount(amount = amount / that.amount,
                                unit = mass to time)

    @JvmName("timesSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount
                                         * convert(that.distance to distance)
                                         / convert(that.time to time),
                                unit = mass to time)

    operator fun times(that: SpeedAmount<D, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount,
                                unit = mass to time)

    // endregion

    // region -> Force

    @JvmName("timesArea")
    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun times(that: AreaAmount<D>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("divFuelEfficiency")
    operator fun <D : Unit<Distance>> div(that: FuelEfficiencyAmount<D>) =
        ForceAmount(amount = amount / that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun div(that: FuelEfficiencyAmount<D>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<D, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.distance to distance,
                                                that.mass to mass)
                                      * convert(that.time to time,
                                                that.time to time),
                             unit = NUMERIC to distance)

    operator fun div(that: ForceAmount<D, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> MassDensity

    @JvmName("divSpecificEnergy")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<D, T>) =
        MassDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance)
                                   * convert(that.time to time,
                                             that.time to time),
                          unit = mass to distance)

    operator fun div(that: SpecificEnergyAmount<D, T>) =
        MassDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    // endregion

    // region -> MolarEnergy

    @JvmName("timesMolarVolume")
    operator fun <N : Unit<Substance>, D : Unit<Distance>> times(that: MolarVolumeAmount<N, D>) =
        MolarEnergyAmount(amount = amount * that.amount
                                   * convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = U2(distance, mass) to U2(that.amountOfSubstance, time))

    operator fun <N : Unit<Substance>> times(that: MolarVolumeAmount<N, D>) =
        MolarEnergyAmount(amount = amount * that.amount,
                          unit = U2(distance, mass) to U2(that.amountOfSubstance, time))

    @JvmName("divMolarity")
    operator fun <N : Unit<Substance>, D : Unit<Distance>> div(that: MolarityAmount<N, D>) =
        MolarEnergyAmount(amount = amount / that.amount
                                   * convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = U2(distance, mass) to U2(that.amountOfSubstance, time))

    operator fun <N : Unit<Substance>> div(that: MolarityAmount<N, D>) =
        MolarEnergyAmount(amount = amount / that.amount,
                          unit = U2(distance, mass) to U2(that.amountOfSubstance, time))

    // endregion

    // region -> Molarity

    @JvmName("divMolarEnergy")
    operator fun <N : Unit<Substance>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MolarEnergyAmount<N, D, M, T>) =
        MolarityAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance,
                                          that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = that.amountOfSubstance to distance)

    operator fun <N : Unit<Substance>> div(that: MolarEnergyAmount<N, D, M, T>) =
        MolarityAmount(amount = amount / that.amount,
                       unit = that.amountOfSubstance to distance)

    // endregion

    // region -> Pace

    @JvmName("divEnergyFluxDensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: EnergyFluxDensityAmount<M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun div(that: EnergyFluxDensityAmount<M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    // endregion

    // region -> Power

    @JvmName("timesVolumetricFlow")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<D, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance,
                                       that.distance to distance)
                             / convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun times(that: VolumetricFlowAmount<D, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> PowerDensity

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        PowerDensityAmount(amount = amount * that.amount
                                    / convert(that.time to time),
                           unit = mass to U2(distance, time))

    operator fun times(that: FrequencyAmount<T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = mass to U2(distance, time))

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        PowerDensityAmount(amount = amount / that.amount
                                    / convert(that.time to time),
                           unit = mass to U2(distance, time))

    operator fun div(that: TimeAmount<T>) =
        PowerDensityAmount(amount = amount / that.amount,
                           unit = mass to U2(distance, time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance),
                              unit = mass to time)

    operator fun times(that: DistanceAmount<D>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to time)

    @JvmName("divSpacialFrequency")
    operator fun <D : Unit<Distance>> div(that: SpacialFrequencyAmount<D>) =
        RadiantExposureAmount(amount = amount / that.amount
                                       * convert(that.distance to distance),
                              unit = mass to time)

    operator fun div(that: SpacialFrequencyAmount<D>) =
        RadiantExposureAmount(amount = amount / that.amount,
                              unit = mass to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: RadiantExposureAmount<M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass)
                                        * convert(that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to distance)

    operator fun div(that: RadiantExposureAmount<M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> SpecificEnergy

    @JvmName("divMassDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: MassDensityAmount<D, M>) =
        SpecificEnergyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance),
                             unit = distance to time)

    operator fun div(that: MassDensityAmount<D, M>) =
        SpecificEnergyAmount(amount = amount / that.amount,
                             unit = distance to time)

    @JvmName("timesSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<D, M>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.mass to mass),
                             unit = distance to time)

    operator fun times(that: SpecificVolumeAmount<D, M>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = distance to time)

    // endregion

    // region -> Time

    @JvmName("divPowerDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerDensityAmount<D, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: PowerDensityAmount<D, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}
