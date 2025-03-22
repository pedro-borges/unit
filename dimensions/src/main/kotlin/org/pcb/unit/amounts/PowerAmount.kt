package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Power
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class PowerAmount<L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, T>)
    : Amount<Power>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        domain = Power::class,
                        units = mapOf(unit.major.first to 2,
                                      unit.major.second to 1,
                                      unit.minor to -3)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Power>) =
            PowerAmount(amount = amount * unit.scale,
                        unit = U2(METER, kilo(GRAM)) to SECOND)
    }

    // region -> Convert

    fun convertTo(unit: Unit<Power>) =
        PowerAmount(amount = amount * convert(this.unit to unit),
                    unit = unit)

    // endregion

    // region -> Power

    override operator fun times(that: Number) =
        PowerAmount(amount = amount * that.toBigDecimal(),
                    unit = U2(distance, mass) to time)

    override operator fun div(that: Number) =
        PowerAmount(amount = amount / that.toBigDecimal(),
                    unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PowerAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                    unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PowerAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                    unit = U2(distance, mass) to time)

    @JvmName("plusPower")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: PowerAmount<L, M, T>) =
        PowerAmount(amount = amount + that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance,
                                       that.mass to mass)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun plus(that: PowerAmount<L, M, T>) =
        PowerAmount(amount = amount + that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("minusPower")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: PowerAmount<L, M, T>) =
        PowerAmount(amount = amount - that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance,
                                       that.mass to mass)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun minus(that: PowerAmount<L, M, T>) =
        PowerAmount(amount = amount - that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divPower")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: PowerAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.distance to distance,
                                              that.mass to mass)
                                    * convert(that.time to time),
                           unit = distance to time)

    operator fun div(that: MomentumAmount<L, M, T>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    // endregion

    // region -> Area

    @JvmName("divIntensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: IntensityAmount<M, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = distance)

    operator fun div(that: IntensityAmount<M, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> Distance

    @JvmName("divYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: YankAmount<L, M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time,
                                          that.time to time),
                       unit = distance)

    operator fun div(that: YankAmount<L, M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricCurrent

    @JvmName("divElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.mass to mass)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = that.electricCurrent)

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricTension

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<I>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(distance, mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        EnergyAmount(amount = amount / that.amount
                              * convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun div(that: FrequencyAmount<T>) =
        EnergyAmount(amount = amount / that.amount,
                     unit = U2(distance, mass) to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun times(that: TimeAmount<T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> EnergyDensity

    @JvmName("divVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<L, T>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.distance to distance)
                                     * convert(that.time to time),
                            unit = mass to U2(distance, time))

    operator fun div(that: VolumetricFlowAmount<L, T>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(distance, time))

    // endregion

    // region -> Force

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.time to time)
                             / convert(that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun times(that: PaceAmount<L, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        ForceAmount(amount = amount / that.amount
                             / convert(that.distance to distance)
                             * convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun div(that: SpeedAmount<L, T>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyAmount<L, M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.mass to mass)
                                 * convert(that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: EnergyAmount<L, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Intensity

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        IntensityAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance),
                        unit = mass to time)

    operator fun div(that: AreaAmount<L>) =
        IntensityAmount(amount = amount / that.amount,
                        unit = mass to time)

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        IntensityAmount(amount = amount * that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance),
                        unit = mass to time)

    operator fun times(that: FuelEfficiencyAmount<L>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = mass to time)

    // endregion

    // region -> MassFlowRate

    @JvmName("divSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<L, T>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance)
                                    * convert(that.time to time,
                                              that.time to time),
                           unit = mass to time)

    operator fun div(that: SpecificEnergyAmount<L, T>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    // endregion

    // region -> Momentum

    @JvmName("divAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        MomentumAmount(amount = amount / that.amount
                                / convert(that.distance to distance)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = U2(distance, mass) to time)

    operator fun div(that: AccelerationAmount<L, T>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(distance, mass) to time)

    // endregion

    // region -> PowerDensity

    @JvmName("divVolume")
    operator fun <L : Unit<Distance>> div(that: VolumeAmount<L>) =
        PowerDensityAmount(amount = amount / that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance),
                           unit = mass to U2(distance, time))

    operator fun div(that: VolumeAmount<L>) =
        PowerDensityAmount(amount = amount / that.amount,
                           unit = mass to U2(distance, time))

    // endregion

    // region -> RadiantIntensity

    operator fun <BAC : Unit<Angle>> div(that: SolidAngleAmount<BAC>) =
        RadiantIntensityAmount(amount = amount / that.amount,
                               unit = U2(distance, mass) to U2(that.angle, time))

    // endregion

    // region -> SolidAngle

    @JvmName("divRadiantIntensity")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: RadiantIntensityAmount<BAC, L, M, T>) =
        SolidAngleAmount(amount = amount / that.amount
                                  / convert(that.distance to distance,
                                            that.distance to distance,
                                            that.mass to mass)
                                  * convert(that.time to time,
                                            that.time to time,
                                            that.time to time),
                         unit = that.angle)

    operator fun <BAC : Unit<Angle>> div(that: RadiantIntensityAmount<BAC, L, M, T>) =
        SolidAngleAmount(amount = amount / that.amount,
                         unit = that.angle)

    // endregion

    // region -> SpecificEnergy

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        SpecificEnergyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.time to time),
                             unit = distance to time)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        SpecificEnergyAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion

    // region -> Speed

    @JvmName("divForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<L, M, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.distance to distance,
                                       that.mass to mass)
                             * convert(that.time to time,
                                       that.time to time),
                    unit = distance to time)

    operator fun div(that: ForceAmount<L, M, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> Volume

    @JvmName("divPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerDensityAmount<L, M, T>) =
        VolumeAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = distance)

    operator fun div(that: PowerDensityAmount<L, M, T>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = distance)

    // endregion

    // region -> VolumetricFlow

    @JvmName("divEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<L, M, T>) =
        VolumetricFlowAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.distance to distance,
                                                that.time to time,
                                                that.time to time),
                             unit = distance to time)

    operator fun div(that: EnergyDensityAmount<L, M, T>) =
        VolumetricFlowAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion

    // region -> Yank

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        YankAmount(amount = amount / that.amount
                            / convert(that.distance to distance),
                   unit = U2(distance, mass) to time)

    operator fun div(that: DistanceAmount<L>) =
        YankAmount(amount = amount / that.amount,
                   unit = U2(distance, mass) to time)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.distance to distance),
                   unit = U2(distance, mass) to time)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, mass) to time)

    // endregion
}
