package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.dimensions.Volume
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class VolumeAmount<D : Unit<Distance>>(
        amount: Number,
        unit: D)
    : Amount<Volume>(amount = amount.toBigDecimal(),
                     unit = CompositeUnit(
                         domain = Volume::class,
                         units = mapOf(unit to 3)))
{
    val distance = unit

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Volume>) =
            VolumeAmount(amount = amount * unit.scale,
                         unit = METER)
    }

    // region -> Volume

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        VolumeAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                     unit = distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        VolumeAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                     unit = distance)

    @JvmName("plusVolume")
    operator fun <D : Unit<Distance>> plus(that: VolumeAmount<D>) =
        VolumeAmount(amount = amount + that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = distance)

    operator fun plus(that: VolumeAmount<D>) =
        VolumeAmount(amount = amount + that.amount,
                     unit = distance)

    @JvmName("minusVolume")
    operator fun <D : Unit<Distance>> minus(that: VolumeAmount<D>) =
        VolumeAmount(amount = amount - that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = distance)

    operator fun minus(that: VolumeAmount<D>) =
        VolumeAmount(amount = amount - that.amount,
                     unit = distance)

    // endregion

    // region -> Scalar

    @JvmName("divVolume")
    operator fun <D : Unit<Distance>> div(that: VolumeAmount<D>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: VolumeAmount<D>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Area

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.distance to distance),
                   unit = distance)

    operator fun div(that: DistanceAmount<D>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    @JvmName("timesSpacialFrequency")
    operator fun <D : Unit<Distance>> times(that: SpacialFrequencyAmount<D>) =
        AreaAmount(amount = amount * that.amount
                            / convert(that.distance to distance),
                   unit = distance)

    operator fun times(that: SpacialFrequencyAmount<D>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    // endregion

    // region -> Distance

    @JvmName("divArea")
    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun div(that: AreaAmount<D>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    @JvmName("timesFuelEfficiency")
    operator fun <D : Unit<Distance>> times(that: FuelEfficiencyAmount<D>) =
        DistanceAmount(amount = amount * that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun times(that: FuelEfficiencyAmount<D>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    // endregion

    // region -> Energy

    @JvmName("timesEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<D, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.distance to distance),
                     unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<D, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Mass

    @JvmName("timesMassDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<D, M>) =
        MassAmount(amount = amount * that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> times(that: MassDensityAmount<D, M>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    @JvmName("divSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<D, M>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> div(that: SpecificVolumeAmount<D, M>) =
        MassAmount(amount = amount / that.amount,
                   unit = that.mass)

    // endregion

    // region -> MolarVolume

    operator fun <N : Unit<Substance>> div(that: SubstanceAmount<N>) =
        MolarVolumeAmount(amount = amount / that.amount,
                          unit = distance to that.substance)

    // endregion

    // region -> Power

    @JvmName("timesPowerDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<D, M, T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.distance to distance),
                    unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<D, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> SpecificVolume

    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        SpecificVolumeAmount(amount = amount / that.amount,
                             unit = distance to that.mass)

    // endregion

    // region -> Substance

    @JvmName("divMolarVolume")
    operator fun <N : Unit<Substance>, D : Unit<Distance>> div(that: MolarVolumeAmount<N, D>) =
        SubstanceAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = that.substance)

    operator fun <N : Unit<Substance>> div(that: MolarVolumeAmount<N, D>) =
        SubstanceAmount(amount = amount / that.amount,
                        unit = that.substance)

    @JvmName("timesMolarity")
    operator fun <N : Unit<Substance>, D : Unit<Distance>> times(that: MolarityAmount<N, D>) =
        SubstanceAmount(amount = amount * that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = that.substance)

    operator fun <N : Unit<Substance>> times(that: MolarityAmount<N, D>) =
        SubstanceAmount(amount = amount * that.amount,
                        unit = that.substance)

    // endregion

    // region -> ThermalConductivity

    @JvmName("divThermalResistance")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalResistanceAmount<D, M, K, T>) =
        ThermalConductivityAmount(amount = amount / that.amount
                                           / convert(that.distance to distance,
                                                     that.distance to distance),
                                  unit = U2(distance, that.mass) to U2(that.temperature, that.time))

    operator fun <M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalResistanceAmount<D, M, K, T>) =
        ThermalConductivityAmount(amount = amount / that.amount,
                                  unit = U2(distance, that.mass) to U2(that.temperature, that.time))

    // endregion

    // region -> ThermalResistance

    @JvmName("divThermalConductivity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalConductivityAmount<D, M, K, T>) =
        ThermalResistanceAmount(amount = amount / that.amount
                                         / convert(that.distance to distance),
                                unit = U3(that.time, distance, that.temperature) to that.mass)

    operator fun <M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalConductivityAmount<D, M, K, T>) =
        ThermalResistanceAmount(amount = amount / that.amount,
                                unit = U3(that.time, distance, that.temperature) to that.mass)

    // endregion

    // region -> Time

    @JvmName("divVolumetricFlow")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<D, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: VolumetricFlowAmount<D, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    // endregion

    // region -> VolumetricFlow

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = distance to that.time)

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        VolumetricFlowAmount(amount = amount / that.amount,
                             unit = distance to that.time)

    // endregion
}
