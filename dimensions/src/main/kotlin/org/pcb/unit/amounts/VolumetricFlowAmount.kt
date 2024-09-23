package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.AmountOfSubstance
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.dimensions.VolumetricFlow
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class VolumetricFlowAmount<D : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<D, T>)
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

    // region -> VolumetricFlow

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        VolumetricFlowAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                             unit = distance to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        VolumetricFlowAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                             unit = distance to time)

    @JvmName("plusVolumetricFlow")
    operator fun <D : Unit<Distance>, T : Unit<Time>> plus(that: VolumetricFlowAmount<D, T>) =
        VolumetricFlowAmount(amount = amount + that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.time to time),
                             unit = distance to time)

    operator fun plus(that: VolumetricFlowAmount<D, T>) =
        VolumetricFlowAmount(amount = amount + that.amount,
                             unit = distance to time)

    @JvmName("minusVolumetricFlow")
    operator fun <D : Unit<Distance>, T : Unit<Time>> minus(that: VolumetricFlowAmount<D, T>) =
        VolumetricFlowAmount(amount = amount - that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.time to time),
                             unit = distance to time)

    operator fun minus(that: VolumetricFlowAmount<D, T>) =
        VolumetricFlowAmount(amount = amount - that.amount,
                             unit = distance to time)

    // endregion

    // region -> Scalar

    @JvmName("divVolumetricFlow")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<D, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: VolumetricFlowAmount<D, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> AmountOfSubstance

    @JvmName("divCatalyticEfficiency")
    operator fun <N : Unit<AmountOfSubstance>, D : Unit<Distance>, T : Unit<Time>> div(that: CatalyticEfficiencyAmount<N, D, T>) =
        AmountOfSubstanceAmount(amount = amount / that.amount
                                         / convert(that.distance to distance,
                                                   that.distance to distance,
                                                   that.distance to distance)
                                         * convert(that.time to time),
                                unit = that.amountOfSubstance)

    operator fun <N : Unit<AmountOfSubstance>> div(that: CatalyticEfficiencyAmount<N, D, T>) =
        AmountOfSubstanceAmount(amount = amount / that.amount,
                                unit = that.amountOfSubstance)

    // endregion

    // region -> Area

    @JvmName("timesPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<D, T>) =
        AreaAmount(amount = amount * that.amount
                            * convert(that.time to time)
                            / convert(that.distance to distance),
                   unit = distance)

    operator fun times(that: PaceAmount<D, T>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time),
                   unit = distance)

    operator fun div(that: SpeedAmount<D, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> CatalyticEfficiency

    operator fun <N : Unit<AmountOfSubstance>> div(that: AmountOfSubstanceAmount<N>) =
        CatalyticEfficiencyAmount(amount = amount / that.amount,
                                  unit = distance to U2(that.amountOfSubstance, time))

    // endregion

    // region -> Frequency

    @JvmName("divVolume")
    operator fun <D : Unit<Distance>> div(that: VolumeAmount<D>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = NUMERIC to time)

    operator fun div(that: VolumeAmount<D>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesMassDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<D, M>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance),
                           unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: MassDensityAmount<D, M>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to time)

    @JvmName("divSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<D, M>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance),
                           unit = that.mass to time)

    operator fun <M : Unit<Mass>> div(that: SpecificVolumeAmount<D, M>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = that.mass to time)

    // endregion

    // region -> Momentum

    @JvmName("timesAreaDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: AreaDensityAmount<D, M>) =
        MomentumAmount(amount = amount * that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance),
                       unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: AreaDensityAmount<D, M>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Power

    @JvmName("timesEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<D, M, T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.distance to distance,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: EnergyDensityAmount<D, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to time)

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
    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.distance to distance,
                                       that.distance to distance),
                    unit = distance to time)

    operator fun div(that: AreaAmount<D>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    @JvmName("timesFuelEfficiency")
    operator fun <D : Unit<Distance>> times(that: FuelEfficiencyAmount<D>) =
        SpeedAmount(amount = amount * that.amount
                             / convert(that.distance to distance,
                                       that.distance to distance),
                    unit = distance to time)

    operator fun times(that: FuelEfficiencyAmount<D>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = distance to time)

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
}
