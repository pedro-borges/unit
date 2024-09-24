package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Currency
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class TimeAmount<T : Unit<Time>>(
        amount: Number,
        unit: T)
    : Amount<Time>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Time::class,
                       units = mapOf(unit to 1)))
{
    val time = unit

    // region -> Time

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        TimeAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                   unit = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        TimeAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                   unit = time)

    @JvmName("plusTime")
    operator fun <T : Unit<Time>> plus(that: TimeAmount<T>) =
        TimeAmount(amount = amount + that.amount
                            * convert(that.time to time),
                   unit = time)

    operator fun plus(that: TimeAmount<T>) =
        TimeAmount(amount = amount + that.amount,
                   unit = time)

    @JvmName("minusTime")
    operator fun <T : Unit<Time>> minus(that: TimeAmount<T>) =
        TimeAmount(amount = amount - that.amount
                            * convert(that.time to time),
                   unit = time)

    operator fun minus(that: TimeAmount<T>) =
        TimeAmount(amount = amount - that.amount,
                   unit = time)

    // endregion

    // region -> Scalar

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        ScalarAmount(amount = amount * that.amount
                              / convert(that.time to time),
                     unit = NUMERIC)

    operator fun times(that: FrequencyAmount<T>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: TimeAmount<T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("timesJerk")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: JerkAmount<L, T>) =
        AccelerationAmount(amount = amount * that.amount
                                    / convert(that.time to time,
                                              that.time to time,
                                              that.time to time),
                           unit = that.distance to time)

    operator fun <L : Unit<Distance>> times(that: JerkAmount<L, T>) =
        AccelerationAmount(amount = amount * that.amount,
                           unit = that.distance to time)

    // endregion

    // region -> Action

    @JvmName("timesEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<L, M, T>) =
        ActionAmount(amount = amount * that.amount
                              / convert(that.time to time,
                                        that.time to time),
                     unit = U2(that.distance, that.mass) to time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: EnergyAmount<L, M, T>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> Angle

    @JvmName("timesAngularVelocity")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> times(that: AngularVelocityAmount<BAC, T>) =
        AngleAmount(amount = amount * that.amount
                             / convert(that.time to time),
                    unit = that.angle)

    operator fun <BAC : Unit<Angle>> times(that: AngularVelocityAmount<BAC, T>) =
        AngleAmount(amount = amount * that.amount,
                    unit = that.angle)

    // endregion

    // region -> AngularVelocity

    @JvmName("timesAngularAcceleration")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> times(that: AngularAccelerationAmount<BAC, T>) =
        AngularVelocityAmount(amount = amount * that.amount
                                       / convert(that.time to time,
                                                 that.time to time),
                              unit = that.angle to time)

    operator fun <BAC : Unit<Angle>> times(that: AngularAccelerationAmount<BAC, T>) =
        AngularVelocityAmount(amount = amount * that.amount,
                              unit = that.angle to time)

    // endregion

    // region -> Capacitance

    @JvmName("timesElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductanceAmount<L, I, M, T>) =
        CapacitanceAmount(amount = amount * that.amount
                                   * convert(that.time to time,
                                             that.time to time,
                                             that.time to time),
                          unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: ElectricConductanceAmount<L, I, M, T>) =
        CapacitanceAmount(amount = amount * that.amount,
                          unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    @JvmName("divElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricResistanceAmount<L, I, M, T>) =
        CapacitanceAmount(amount = amount / that.amount
                                   * convert(that.time to time,
                                             that.time to time,
                                             that.time to time),
                          unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>> div(that: ElectricResistanceAmount<L, I, M, T>) =
        CapacitanceAmount(amount = amount / that.amount,
                          unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> Currency

    @JvmName("timesRent")
    operator fun <C : Unit<Currency>, T : Unit<Time>> times(that: RentAmount<C, T>) =
        CurrencyAmount(amount = amount * that.amount
                                / convert(that.time to time),
                       unit = that.currency)

    operator fun <C : Unit<Currency>> times(that: RentAmount<C, T>) =
        CurrencyAmount(amount = amount * that.amount,
                       unit = that.currency)

    // endregion

    // region -> Debt

    operator fun <C : Unit<Currency>> times(that: CurrencyAmount<C>) =
        DebtAmount(amount = amount * that.amount,
                   unit = U2(that.currency, time))

    // endregion

    // region -> Distance

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.time to time),
                       unit = that.distance)

    operator fun <L : Unit<Distance>> div(that: PaceAmount<L, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = that.distance)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        DistanceAmount(amount = amount * that.amount
                                / convert(that.time to time),
                       unit = that.distance)

    operator fun <L : Unit<Distance>> times(that: SpeedAmount<L, T>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = that.distance)

    // endregion

    // region -> ElectricCharge

    operator fun <I : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<I>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(that.electricCurrent, time))

    // endregion

    // region -> ElectricConductance

    @JvmName("divInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: InductanceAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount
                                           * convert(that.time to time,
                                                     that.time to time),
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>> div(that: InductanceAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount,
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> ElectricResistance

    @JvmName("divCapacitance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: CapacitanceAmount<L, I, M, T>) =
        ElectricResistanceAmount(amount = amount / that.amount
                                          / convert(that.time to time,
                                                    that.time to time,
                                                    that.time to time,
                                                    that.time to time),
                                 unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>> div(that: CapacitanceAmount<L, I, M, T>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesPower")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = U2(that.distance, that.mass) to time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: PowerAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerDensityAmount<L, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     / convert(that.time to time,
                                               that.time to time,
                                               that.time to time),
                            unit = that.mass to U2(that.distance, time))

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: PowerDensityAmount<L, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(that.distance, time))

    // endregion

    // region -> Force

    @JvmName("timesYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: YankAmount<L, M, T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(that.distance, that.mass) to time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: YankAmount<L, M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> Inductance

    @JvmName("divElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        InductanceAmount(amount = amount / that.amount
                                  / convert(that.time to time,
                                            that.time to time,
                                            that.time to time),
                         unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        InductanceAmount(amount = amount / that.amount,
                         unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    @JvmName("timesElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        InductanceAmount(amount = amount * that.amount
                                  / convert(that.time to time,
                                            that.time to time,
                                            that.time to time),
                         unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        InductanceAmount(amount = amount * that.amount,
                         unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> Jerk

    @JvmName("timesSnap")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SnapAmount<L, T>) =
        JerkAmount(amount = amount * that.amount
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = that.distance to time)

    operator fun <L : Unit<Distance>> times(that: SnapAmount<L, T>) =
        JerkAmount(amount = amount * that.amount,
                   unit = that.distance to time)

    // endregion

    // region -> LuminousEnergy

    operator fun <BAC : Unit<Angle>, J : Unit<LuminousIntensity>> times(that: LuminousFluxAmount<BAC, J>) =
        LuminousEnergyAmount(amount = amount * that.amount,
                             unit = U3(that.angle, that.luminousIntensity, time))

    // endregion

    // region -> MagneticFlux

    @JvmName("timesElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        MagneticFluxAmount(amount = amount * that.amount
                                    / convert(that.time to time,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: ElectricTensionAmount<L, I, M, T>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> Mass

    @JvmName("timesMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        MassAmount(amount = amount * that.amount
                            / convert(that.time to time),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> times(that: MassFlowRateAmount<M, T>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: RadiantExposureAmount<M, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    / convert(that.time to time,
                                              that.time to time),
                           unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: RadiantExposureAmount<M, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to time)

    // endregion

    // region -> MolarVolume

    @JvmName("timesCatalyticEfficiency")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> times(that: CatalyticEfficiencyAmount<L, N, T>) =
        MolarVolumeAmount(amount = amount * that.amount
                                   / convert(that.time to time),
                          unit = that.distance to that.substance)

    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: CatalyticEfficiencyAmount<L, N, T>) =
        MolarVolumeAmount(amount = amount * that.amount,
                          unit = that.distance to that.substance)

    // endregion

    // region -> Molarity

    @JvmName("timesReactionRate")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> times(that: ReactionRateAmount<L, N, T>) =
        MolarityAmount(amount = amount * that.amount
                                / convert(that.time to time),
                       unit = that.substance to that.distance)

    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: ReactionRateAmount<L, N, T>) =
        MolarityAmount(amount = amount * that.amount,
                       unit = that.substance to that.distance)

    // endregion

    // region -> MomentOfInertia

    @JvmName("timesAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ActionAmount<L, M, T>) =
        MomentOfInertiaAmount(amount = amount * that.amount
                                       / convert(that.time to time),
                              unit = U2(that.distance, that.mass))

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: ActionAmount<L, M, T>) =
        MomentOfInertiaAmount(amount = amount * that.amount,
                              unit = U2(that.distance, that.mass))

    // endregion

    // region -> Momentum

    @JvmName("timesForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<L, M, T>) =
        MomentumAmount(amount = amount * that.amount
                                / convert(that.time to time,
                                          that.time to time),
                       unit = U2(that.distance, that.mass) to time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: ForceAmount<L, M, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> Pace

    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to that.distance)

    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        PaceAmount(amount = amount * that.amount,
                   unit = time to that.distance)

    // endregion

    // region -> Permittivity

    @JvmName("timesElectricConductivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductivityAmount<L, I, M, T>) =
        PermittivityAmount(amount = amount * that.amount
                                    * convert(that.time to time,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: ElectricConductivityAmount<L, I, M, T>) =
        PermittivityAmount(amount = amount * that.amount,
                           unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesIntensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: IntensityAmount<M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       / convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: IntensityAmount<M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to time)

    // endregion

    // region -> Speed

    @JvmName("timesAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        SpeedAmount(amount = amount * that.amount
                             / convert(that.time to time,
                                       that.time to time),
                    unit = that.distance to time)

    operator fun <L : Unit<Distance>> times(that: AccelerationAmount<L, T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = that.distance to time)

    // endregion

    // region -> Volume

    @JvmName("timesVolumetricFlow")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<L, T>) =
        VolumeAmount(amount = amount * that.amount
                              / convert(that.time to time),
                     unit = that.distance)

    operator fun <L : Unit<Distance>> times(that: VolumetricFlowAmount<L, T>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = that.distance)

    // endregion
}
