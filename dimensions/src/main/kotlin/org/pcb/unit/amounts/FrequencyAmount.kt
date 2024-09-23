package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Currency
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Frequency
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class FrequencyAmount<T : Unit<Time>>(
        amount: Number,
        unit: Pair<Unit<Scalar>, T>)
    : Amount<Frequency>(amount = amount.toBigDecimal(),
                        unit = CompositeUnit(
                            domain = Frequency::class,
                            units = mapOf(unit.minor to -1)))
{
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Frequency>) =
            FrequencyAmount(amount = amount * unit.scale,
                            unit = NUMERIC to SECOND)
    }

    // region -> Frequency

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        FrequencyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                        unit = NUMERIC to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        FrequencyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                        unit = NUMERIC to time)

    @JvmName("plusFrequency")
    operator fun <T : Unit<Time>> plus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount + that.amount
                                 / convert(that.time to time),
                        unit = NUMERIC to time)

    operator fun plus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount + that.amount,
                        unit = NUMERIC to time)

    @JvmName("minusFrequency")
    operator fun <T : Unit<Time>> minus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount - that.amount
                                 / convert(that.time to time),
                        unit = NUMERIC to time)

    operator fun minus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount - that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Scalar

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        ScalarAmount(amount = amount / that.amount
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: FrequencyAmount<T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun times(that: TimeAmount<T>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.time to time),
                           unit = that.distance to time)

    operator fun <L : Unit<Distance>> div(that: PaceAmount<L, T>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = that.distance to time)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        AccelerationAmount(amount = amount * that.amount
                                    / convert(that.time to time),
                           unit = that.distance to time)

    operator fun <L : Unit<Distance>> times(that: SpeedAmount<L, T>) =
        AccelerationAmount(amount = amount * that.amount,
                           unit = that.distance to time)

    // endregion

    // region -> Action

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MomentOfInertiaAmount<L, M>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> AngularAcceleration

    @JvmName("timesAngularVelocity")
    operator fun <BAC : Unit<Angle>, T : Unit<Time>> times(that: AngularVelocityAmount<BAC, T>) =
        AngularAccelerationAmount(amount = amount * that.amount
                                           / convert(that.time to time),
                                  unit = that.angle to time)

    operator fun <BAC : Unit<Angle>> times(that: AngularVelocityAmount<BAC, T>) =
        AngularAccelerationAmount(amount = amount * that.amount,
                                  unit = that.angle to time)

    // endregion

    // region -> AngularVelocity

    operator fun <BAC : Unit<Angle>> times(that: AngleAmount<BAC>) =
        AngularVelocityAmount(amount = amount * that.amount,
                              unit = that.angle to time)

    // endregion

    // region -> CatalyticEfficiency

    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarVolumeAmount<L, N>) =
        CatalyticEfficiencyAmount(amount = amount * that.amount,
                                  unit = that.distance to U2(that.substance, time))

    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarityAmount<L, N>) =
        CatalyticEfficiencyAmount(amount = amount / that.amount,
                                  unit = that.distance to U2(that.substance, time))

    // endregion

    // region -> Currency

    @JvmName("timesDebt")
    operator fun <C : Unit<Currency>, T : Unit<Time>> times(that: DebtAmount<C, T>) =
        CurrencyAmount(amount = amount * that.amount
                                * convert(that.time to time),
                       unit = that.currency)

    operator fun <C : Unit<Currency>> times(that: DebtAmount<C, T>) =
        CurrencyAmount(amount = amount * that.amount,
                       unit = that.currency)

    // endregion

    // region -> ElectricConductance

    @JvmName("timesCapacitance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: CapacitanceAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount * that.amount
                                           * convert(that.time to time,
                                                     that.time to time,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: CapacitanceAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount * that.amount,
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> ElectricCurrent

    @JvmName("timesElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<I, T>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.time to time),
                              unit = that.electricCurrent)

    operator fun <I : Unit<ElectricCurrent>> times(that: ElectricChargeAmount<I, T>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricResistance

    @JvmName("timesInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: InductanceAmount<L, I, M, T>) =
        ElectricResistanceAmount(amount = amount * that.amount
                                          / convert(that.time to time,
                                                    that.time to time),
                                 unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: InductanceAmount<L, I, M, T>) =
        ElectricResistanceAmount(amount = amount * that.amount,
                                 unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> ElectricTension

    @JvmName("timesMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<L, I, M, T>) =
        ElectricTensionAmount(amount = amount * that.amount
                                       / convert(that.time to time,
                                                 that.time to time),
                              unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: MagneticFluxAmount<L, I, M, T>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesAction")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ActionAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.time to time),
                     unit = U2(that.distance, that.mass) to time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: ActionAmount<L, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> EnergyFluxDensity

    @JvmName("timesRadiantExposure")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: RadiantExposureAmount<M, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount
                                         / convert(that.time to time,
                                                   that.time to time),
                                unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: RadiantExposureAmount<M, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount,
                                unit = that.mass to time)

    // endregion

    // region -> Force

    @JvmName("timesMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<L, M, T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.time to time),
                    unit = U2(that.distance, that.mass) to time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: MomentumAmount<L, M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> Jerk

    @JvmName("timesAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        JerkAmount(amount = amount * that.amount
                            / convert(that.time to time,
                                      that.time to time),
                   unit = that.distance to time)

    operator fun <L : Unit<Distance>> times(that: AccelerationAmount<L, T>) =
        JerkAmount(amount = amount * that.amount,
                   unit = that.distance to time)

    // endregion

    // region -> LuminousFlux

    @JvmName("timesLuminousEnergy")
    operator fun <BAC : Unit<Angle>, J : Unit<LuminousIntensity>, T : Unit<Time>> times(that: LuminousEnergyAmount<BAC, J, T>) =
        LuminousFluxAmount(amount = amount * that.amount
                                    * convert(that.time to time),
                           unit = U2(that.angle, that.luminousIntensity))

    operator fun <BAC : Unit<Angle>, J : Unit<LuminousIntensity>> times(that: LuminousEnergyAmount<BAC, J, T>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(that.angle, that.luminousIntensity))

    // endregion

    // region -> MassFlowRate

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to time)

    // endregion

    // region -> Molarity

    @JvmName("divCatalyticEfficiency")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> div(that: CatalyticEfficiencyAmount<L, N, T>) =
        MolarityAmount(amount = amount / that.amount
                                * convert(that.time to time),
                       unit = that.substance to that.distance)

    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: CatalyticEfficiencyAmount<L, N, T>) =
        MolarityAmount(amount = amount / that.amount,
                       unit = that.substance to that.distance)

    // endregion

    // region -> Pace

    @JvmName("divAcceleration")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<L, T>) =
        PaceAmount(amount = amount / that.amount
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time to that.distance)

    operator fun <L : Unit<Distance>> div(that: AccelerationAmount<L, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to that.distance)

    // endregion

    // region -> Power

    @JvmName("timesEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<L, M, T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(that.distance, that.mass) to time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: EnergyAmount<L, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> PowerDensity

    @JvmName("timesEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<L, M, T>) =
        PowerDensityAmount(amount = amount * that.amount
                                    / convert(that.time to time,
                                              that.time to time),
                           unit = that.mass to U2(that.distance, time))

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: EnergyDensityAmount<L, M, T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = that.mass to U2(that.distance, time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       / convert(that.time to time),
                              unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: MassFlowRateAmount<M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to time)

    // endregion

    // region -> Rent

    operator fun <C : Unit<Currency>> times(that: CurrencyAmount<C>) =
        RentAmount(amount = amount * that.amount,
                   unit = that.currency to time)

    // endregion

    // region -> Snap

    @JvmName("timesJerk")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: JerkAmount<L, T>) =
        SnapAmount(amount = amount * that.amount
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = that.distance to time)

    operator fun <L : Unit<Distance>> times(that: JerkAmount<L, T>) =
        SnapAmount(amount = amount * that.amount,
                   unit = that.distance to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        SpacialFrequencyAmount(amount = amount * that.amount
                                        * convert(that.time to time),
                               unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> times(that: PaceAmount<L, T>) =
        SpacialFrequencyAmount(amount = amount * that.amount,
                               unit = NUMERIC to that.distance)

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        * convert(that.time to time),
                               unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> div(that: SpeedAmount<L, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to that.distance)

    // endregion

    // region -> Speed

    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = that.distance to time)

    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = that.distance to time)

    // endregion

    // region -> VolumetricFlow

    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = that.distance to time)

    // endregion

    // region -> Yank

    @JvmName("timesForce")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<L, M, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.time to time,
                                      that.time to time),
                   unit = U2(that.distance, that.mass) to time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: ForceAmount<L, M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(that.distance, that.mass) to time)

    // endregion
}
