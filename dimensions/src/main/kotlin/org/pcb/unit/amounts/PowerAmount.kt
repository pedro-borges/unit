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

open class PowerAmount<D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<D, M>, T>)
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

    // region -> Power

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PowerAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                    unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PowerAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                    unit = U2(distance, mass) to time)

    @JvmName("plusPower")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: PowerAmount<D, M, T>) =
        PowerAmount(amount = amount + that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance,
                                       that.mass to mass)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun plus(that: PowerAmount<D, M, T>) =
        PowerAmount(amount = amount + that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("minusPower")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: PowerAmount<D, M, T>) =
        PowerAmount(amount = amount - that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance,
                                       that.mass to mass)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun minus(that: PowerAmount<D, M, T>) =
        PowerAmount(amount = amount - that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divPower")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: PowerAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divMomentum")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<D, M, T>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.distance to distance,
                                              that.mass to mass)
                                    * convert(that.time to time),
                           unit = distance to time)

    operator fun div(that: MomentumAmount<D, M, T>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    // endregion

    // region -> Area

    @JvmName("divEnergyFluxDensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: EnergyFluxDensityAmount<M, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = distance)

    operator fun div(that: EnergyFluxDensityAmount<M, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> Distance

    @JvmName("divYank")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: YankAmount<D, M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time,
                                          that.time to time),
                       unit = distance)

    operator fun div(that: YankAmount<D, M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricCurrent

    @JvmName("divElectricTension")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<D, EC, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.mass to mass)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = that.electricCurrent)

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<D, EC, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricTension

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
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
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<D, T>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.distance to distance)
                                     * convert(that.time to time),
                            unit = mass to U2(distance, time))

    operator fun div(that: VolumetricFlowAmount<D, T>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(distance, time))

    // endregion

    // region -> EnergyFluxDensity

    @JvmName("divArea")
    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        EnergyFluxDensityAmount(amount = amount / that.amount
                                         / convert(that.distance to distance,
                                                   that.distance to distance),
                                unit = mass to time)

    operator fun div(that: AreaAmount<D>) =
        EnergyFluxDensityAmount(amount = amount / that.amount,
                                unit = mass to time)

    @JvmName("timesFuelEfficiency")
    operator fun <D : Unit<Distance>> times(that: FuelEfficiencyAmount<D>) =
        EnergyFluxDensityAmount(amount = amount * that.amount
                                         / convert(that.distance to distance,
                                                   that.distance to distance),
                                unit = mass to time)

    operator fun times(that: FuelEfficiencyAmount<D>) =
        EnergyFluxDensityAmount(amount = amount * that.amount,
                                unit = mass to time)

    // endregion

    // region -> Force

    @JvmName("timesPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<D, T>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.time to time)
                             / convert(that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun times(that: PaceAmount<D, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        ForceAmount(amount = amount / that.amount
                             / convert(that.distance to distance)
                             * convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun div(that: SpeedAmount<D, T>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divEnergy")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyAmount<D, M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.mass to mass)
                                 * convert(that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: EnergyAmount<D, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> MassFlowRate

    @JvmName("divSpecificEnergy")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<D, T>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance)
                                    * convert(that.time to time,
                                              that.time to time),
                           unit = mass to time)

    operator fun div(that: SpecificEnergyAmount<D, T>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    // endregion

    // region -> Momentum

    @JvmName("divAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<D, T>) =
        MomentumAmount(amount = amount / that.amount
                                / convert(that.distance to distance)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = U2(distance, mass) to time)

    operator fun div(that: AccelerationAmount<D, T>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(distance, mass) to time)

    // endregion

    // region -> PowerDensity

    @JvmName("divVolume")
    operator fun <D : Unit<Distance>> div(that: VolumeAmount<D>) =
        PowerDensityAmount(amount = amount / that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance),
                           unit = mass to U2(distance, time))

    operator fun div(that: VolumeAmount<D>) =
        PowerDensityAmount(amount = amount / that.amount,
                           unit = mass to U2(distance, time))

    // endregion

    // region -> RadiantIntensity

    operator fun <A : Unit<Angle>> div(that: SolidAngleAmount<A>) =
        RadiantIntensityAmount(amount = amount / that.amount,
                               unit = U2(distance, mass) to U2(that.angle, time))

    // endregion

    // region -> SolidAngle

    @JvmName("divRadiantIntensity")
    operator fun <A : Unit<Angle>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: RadiantIntensityAmount<A, D, M, T>) =
        SolidAngleAmount(amount = amount / that.amount
                                  / convert(that.distance to distance,
                                            that.distance to distance,
                                            that.mass to mass)
                                  * convert(that.time to time,
                                            that.time to time,
                                            that.time to time),
                         unit = that.angle)

    operator fun <A : Unit<Angle>> div(that: RadiantIntensityAmount<A, D, M, T>) =
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
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<D, M, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.distance to distance,
                                       that.mass to mass)
                             * convert(that.time to time,
                                       that.time to time),
                    unit = distance to time)

    operator fun div(that: ForceAmount<D, M, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> Volume

    @JvmName("divPowerDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerDensityAmount<D, M, T>) =
        VolumeAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = distance)

    operator fun div(that: PowerDensityAmount<D, M, T>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = distance)

    // endregion

    // region -> VolumetricFlow

    @JvmName("divEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<D, M, T>) =
        VolumetricFlowAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.distance to distance,
                                                that.time to time,
                                                that.time to time),
                             unit = distance to time)

    operator fun div(that: EnergyDensityAmount<D, M, T>) =
        VolumetricFlowAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion

    // region -> Yank

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        YankAmount(amount = amount / that.amount
                            / convert(that.distance to distance),
                   unit = U2(distance, mass) to time)

    operator fun div(that: DistanceAmount<D>) =
        YankAmount(amount = amount / that.amount,
                   unit = U2(distance, mass) to time)

    @JvmName("timesSpacialFrequency")
    operator fun <D : Unit<Distance>> times(that: SpacialFrequencyAmount<D>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.distance to distance),
                   unit = U2(distance, mass) to time)

    operator fun times(that: SpacialFrequencyAmount<D>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, mass) to time)

    // endregion
}
