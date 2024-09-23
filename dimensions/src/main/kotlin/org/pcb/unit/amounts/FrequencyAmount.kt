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
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<D, T>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.time to time),
                           unit = that.distance to time)

    operator fun <D : Unit<Distance>> div(that: PaceAmount<D, T>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = that.distance to time)

    @JvmName("timesSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        AccelerationAmount(amount = amount * that.amount
                                    / convert(that.time to time),
                           unit = that.distance to time)

    operator fun <D : Unit<Distance>> times(that: SpeedAmount<D, T>) =
        AccelerationAmount(amount = amount * that.amount,
                           unit = that.distance to time)

    // endregion

    // region -> Action

    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: MomentOfInertiaAmount<D, M>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> AngularAcceleration

    @JvmName("timesAngularVelocity")
    operator fun <A : Unit<Angle>, T : Unit<Time>> times(that: AngularVelocityAmount<A, T>) =
        AngularAccelerationAmount(amount = amount * that.amount
                                           / convert(that.time to time),
                                  unit = that.angle to time)

    operator fun <A : Unit<Angle>> times(that: AngularVelocityAmount<A, T>) =
        AngularAccelerationAmount(amount = amount * that.amount,
                                  unit = that.angle to time)

    // endregion

    // region -> AngularVelocity

    operator fun <A : Unit<Angle>> times(that: AngleAmount<A>) =
        AngularVelocityAmount(amount = amount * that.amount,
                              unit = that.angle to time)

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
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: CapacitanceAmount<D, EC, M, T>) =
        ElectricConductanceAmount(amount = amount * that.amount
                                           * convert(that.time to time,
                                                     that.time to time,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: CapacitanceAmount<D, EC, M, T>) =
        ElectricConductanceAmount(amount = amount * that.amount,
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> ElectricCurrent

    @JvmName("timesElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<EC, T>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.time to time),
                              unit = that.electricCurrent)

    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricChargeAmount<EC, T>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricResistance

    @JvmName("timesInductance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: InductanceAmount<D, EC, M, T>) =
        ElectricResistanceAmount(amount = amount * that.amount
                                          / convert(that.time to time,
                                                    that.time to time),
                                 unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: InductanceAmount<D, EC, M, T>) =
        ElectricResistanceAmount(amount = amount * that.amount,
                                 unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> ElectricTension

    @JvmName("timesMagneticFlux")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<D, EC, M, T>) =
        ElectricTensionAmount(amount = amount * that.amount
                                       / convert(that.time to time,
                                                 that.time to time),
                              unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: MagneticFluxAmount<D, EC, M, T>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesAction")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ActionAmount<D, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.time to time),
                     unit = U2(that.distance, that.mass) to time)

    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: ActionAmount<D, M, T>) =
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
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<D, M, T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.time to time),
                    unit = U2(that.distance, that.mass) to time)

    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: MomentumAmount<D, M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> Jerk

    @JvmName("timesAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<D, T>) =
        JerkAmount(amount = amount * that.amount
                            / convert(that.time to time,
                                      that.time to time),
                   unit = that.distance to time)

    operator fun <D : Unit<Distance>> times(that: AccelerationAmount<D, T>) =
        JerkAmount(amount = amount * that.amount,
                   unit = that.distance to time)

    // endregion

    // region -> LuminousFlux

    @JvmName("timesLuminousEnergy")
    operator fun <A : Unit<Angle>, LI : Unit<LuminousIntensity>, T : Unit<Time>> times(that: LuminousEnergyAmount<A, LI, T>) =
        LuminousFluxAmount(amount = amount * that.amount
                                    * convert(that.time to time),
                           unit = U2(that.angle, that.luminousIntensity))

    operator fun <A : Unit<Angle>, LI : Unit<LuminousIntensity>> times(that: LuminousEnergyAmount<A, LI, T>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(that.angle, that.luminousIntensity))

    // endregion

    // region -> MassFlowRate

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to time)

    // endregion

    // region -> Pace

    @JvmName("divAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<D, T>) =
        PaceAmount(amount = amount / that.amount
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time to that.distance)

    operator fun <D : Unit<Distance>> div(that: AccelerationAmount<D, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to that.distance)

    // endregion

    // region -> Power

    @JvmName("timesEnergy")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<D, M, T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(that.distance, that.mass) to time)

    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: EnergyAmount<D, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> PowerDensity

    @JvmName("timesEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<D, M, T>) =
        PowerDensityAmount(amount = amount * that.amount
                                    / convert(that.time to time,
                                              that.time to time),
                           unit = that.mass to U2(that.distance, time))

    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: EnergyDensityAmount<D, M, T>) =
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
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: JerkAmount<D, T>) =
        SnapAmount(amount = amount * that.amount
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = that.distance to time)

    operator fun <D : Unit<Distance>> times(that: JerkAmount<D, T>) =
        SnapAmount(amount = amount * that.amount,
                   unit = that.distance to time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("timesPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<D, T>) =
        SpacialFrequencyAmount(amount = amount * that.amount
                                        * convert(that.time to time),
                               unit = NUMERIC to that.distance)

    operator fun <D : Unit<Distance>> times(that: PaceAmount<D, T>) =
        SpacialFrequencyAmount(amount = amount * that.amount,
                               unit = NUMERIC to that.distance)

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        * convert(that.time to time),
                               unit = NUMERIC to that.distance)

    operator fun <D : Unit<Distance>> div(that: SpeedAmount<D, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to that.distance)

    // endregion

    // region -> Speed

    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = that.distance to time)

    operator fun <D : Unit<Distance>> div(that: SpacialFrequencyAmount<D>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = that.distance to time)

    // endregion

    // region -> VolumetricFlow

    operator fun <D : Unit<Distance>> times(that: VolumeAmount<D>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = that.distance to time)

    // endregion

    // region -> Yank

    @JvmName("timesForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<D, M, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.time to time,
                                      that.time to time),
                   unit = U2(that.distance, that.mass) to time)

    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: ForceAmount<D, M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(that.distance, that.mass) to time)

    // endregion
}
