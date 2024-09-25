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
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Energy
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class EnergyAmount<L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, T>)
    : Amount<Energy>(amount = amount.toBigDecimal(),
                     unit = CompositeUnit(
                         domain = Energy::class,
                         units = mapOf(unit.major.first to 2,
                                       unit.major.second to 1,
                                       unit.minor to -2)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Energy>) =
            EnergyAmount(amount = amount * unit.scale,
                         unit = U2(METER, kilo(GRAM)) to SECOND)
    }

    // region -> Energy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                     unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                     unit = U2(distance, mass) to time)

    @JvmName("plusEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: EnergyAmount<L, M, T>) =
        EnergyAmount(amount = amount + that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              / convert(that.time to time,
                                        that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun plus(that: EnergyAmount<L, M, T>) =
        EnergyAmount(amount = amount + that.amount,
                     unit = U2(distance, mass) to time)

    @JvmName("minusEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: EnergyAmount<L, M, T>) =
        EnergyAmount(amount = amount - that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              / convert(that.time to time,
                                        that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun minus(that: EnergyAmount<L, M, T>) =
        EnergyAmount(amount = amount - that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: EnergyAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: YankAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time,
                                          that.time to time),
                       unit = U2(distance, time))

    operator fun div(that: YankAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(distance, time))

    // endregion

    // region -> Action

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        ActionAmount(amount = amount / that.amount
                              * convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun div(that: FrequencyAmount<T>) =
        ActionAmount(amount = amount / that.amount,
                     unit = U2(distance, mass) to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        ActionAmount(amount = amount * that.amount
                              * convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun times(that: TimeAmount<T>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Area

    @JvmName("divRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: RadiantExposureAmount<M, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = distance)

    operator fun div(that: RadiantExposureAmount<M, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> Distance

    @JvmName("divForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<L, M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = distance)

    operator fun div(that: ForceAmount<L, M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricCharge

    @JvmName("divElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      / convert(that.distance to distance,
                                                that.distance to distance,
                                                that.mass to mass)
                                      * convert(that.time to time,
                                                that.time to time,
                                                that.time to time),
                             unit = U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount,
                             unit = U2(that.electricCurrent, time))

    // endregion

    // region -> ElectricCurrent

    @JvmName("divMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxAmount<L, I, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.mass to mass)
                                       * convert(that.time to time,
                                                 that.time to time),
                              unit = that.electricCurrent)

    operator fun <I : Unit<ElectricCurrent>> div(that: MagneticFluxAmount<L, I, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricTension

    @JvmName("divElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<I, T>) =
        ElectricTensionAmount(amount = amount / that.amount
                                       / convert(that.time to time),
                              unit = U2(distance, mass) to U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricChargeAmount<I, T>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(distance, mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> EnergyDensity

    @JvmName("divVolume")
    operator fun <L : Unit<Distance>> div(that: VolumeAmount<L>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.distance to distance),
                            unit = mass to U2(distance, time))

    operator fun div(that: VolumeAmount<L>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(distance, time))

    // endregion

    // region -> Force

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        ForceAmount(amount = amount / that.amount
                             / convert(that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun div(that: DistanceAmount<L>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ActionAmount<L, M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.mass to mass)
                                 * convert(that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: ActionAmount<L, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> HeatCapacity

    operator fun <K : Unit<Temperature>> div(that: TemperatureAmount<K>) =
        HeatCapacityAmount(amount = amount / that.amount,
                           unit = U2(distance, mass) to U2(that.temperature, time))

    operator fun <K : Unit<Temperature>> times(that: ThermalExpansionCoefficientAmount<K>) =
        HeatCapacityAmount(amount = amount * that.amount,
                           unit = U2(distance, mass) to U2(that.temperature, time))

    // endregion

    // region -> MagneticFlux

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<I>) =
        MagneticFluxAmount(amount = amount / that.amount,
                           unit = U2(distance, mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> Mass

    @JvmName("divSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<L, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = mass)

    operator fun div(that: SpecificEnergyAmount<L, T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> MolarEnergy

    operator fun <N : Unit<Substance>> div(that: SubstanceAmount<N>) =
        MolarEnergyAmount(amount = amount / that.amount,
                          unit = U2(distance, mass) to U2(that.substance, time))

    // endregion

    // region -> Momentum

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        MomentumAmount(amount = amount * that.amount
                                * convert(that.time to time)
                                / convert(that.distance to distance),
                       unit = U2(distance, mass) to time)

    operator fun times(that: PaceAmount<L, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, mass) to time)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        MomentumAmount(amount = amount / that.amount
                                / convert(that.distance to distance)
                                * convert(that.time to time),
                       unit = U2(distance, mass) to time)

    operator fun div(that: SpeedAmount<L, T>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(distance, mass) to time)

    // endregion

    // region -> Power

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun times(that: FrequencyAmount<T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        PowerAmount(amount = amount / that.amount
                             / convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun div(that: TimeAmount<T>) =
        PowerAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> RadiantExposure

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        RadiantExposureAmount(amount = amount / that.amount
                                       / convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = mass to time)

    operator fun div(that: AreaAmount<L>) =
        RadiantExposureAmount(amount = amount / that.amount,
                              unit = mass to time)

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       / convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = mass to time)

    operator fun times(that: FuelEfficiencyAmount<L>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to time)

    // endregion

    // region -> SpecificEnergy

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        SpecificEnergyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass),
                             unit = distance to time)

    operator fun div(that: MassAmount<M>) =
        SpecificEnergyAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion

    // region -> Speed

    @JvmName("divMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.distance to distance,
                                       that.mass to mass)
                             * convert(that.time to time),
                    unit = distance to time)

    operator fun div(that: MomentumAmount<L, M, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> Substance

    @JvmName("divMolarEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> div(that: MolarEnergyAmount<L, M, N, T>) =
        SubstanceAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.mass to mass)
                                 * convert(that.time to time,
                                           that.time to time),
                        unit = that.substance)

    operator fun <N : Unit<Substance>> div(that: MolarEnergyAmount<L, M, N, T>) =
        SubstanceAmount(amount = amount / that.amount,
                        unit = that.substance)

    // endregion

    // region -> Temperature

    @JvmName("divHeatCapacity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: HeatCapacityAmount<L, M, K, T>) =
        TemperatureAmount(amount = amount / that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.mass to mass)
                                   * convert(that.time to time,
                                             that.time to time),
                          unit = that.temperature)

    operator fun <K : Unit<Temperature>> div(that: HeatCapacityAmount<L, M, K, T>) =
        TemperatureAmount(amount = amount / that.amount,
                          unit = that.temperature)

    // endregion

    // region -> Time

    @JvmName("divPower")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: PowerAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> Volume

    @JvmName("divEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<L, M, T>) =
        VolumeAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.time to time,
                                        that.time to time),
                     unit = distance)

    operator fun div(that: EnergyDensityAmount<L, M, T>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = distance)

    // endregion

    // region -> Yank

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        YankAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.time to time),
                   unit = U2(distance, mass) to time)

    operator fun div(that: AbsementAmount<L, T>) =
        YankAmount(amount = amount / that.amount,
                   unit = U2(distance, mass) to time)

    // endregion
}
