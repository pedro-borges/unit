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
import org.pcb.unit.dimensions.MassFlowRate
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MassFlowRateAmount<M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, T>)
    : Amount<MassFlowRate>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               domain = MassFlowRate::class,
                               units = mapOf(unit.major to 1,
                                             unit.minor to -1)))
{
    val mass = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MassFlowRate>) =
            MassFlowRateAmount(amount = amount * unit.scale,
                               unit = kilo(GRAM) to SECOND)
    }

    // region -> MassFlowRate

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MassFlowRateAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = mass to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MassFlowRateAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = mass to time)

    @JvmName("plusMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> plus(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount + that.amount
                                    * convert(that.mass to mass)
                                    / convert(that.time to time),
                           unit = mass to time)

    operator fun plus(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount + that.amount,
                           unit = mass to time)

    @JvmName("minusMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> minus(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount - that.amount
                                    * convert(that.mass to mass)
                                    / convert(that.time to time),
                           unit = mass to time)

    operator fun minus(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount - that.amount,
                           unit = mass to time)

    // endregion

    // region -> Scalar

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = U2(that.distance, time))

    operator fun <L : Unit<Distance>> div(that: EnergyDensityAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(that.distance, time))

    // endregion

    // region -> Action

    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        ActionAmount(amount = amount / that.amount,
                     unit = U2(that.distance, mass) to time)

    // endregion

    // region -> ElectricCharge

    @JvmName("divMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.time to time,
                                                that.time to time),
                             unit = U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount,
                             unit = U2(that.electricCurrent, time))

    // endregion

    // region -> EnergyDensity

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     / convert(that.time to time),
                            unit = mass to U2(that.distance, time))

    operator fun <L : Unit<Distance>> div(that: AbsementAmount<L, T>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(that.distance, time))

    // endregion

    // region -> Force

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        ForceAmount(amount = amount / that.amount
                             / convert(that.time to time),
                    unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> div(that: PaceAmount<L, T>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(that.distance, mass) to time)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.time to time),
                    unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> times(that: SpeedAmount<L, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.mass to mass),
                        unit = NUMERIC to time)

    operator fun div(that: MassAmount<M>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ActionAmount<L, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.time to time),
                             unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> div(that: ActionAmount<L, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to that.distance)

    // endregion

    // region -> LinearDensity

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        LinearDensityAmount(amount = amount * that.amount
                                     * convert(that.time to time),
                            unit = mass to that.distance)

    operator fun <L : Unit<Distance>> times(that: PaceAmount<L, T>) =
        LinearDensityAmount(amount = amount * that.amount,
                            unit = mass to that.distance)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        LinearDensityAmount(amount = amount / that.amount
                                     * convert(that.time to time),
                            unit = mass to that.distance)

    operator fun <L : Unit<Distance>> div(that: SpeedAmount<L, T>) =
        LinearDensityAmount(amount = amount / that.amount,
                            unit = mass to that.distance)

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("divElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<I, T>) =
        MagneticFluxDensityAmount(amount = amount / that.amount
                                           / convert(that.time to time),
                                  unit = mass to U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricChargeAmount<I, T>) =
        MagneticFluxDensityAmount(amount = amount / that.amount,
                                  unit = mass to U2(that.electricCurrent, time))

    // endregion

    // region -> Mass

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        MassAmount(amount = amount / that.amount
                            * convert(that.time to time),
                   unit = mass)

    operator fun div(that: FrequencyAmount<T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.time to time),
                   unit = mass)

    operator fun times(that: TimeAmount<T>) =
        MassAmount(amount = amount * that.amount,
                   unit = mass)

    // endregion

    // region -> MassDensity

    @JvmName("divVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<L, T>) =
        MassDensityAmount(amount = amount / that.amount
                                   * convert(that.time to time),
                          unit = mass to that.distance)

    operator fun <L : Unit<Distance>> div(that: VolumetricFlowAmount<L, T>) =
        MassDensityAmount(amount = amount / that.amount,
                          unit = mass to that.distance)

    // endregion

    // region -> Momentum

    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(that.distance, mass) to time)

    // endregion

    // region -> Pace

    @JvmName("divForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time to that.distance)

    operator fun <L : Unit<Distance>> div(that: ForceAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to that.distance)

    // endregion

    // region -> Power

    @JvmName("timesSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> times(that: SpecificEnergyAmount<L, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(that.distance, mass) to time)

    // endregion

    // region -> RadiantExposure

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       / convert(that.time to time),
                              unit = mass to time)

    operator fun times(that: FrequencyAmount<T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        RadiantExposureAmount(amount = amount / that.amount
                                       / convert(that.time to time),
                              unit = mass to time)

    operator fun div(that: TimeAmount<T>) =
        RadiantExposureAmount(amount = amount / that.amount,
                              unit = mass to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass)
                                        * convert(that.time to time),
                               unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> div(that: MomentumAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to that.distance)

    // endregion

    // region -> Speed

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass),
                    unit = that.distance to time)

    operator fun <L : Unit<Distance>> div(that: LinearDensityAmount<L, M>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = that.distance to time)

    // endregion

    // region -> Time

    @JvmName("divRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: RadiantExposureAmount<M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: RadiantExposureAmount<M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> VolumetricFlow

    @JvmName("divMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MassDensityAmount<L, M>) =
        VolumetricFlowAmount(amount = amount / that.amount
                                      / convert(that.mass to mass),
                             unit = that.distance to time)

    operator fun <L : Unit<Distance>> div(that: MassDensityAmount<L, M>) =
        VolumetricFlowAmount(amount = amount / that.amount,
                             unit = that.distance to time)

    @JvmName("timesSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<L, M>) =
        VolumetricFlowAmount(amount = amount * that.amount
                                      / convert(that.mass to mass),
                             unit = that.distance to time)

    operator fun <L : Unit<Distance>> times(that: SpecificVolumeAmount<L, M>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = that.distance to time)

    // endregion

    // region -> Yank

    @JvmName("timesAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.time to time,
                                      that.time to time),
                   unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> times(that: AccelerationAmount<L, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(that.distance, mass) to time)

    // endregion
}
