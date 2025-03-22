package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.dimensions.VolumetricFlow
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class VolumetricFlowAmount<L : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<L, T>)
    : Amount<VolumetricFlow>(amount = amount.toBigDecimal(),
                             unit = CompositeUnit(
                                 domain = VolumetricFlow::class,
                                 units = mapOf(unit.major to 3,
                                               unit.minor to -1)))
{
    val distance = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<VolumetricFlow>) =
            VolumetricFlowAmount(amount = amount * unit.scale,
                                 unit = METER to SECOND)
    }

    // region -> Convert

    fun convertTo(unit: Unit<VolumetricFlow>) =
        VolumetricFlowAmount(amount = amount * convert(this.unit to unit),
                             unit = unit)

    // endregion

    // region -> VolumetricFlow

    override operator fun times(that: Number) =
        VolumetricFlowAmount(amount = amount * that.toBigDecimal(),
                             unit = distance to time)

    override operator fun div(that: Number) =
        VolumetricFlowAmount(amount = amount / that.toBigDecimal(),
                             unit = distance to time)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        VolumetricFlowAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                             unit = distance to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        VolumetricFlowAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                             unit = distance to time)

    @JvmName("plusVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> plus(that: VolumetricFlowAmount<L, T>) =
        VolumetricFlowAmount(amount = amount + that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.time to time),
                             unit = distance to time)

    operator fun plus(that: VolumetricFlowAmount<L, T>) =
        VolumetricFlowAmount(amount = amount + that.amount,
                             unit = distance to time)

    @JvmName("minusVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> minus(that: VolumetricFlowAmount<L, T>) =
        VolumetricFlowAmount(amount = amount - that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.time to time),
                             unit = distance to time)

    operator fun minus(that: VolumetricFlowAmount<L, T>) =
        VolumetricFlowAmount(amount = amount - that.amount,
                             unit = distance to time)

    // endregion

    // region -> Scalar

    @JvmName("divVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: VolumetricFlowAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<L, T>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = U2(distance, time))

    operator fun div(that: SpecificEnergyAmount<L, T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(distance, time))

    // endregion

    // region -> Action

    @JvmName("timesLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        ActionAmount(amount = amount * that.amount
                              / convert(that.distance to distance),
                     unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Area

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        AreaAmount(amount = amount * that.amount
                            * convert(that.time to time)
                            / convert(that.distance to distance),
                   unit = distance)

    operator fun times(that: PaceAmount<L, T>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time),
                   unit = distance)

    operator fun div(that: SpeedAmount<L, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> CatalyticEfficiency

    operator fun <N : Unit<Substance>> times(that: ReciprocalSubstanceAmount<N>) =
        CatalyticEfficiencyAmount(amount = amount * that.amount,
                                  unit = distance to U2(that.substance, time))

    operator fun <N : Unit<Substance>> div(that: SubstanceAmount<N>) =
        CatalyticEfficiencyAmount(amount = amount / that.amount,
                                  unit = distance to U2(that.substance, time))

    // endregion

    // region -> Energy

    @JvmName("timesViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ViscosityAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.distance to distance,
                                        that.time to time),
                     unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: ViscosityAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divVolume")
    operator fun <L : Unit<Distance>> div(that: VolumeAmount<L>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = NUMERIC to time)

    operator fun div(that: VolumeAmount<L>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance),
                           unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to time)

    @JvmName("divSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance),
                           unit = that.mass to time)

    operator fun <M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = that.mass to time)

    // endregion

    // region -> Momentum

    @JvmName("timesAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        MomentumAmount(amount = amount * that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance),
                       unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: AreaDensityAmount<L, M>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Power

    @JvmName("timesEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.distance to distance,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: EnergyDensityAmount<L, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to time)

    // endregion

    // region -> SpecificEnergy

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        SpecificEnergyAmount(amount = amount / that.amount
                                      / convert(that.distance to distance,
                                                that.time to time),
                             unit = distance to time)

    operator fun div(that: AbsementAmount<L, T>) =
        SpecificEnergyAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion

    // region -> SpecificVolume

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        SpecificVolumeAmount(amount = amount / that.amount
                                      * convert(that.time to time),
                             unit = distance to that.mass)

    operator fun <M : Unit<Mass>> div(that: MassFlowRateAmount<M, T>) =
        SpecificVolumeAmount(amount = amount / that.amount,
                             unit = distance to that.mass)

    // endregion

    // region -> Speed

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.distance to distance,
                                       that.distance to distance),
                    unit = distance to time)

    operator fun div(that: AreaAmount<L>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        SpeedAmount(amount = amount * that.amount
                             / convert(that.distance to distance,
                                       that.distance to distance),
                    unit = distance to time)

    operator fun times(that: FuelEfficiencyAmount<L>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = distance to time)

    // endregion

    // region -> Substance

    @JvmName("divCatalyticEfficiency")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> div(that: CatalyticEfficiencyAmount<L, N, T>) =
        SubstanceAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance)
                                 * convert(that.time to time),
                        unit = that.substance)

    operator fun <N : Unit<Substance>> div(that: CatalyticEfficiencyAmount<L, N, T>) =
        SubstanceAmount(amount = amount / that.amount,
                        unit = that.substance)

    // endregion

    // region -> Volume

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        VolumeAmount(amount = amount / that.amount
                              * convert(that.time to time),
                     unit = distance)

    operator fun div(that: FrequencyAmount<T>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = distance)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        VolumeAmount(amount = amount * that.amount
                              * convert(that.time to time),
                     unit = distance)

    operator fun times(that: TimeAmount<T>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    // endregion

    // region -> Yank

    @JvmName("timesEnergyDensityGradient")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityGradientAmount<L, M, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.time to time,
                                      that.time to time),
                   unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: EnergyDensityGradientAmount<L, M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, that.mass) to time)

    // endregion
}
