package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
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
import org.pcb.unit.dimensions.Momentum
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MomentumAmount<L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, T>)
    : Amount<Momentum>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           domain = Momentum::class,
                           units = mapOf(unit.major.first to 1,
                                         unit.major.second to 1,
                                         unit.minor to -1)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Momentum>) =
            MomentumAmount(amount = amount * unit.scale,
                           unit = U2(METER, kilo(GRAM)) to SECOND)
    }

    // region -> Momentum

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                       unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MomentumAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                       unit = U2(distance, mass) to time)

    @JvmName("plusMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: MomentumAmount<L, M, T>) =
        MomentumAmount(amount = amount + that.amount
                                * convert(that.distance to distance,
                                          that.mass to mass)
                                / convert(that.time to time),
                       unit = U2(distance, mass) to time)

    operator fun plus(that: MomentumAmount<L, M, T>) =
        MomentumAmount(amount = amount + that.amount,
                       unit = U2(distance, mass) to time)

    @JvmName("minusMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: MomentumAmount<L, M, T>) =
        MomentumAmount(amount = amount - that.amount
                                * convert(that.distance to distance,
                                          that.mass to mass)
                                / convert(that.time to time),
                       unit = U2(distance, mass) to time)

    operator fun minus(that: MomentumAmount<L, M, T>) =
        MomentumAmount(amount = amount - that.amount,
                       unit = U2(distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.mass to mass)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MomentumAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: RadiantExposureAmount<M, T>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = U2(distance, time))

    operator fun div(that: RadiantExposureAmount<M, T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(distance, time))

    // endregion

    // region -> Action

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        ActionAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun times(that: DistanceAmount<L>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        ActionAmount(amount = amount / that.amount
                              * convert(that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        ActionAmount(amount = amount / that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> AngularMomentum

    @JvmName("timesMass")
    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        AngularMomentumAmount(amount = amount * that.amount
                                       * convert(that.mass to mass),
                              unit = U2(mass, distance) to time)

    operator fun times(that: MassAmount<M>) =
        AngularMomentumAmount(amount = amount * that.amount,
                              unit = U2(mass, distance) to time)

    // endregion

    // region -> Area

    @JvmName("divViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance,
                                      that.time to time),
                   unit = distance)

    operator fun div(that: ViscosityAmount<L, M, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> AreaDensity

    @JvmName("divVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: VolumetricFlowAmount<L, T>) =
        AreaDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance)
                                   * convert(that.time to time),
                          unit = mass to distance)

    operator fun div(that: VolumetricFlowAmount<L, T>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    // endregion

    // region -> CentrifugalForce

    @JvmName("timesAngularVelocity")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> times(that: AngularVelocityAmount<BAC, T>) =
        CentrifugalForceAmount(amount = amount * that.amount
                                        / convert(that.time to time),
                               unit = U3(that.angle, distance, mass) to time)

    operator fun <BAC : Unit<Angle>> times(that: AngularVelocityAmount<BAC, T>) =
        CentrifugalForceAmount(amount = amount * that.amount,
                               unit = U3(that.angle, distance, mass) to time)

    // endregion

    // region -> Distance

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass)
                                * convert(that.time to time),
                       unit = distance)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricDipoleMoment

    @JvmName("divMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricDipoleMomentAmount(amount = amount / that.amount
                                            / convert(that.mass to mass)
                                            * convert(that.time to time,
                                                      that.time to time),
                                   unit = U3(distance, that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricDipoleMomentAmount(amount = amount / that.amount,
                                   unit = U3(distance, that.electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        EnergyAmount(amount = amount / that.amount
                              / convert(that.time to time)
                              * convert(that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun div(that: PaceAmount<L, T>) =
        EnergyAmount(amount = amount / that.amount,
                     unit = U2(distance, mass) to time)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance)
                              / convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun times(that: SpeedAmount<L, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Force

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun times(that: FrequencyAmount<T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount
                             / convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun div(that: TimeAmount<T>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("divElectricDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricDipoleMomentAmount<L, I, T>) =
        MagneticFluxDensityAmount(amount = amount / that.amount
                                           / convert(that.distance to distance,
                                                     that.time to time),
                                  unit = mass to U2(that.electricCurrent, time))

    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricDipoleMomentAmount<L, I, T>) =
        MagneticFluxDensityAmount(amount = amount / that.amount,
                                  unit = mass to U2(that.electricCurrent, time))

    // endregion

    // region -> Mass

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.time to time)
                            / convert(that.distance to distance),
                   unit = mass)

    operator fun times(that: PaceAmount<L, T>) =
        MassAmount(amount = amount * that.amount,
                   unit = mass)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time),
                   unit = mass)

    operator fun div(that: SpeedAmount<L, T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance),
                           unit = mass to time)

    operator fun div(that: DistanceAmount<L>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    / convert(that.distance to distance),
                           unit = mass to time)

    operator fun times(that: SpacialFrequencyAmount<L>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to time)

    // endregion

    // region -> MomentOfInertia

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        MomentOfInertiaAmount(amount = amount * that.amount
                                       * convert(that.distance to distance,
                                                 that.time to time),
                              unit = U2(distance, mass))

    operator fun times(that: AbsementAmount<L, T>) =
        MomentOfInertiaAmount(amount = amount * that.amount,
                              unit = U2(distance, mass))

    // endregion

    // region -> Pace

    @JvmName("divEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun div(that: EnergyAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    // endregion

    // region -> Power

    @JvmName("timesAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun times(that: AccelerationAmount<L, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> RadiantExposure

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        RadiantExposureAmount(amount = amount / that.amount
                                       / convert(that.distance to distance,
                                                 that.time to time),
                              unit = mass to time)

    operator fun div(that: AbsementAmount<L, T>) =
        RadiantExposureAmount(amount = amount / that.amount,
                              unit = mass to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ActionAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.distance to distance,
                                                  that.distance to distance,
                                                  that.mass to mass)
                                        * convert(that.time to time),
                               unit = NUMERIC to distance)

    operator fun div(that: ActionAmount<L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Speed

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass),
                    unit = distance to time)

    operator fun div(that: MassAmount<M>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> Time

    @JvmName("divForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: ForceAmount<L, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> Viscosity

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        ViscosityAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance),
                        unit = mass to U2(distance, time))

    operator fun div(that: AreaAmount<L>) =
        ViscosityAmount(amount = amount / that.amount,
                        unit = mass to U2(distance, time))

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        ViscosityAmount(amount = amount * that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance),
                        unit = mass to U2(distance, time))

    operator fun times(that: FuelEfficiencyAmount<L>) =
        ViscosityAmount(amount = amount * that.amount,
                        unit = mass to U2(distance, time))

    // endregion

    // region -> VolumetricFlow

    @JvmName("divAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<L, M>) =
        VolumetricFlowAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.distance to distance,
                                                that.distance to distance),
                             unit = distance to time)

    operator fun div(that: AreaDensityAmount<L, M>) =
        VolumetricFlowAmount(amount = amount / that.amount,
                             unit = distance to time)

    // endregion
}
