package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.RadiantExposure
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class RadiantExposureAmount<M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, T>)
    : Amount<RadiantExposure>(amount = amount.toBigDecimal(),
                              unit = CompositeUnit(
                                  domain = RadiantExposure::class,
                                  units = mapOf(unit.major to 1,
                                                unit.minor to -2)))
{
    val mass = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<RadiantExposure>) =
            RadiantExposureAmount(amount = amount * unit.scale,
                                  unit = kilo(GRAM) to SECOND)
    }

    // region -> RadiantExposure

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        RadiantExposureAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                              unit = mass to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        RadiantExposureAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                              unit = mass to time)

    @JvmName("plusRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> plus(that: RadiantExposureAmount<M, T>) =
        RadiantExposureAmount(amount = amount + that.amount
                                       * convert(that.mass to mass)
                                       / convert(that.time to time,
                                                 that.time to time),
                              unit = mass to time)

    operator fun plus(that: RadiantExposureAmount<M, T>) =
        RadiantExposureAmount(amount = amount + that.amount,
                              unit = mass to time)

    @JvmName("minusRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> minus(that: RadiantExposureAmount<M, T>) =
        RadiantExposureAmount(amount = amount - that.amount
                                       * convert(that.mass to mass)
                                       / convert(that.time to time,
                                                 that.time to time),
                              unit = mass to time)

    operator fun minus(that: RadiantExposureAmount<M, T>) =
        RadiantExposureAmount(amount = amount - that.amount,
                              unit = mass to time)

    // endregion

    // region -> Scalar

    @JvmName("divRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: RadiantExposureAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: RadiantExposureAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerDensityAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time,
                                          that.time to time),
                       unit = U2(that.distance, time))

    operator fun <L : Unit<Distance>> div(that: PowerDensityAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(that.distance, time))

    // endregion

    // region -> Acceleration

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.mass to mass),
                           unit = that.distance to time)

    operator fun <L : Unit<Distance>> div(that: LinearDensityAmount<L, M>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = that.distance to time)

    // endregion

    // region -> AreaDensity

    @JvmName("divSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<L, T>) =
        AreaDensityAmount(amount = amount / that.amount
                                   * convert(that.time to time,
                                             that.time to time),
                          unit = mass to that.distance)

    operator fun <L : Unit<Distance>> div(that: SpecificEnergyAmount<L, T>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = mass to that.distance)

    // endregion

    // region -> Distance

    @JvmName("divEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<L, M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = that.distance)

    operator fun <L : Unit<Distance>> div(that: EnergyDensityAmount<L, M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = that.distance)

    // endregion

    // region -> ElectricCurrent

    @JvmName("divMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.mass to mass)
                                       * convert(that.time to time,
                                                 that.time to time),
                              unit = that.electricCurrent)

    operator fun <I : Unit<ElectricCurrent>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> Energy

    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        EnergyAmount(amount = amount / that.amount,
                     unit = U2(that.distance, mass) to time)

    // endregion

    // region -> EnergyDensity

    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(that.distance, time))

    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(that.distance, time))

    // endregion

    // region -> Force

    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(that.distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.mass to mass)
                                 * convert(that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyAmount<L, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.time to time,
                                                that.time to time),
                             unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> div(that: EnergyAmount<L, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to that.distance)

    // endregion

    // region -> Intensity

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        IntensityAmount(amount = amount * that.amount
                                 / convert(that.time to time),
                        unit = mass to time)

    operator fun times(that: FrequencyAmount<T>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = mass to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        IntensityAmount(amount = amount / that.amount
                                 / convert(that.time to time),
                        unit = mass to time)

    operator fun div(that: TimeAmount<T>) =
        IntensityAmount(amount = amount / that.amount,
                        unit = mass to time)

    // endregion

    // region -> LinearDensity

    @JvmName("divAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        LinearDensityAmount(amount = amount / that.amount
                                     * convert(that.time to time,
                                               that.time to time),
                            unit = mass to that.distance)

    operator fun <L : Unit<Distance>> div(that: AccelerationAmount<L, T>) =
        LinearDensityAmount(amount = amount / that.amount,
                            unit = mass to that.distance)

    // endregion

    // region -> MagneticFluxDensity

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<I>) =
        MagneticFluxDensityAmount(amount = amount / that.amount,
                                  unit = mass to U2(that.electricCurrent, time))

    // endregion

    // region -> MassFlowRate

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    * convert(that.time to time),
                           unit = mass to time)

    operator fun div(that: FrequencyAmount<T>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.time to time),
                           unit = mass to time)

    operator fun times(that: TimeAmount<T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to time)

    // endregion

    // region -> Momentum

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        MomentumAmount(amount = amount * that.amount
                                * convert(that.time to time),
                       unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> times(that: AbsementAmount<L, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(that.distance, mass) to time)

    // endregion

    // region -> Pace

    @JvmName("divYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: YankAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time to that.distance)

    operator fun <L : Unit<Distance>> div(that: YankAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to that.distance)

    // endregion

    // region -> PowerDensity

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        PowerDensityAmount(amount = amount / that.amount
                                    / convert(that.time to time),
                           unit = mass to U2(that.distance, time))

    operator fun <L : Unit<Distance>> div(that: AbsementAmount<L, T>) =
        PowerDensityAmount(amount = amount / that.amount,
                           unit = mass to U2(that.distance, time))

    // endregion

    // region -> SpacialFrequency

    @JvmName("divForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass)
                                        * convert(that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> div(that: ForceAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to that.distance)

    // endregion

    // region -> SpecificEnergy

    @JvmName("divAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<L, M>) =
        SpecificEnergyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass),
                             unit = that.distance to time)

    operator fun <L : Unit<Distance>> div(that: AreaDensityAmount<L, M>) =
        SpecificEnergyAmount(amount = amount / that.amount,
                             unit = that.distance to time)

    // endregion

    // region -> Time

    @JvmName("divIntensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: IntensityAmount<M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: IntensityAmount<M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> Yank

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        YankAmount(amount = amount / that.amount
                            / convert(that.time to time),
                   unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> div(that: PaceAmount<L, T>) =
        YankAmount(amount = amount / that.amount,
                   unit = U2(that.distance, mass) to time)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.time to time),
                   unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> times(that: SpeedAmount<L, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(that.distance, mass) to time)

    // endregion
}
