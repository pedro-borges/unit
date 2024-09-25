package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.dimensions.Volume
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class VolumeAmount<L : Unit<Distance>>(
        amount: Number,
        unit: L)
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
    operator fun <L : Unit<Distance>> plus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount + that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = distance)

    operator fun plus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount + that.amount,
                     unit = distance)

    @JvmName("minusVolume")
    operator fun <L : Unit<Distance>> minus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount - that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = distance)

    operator fun minus(that: VolumeAmount<L>) =
        VolumeAmount(amount = amount - that.amount,
                     unit = distance)

    // endregion

    // region -> Scalar

    @JvmName("divVolume")
    operator fun <L : Unit<Distance>> div(that: VolumeAmount<L>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: VolumeAmount<L>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Action

    @JvmName("timesViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ViscosityAmount<L, M, T>) =
        ActionAmount(amount = amount * that.amount
                              / convert(that.distance to distance),
                     unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ViscosityAmount<L, M, T>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Area

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.distance to distance),
                   unit = distance)

    operator fun div(that: DistanceAmount<L>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        AreaAmount(amount = amount * that.amount
                            / convert(that.distance to distance),
                   unit = distance)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    // endregion

    // region -> Distance

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun div(that: AreaAmount<L>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        DistanceAmount(amount = amount * that.amount
                                / convert(that.distance to distance,
                                          that.distance to distance),
                       unit = distance)

    operator fun times(that: FuelEfficiencyAmount<L>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricDipoleMoment

    @JvmName("timesElectricDisplacementField")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricDisplacementFieldAmount<L, I, T>) =
        ElectricDipoleMomentAmount(amount = amount * that.amount
                                            / convert(that.distance to distance,
                                                      that.distance to distance),
                                   unit = U3(distance, that.electricCurrent, that.time))

    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricDisplacementFieldAmount<L, I, T>) =
        ElectricDipoleMomentAmount(amount = amount * that.amount,
                                   unit = U3(distance, that.electricCurrent, that.time))

    // endregion

    // region -> Energy

    @JvmName("timesEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.distance to distance),
                     unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> Force

    @JvmName("timesEnergyDensityGradient")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityGradientAmount<L, M, T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.distance to distance,
                                       that.distance to distance),
                    unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityGradientAmount<L, M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> MagneticDipoleMoment

    @JvmName("timesMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> times(that: MagneticFieldStrengthAmount<L, I>) =
        MagneticDipoleMomentAmount(amount = amount * that.amount
                                            / convert(that.distance to distance),
                                   unit = U2(distance, that.electricCurrent))

    operator fun <I : Unit<ElectricCurrent>> times(that: MagneticFieldStrengthAmount<L, I>) =
        MagneticDipoleMomentAmount(amount = amount * that.amount,
                                   unit = U2(distance, that.electricCurrent))

    // endregion

    // region -> Mass

    @JvmName("timesMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        MassAmount(amount = amount * that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> times(that: MassDensityAmount<L, M>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    @JvmName("divSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> div(that: SpecificVolumeAmount<L, M>) =
        MassAmount(amount = amount / that.amount,
                   unit = that.mass)

    // endregion

    // region -> MolarVolume

    operator fun <N : Unit<Substance>> div(that: SubstanceAmount<N>) =
        MolarVolumeAmount(amount = amount / that.amount,
                          unit = distance to that.substance)

    // endregion

    // region -> MomentOfInertia

    @JvmName("timesLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        MomentOfInertiaAmount(amount = amount * that.amount
                                       / convert(that.distance to distance),
                              unit = U2(distance, that.mass))

    operator fun <M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        MomentOfInertiaAmount(amount = amount * that.amount,
                              unit = U2(distance, that.mass))

    // endregion

    // region -> Power

    @JvmName("timesPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<L, M, T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.distance to distance),
                    unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<L, M, T>) =
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
    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarVolumeAmount<L, N>) =
        SubstanceAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = that.substance)

    operator fun <N : Unit<Substance>> div(that: MolarVolumeAmount<L, N>) =
        SubstanceAmount(amount = amount / that.amount,
                        unit = that.substance)

    @JvmName("timesMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarityAmount<L, N>) =
        SubstanceAmount(amount = amount * that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = that.substance)

    operator fun <N : Unit<Substance>> times(that: MolarityAmount<L, N>) =
        SubstanceAmount(amount = amount * that.amount,
                        unit = that.substance)

    // endregion

    // region -> ThermalConductivity

    @JvmName("divThermalResistance")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalResistanceAmount<L, M, K, T>) =
        ThermalConductivityAmount(amount = amount / that.amount
                                           / convert(that.distance to distance,
                                                     that.distance to distance),
                                  unit = U2(distance, that.mass) to U2(that.temperature, that.time))

    operator fun <M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalResistanceAmount<L, M, K, T>) =
        ThermalConductivityAmount(amount = amount / that.amount,
                                  unit = U2(distance, that.mass) to U2(that.temperature, that.time))

    // endregion

    // region -> ThermalResistance

    @JvmName("divThermalConductivity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalConductivityAmount<L, M, K, T>) =
        ThermalResistanceAmount(amount = amount / that.amount
                                         / convert(that.distance to distance),
                                unit = U3(that.time, distance, that.temperature) to that.mass)

    operator fun <M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalConductivityAmount<L, M, K, T>) =
        ThermalResistanceAmount(amount = amount / that.amount,
                                unit = U3(that.time, distance, that.temperature) to that.mass)

    // endregion

    // region -> Time

    @JvmName("divVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<L, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: VolumetricFlowAmount<L, T>) =
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
