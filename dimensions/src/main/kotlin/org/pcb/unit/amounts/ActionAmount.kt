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
import org.pcb.unit.dimensions.Action
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ActionAmount<L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, T>)
    : Amount<Action>(amount = amount.toBigDecimal(),
                     unit = CompositeUnit(
                         domain = Action::class,
                         units = mapOf(unit.major.first to 2,
                                       unit.major.second to 1,
                                       unit.minor to -1)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Action>) =
            ActionAmount(amount = amount * unit.scale,
                         unit = U2(METER, kilo(GRAM)) to SECOND)
    }

    // region -> Action

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ActionAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                     unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ActionAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                     unit = U2(distance, mass) to time)

    @JvmName("plusAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: ActionAmount<L, M, T>) =
        ActionAmount(amount = amount + that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              / convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun plus(that: ActionAmount<L, M, T>) =
        ActionAmount(amount = amount + that.amount,
                     unit = U2(distance, mass) to time)

    @JvmName("minusAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: ActionAmount<L, M, T>) =
        ActionAmount(amount = amount - that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              / convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun minus(that: ActionAmount<L, M, T>) =
        ActionAmount(amount = amount - that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ActionAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: ActionAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = U2(distance, time))

    operator fun div(that: ForceAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(distance, time))

    // endregion

    // region -> AngularMomentum

    @JvmName("timesLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: LinearDensityAmount<L, M>) =
        AngularMomentumAmount(amount = amount * that.amount
                                       * convert(that.mass to mass)
                                       / convert(that.distance to distance),
                              unit = U2(mass, distance) to time)

    operator fun times(that: LinearDensityAmount<L, M>) =
        AngularMomentumAmount(amount = amount * that.amount,
                              unit = U2(mass, distance) to time)

    // endregion

    // region -> Area

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time),
                   unit = distance)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> Distance

    @JvmName("divMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.mass to mass)
                                * convert(that.time to time),
                       unit = distance)

    operator fun div(that: MomentumAmount<L, M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricCharge

    @JvmName("divMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxAmount<L, I, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      / convert(that.distance to distance,
                                                that.distance to distance,
                                                that.mass to mass)
                                      * convert(that.time to time,
                                                that.time to time),
                             unit = U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>> div(that: MagneticFluxAmount<L, I, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount,
                             unit = U2(that.electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun times(that: FrequencyAmount<T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        EnergyAmount(amount = amount / that.amount
                              / convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun div(that: TimeAmount<T>) =
        EnergyAmount(amount = amount / that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Force

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        ForceAmount(amount = amount / that.amount
                             / convert(that.distance to distance,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun div(that: AbsementAmount<L, T>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divMomentOfInertia")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MomentOfInertiaAmount<L, M>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.mass to mass),
                        unit = NUMERIC to time)

    operator fun div(that: MomentOfInertiaAmount<L, M>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> LinearDensity

    @JvmName("divVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<L, T>) =
        LinearDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.distance to distance)
                                     * convert(that.time to time),
                            unit = mass to distance)

    operator fun div(that: VolumetricFlowAmount<L, T>) =
        LinearDensityAmount(amount = amount / that.amount,
                            unit = mass to distance)

    // endregion

    // region -> MagneticFlux

    @JvmName("divElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<I, T>) =
        MagneticFluxAmount(amount = amount / that.amount
                                    / convert(that.time to time),
                           unit = U2(distance, mass) to U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricChargeAmount<I, T>) =
        MagneticFluxAmount(amount = amount / that.amount,
                           unit = U2(distance, mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> MassFlowRate

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance),
                           unit = mass to time)

    operator fun div(that: AreaAmount<L>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance),
                           unit = mass to time)

    operator fun times(that: FuelEfficiencyAmount<L>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to time)

    // endregion

    // region -> MomentOfInertia

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        MomentOfInertiaAmount(amount = amount / that.amount
                                       * convert(that.time to time),
                              unit = U2(distance, mass))

    operator fun div(that: FrequencyAmount<T>) =
        MomentOfInertiaAmount(amount = amount / that.amount,
                              unit = U2(distance, mass))

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MomentOfInertiaAmount(amount = amount * that.amount
                                       * convert(that.time to time),
                              unit = U2(distance, mass))

    operator fun times(that: TimeAmount<T>) =
        MomentOfInertiaAmount(amount = amount * that.amount,
                              unit = U2(distance, mass))

    // endregion

    // region -> Momentum

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        MomentumAmount(amount = amount / that.amount
                                / convert(that.distance to distance),
                       unit = U2(distance, mass) to time)

    operator fun div(that: DistanceAmount<L>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(distance, mass) to time)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        MomentumAmount(amount = amount * that.amount
                                / convert(that.distance to distance),
                       unit = U2(distance, mass) to time)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, mass) to time)

    // endregion

    // region -> Time

    @JvmName("divEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: EnergyAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> Viscosity

    @JvmName("divVolume")
    operator fun <L : Unit<Distance>> div(that: VolumeAmount<L>) =
        ViscosityAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = mass to U2(distance, time))

    operator fun div(that: VolumeAmount<L>) =
        ViscosityAmount(amount = amount / that.amount,
                        unit = mass to U2(distance, time))

    // endregion

    // region -> Volume

    @JvmName("divViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        VolumeAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.time to time),
                     unit = distance)

    operator fun div(that: ViscosityAmount<L, M, T>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = distance)

    // endregion

    // region -> VolumetricFlow

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        VolumetricFlowAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.distance to distance),
                             unit = distance to time)

    operator fun div(that: LinearDensityAmount<L, M>) =
        VolumetricFlowAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion
}
