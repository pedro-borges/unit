package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Absement
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AbsementAmount<L : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: U2<L, T>)
    : Amount<Absement>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           domain = Absement::class,
                           units = mapOf(unit.first to 1,
                                         unit.second to 1)))
{
    val distance = unit.first
    val time = unit.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Absement>) =
            AbsementAmount(amount = amount * unit.scale,
                           unit = U2(METER, SECOND))
    }

    // region -> Absement

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AbsementAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                       unit = U2(distance, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AbsementAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                       unit = U2(distance, time))

    @JvmName("plusAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> plus(that: AbsementAmount<L, T>) =
        AbsementAmount(amount = amount + that.amount
                                * convert(that.distance to distance,
                                          that.time to time),
                       unit = U2(distance, time))

    operator fun plus(that: AbsementAmount<L, T>) =
        AbsementAmount(amount = amount + that.amount,
                       unit = U2(distance, time))

    @JvmName("minusAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> minus(that: AbsementAmount<L, T>) =
        AbsementAmount(amount = amount - that.amount
                                * convert(that.distance to distance,
                                          that.time to time),
                       unit = U2(distance, time))

    operator fun minus(that: AbsementAmount<L, T>) =
        AbsementAmount(amount = amount - that.amount,
                       unit = U2(distance, time))

    // endregion

    // region -> Scalar

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: AbsementAmount<L, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Action

    @JvmName("timesForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<L, M, T>) =
        ActionAmount(amount = amount * that.amount
                              * convert(that.distance to distance)
                              / convert(that.time to time,
                                        that.time to time),
                     unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: ForceAmount<L, M, T>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Area

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.time to time)
                            * convert(that.distance to distance),
                   unit = distance)

    operator fun div(that: PaceAmount<L, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        AreaAmount(amount = amount * that.amount
                            * convert(that.distance to distance)
                            / convert(that.time to time),
                   unit = distance)

    operator fun times(that: SpeedAmount<L, T>) =
        AreaAmount(amount = amount * that.amount,
                   unit = distance)

    // endregion

    // region -> Capacitance

    @JvmName("timesElectricConductivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductivityAmount<L, I, M, T>) =
        CapacitanceAmount(amount = amount * that.amount
                                   * convert(that.time to time,
                                             that.time to time,
                                             that.time to time)
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = U2(time, that.electricCurrent) to U2(that.mass, distance))

    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: ElectricConductivityAmount<L, I, M, T>) =
        CapacitanceAmount(amount = amount * that.amount,
                          unit = U2(time, that.electricCurrent) to U2(that.mass, distance))

    // endregion

    // region -> Distance

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        DistanceAmount(amount = amount * that.amount
                                / convert(that.time to time),
                       unit = distance)

    operator fun times(that: FrequencyAmount<T>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.time to time),
                       unit = distance)

    operator fun div(that: TimeAmount<T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> Energy

    @JvmName("timesYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance)
                              / convert(that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: YankAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Force

    @JvmName("timesIntensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: IntensityAmount<M, T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: IntensityAmount<M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to time)

    // endregion

    // region -> HeatCapacity

    @JvmName("timesThermalConductivity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: ThermalConductivityAmount<L, M, K, T>) =
        HeatCapacityAmount(amount = amount * that.amount
                                    * convert(that.distance to distance)
                                    / convert(that.time to time,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(distance, that.mass) to U2(that.temperature, time))

    operator fun <M : Unit<Mass>, K : Unit<Temperature>> times(that: ThermalConductivityAmount<L, M, K, T>) =
        HeatCapacityAmount(amount = amount * that.amount,
                           unit = U2(distance, that.mass) to U2(that.temperature, time))

    // endregion

    // region -> MassFlowRate

    @JvmName("timesEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    / convert(that.distance to distance,
                                              that.time to time,
                                              that.time to time),
                           unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: EnergyDensityAmount<L, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to time)

    // endregion

    // region -> MomentOfInertia

    @JvmName("timesMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<L, M, T>) =
        MomentOfInertiaAmount(amount = amount * that.amount
                                       * convert(that.distance to distance)
                                       / convert(that.time to time),
                              unit = U2(distance, that.mass))

    operator fun <M : Unit<Mass>> times(that: MomentumAmount<L, M, T>) =
        MomentOfInertiaAmount(amount = amount * that.amount,
                              unit = U2(distance, that.mass))

    // endregion

    // region -> Momentum

    @JvmName("timesRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: RadiantExposureAmount<M, T>) =
        MomentumAmount(amount = amount * that.amount
                                / convert(that.time to time,
                                          that.time to time),
                       unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: RadiantExposureAmount<M, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Pace

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance),
                   unit = time to distance)

    operator fun div(that: AreaAmount<L>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        PaceAmount(amount = amount * that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance),
                   unit = time to distance)

    operator fun times(that: FuelEfficiencyAmount<L>) =
        PaceAmount(amount = amount * that.amount,
                   unit = time to distance)

    // endregion

    // region -> RadiantExposure

    @JvmName("timesPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<L, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       / convert(that.distance to distance,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: PowerDensityAmount<L, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to time)

    // endregion

    // region -> SpecificEnergy

    @JvmName("timesJerk")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: JerkAmount<L, T>) =
        SpecificEnergyAmount(amount = amount * that.amount
                                      * convert(that.distance to distance)
                                      / convert(that.time to time,
                                                that.time to time,
                                                that.time to time),
                             unit = distance to time)

    operator fun times(that: JerkAmount<L, T>) =
        SpecificEnergyAmount(amount = amount * that.amount,
                             unit = distance to time)

    // endregion

    // region -> Time

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance),
                   unit = time)

    operator fun div(that: DistanceAmount<L>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        TimeAmount(amount = amount * that.amount
                            / convert(that.distance to distance),
                   unit = time)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        TimeAmount(amount = amount * that.amount,
                   unit = time)

    // endregion

    // region -> VolumetricFlow

    @JvmName("timesSpecificEnergy")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        VolumetricFlowAmount(amount = amount * that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.time to time,
                                                that.time to time),
                             unit = distance to time)

    operator fun times(that: SpecificEnergyAmount<L, T>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = distance to time)

    // endregion
}
