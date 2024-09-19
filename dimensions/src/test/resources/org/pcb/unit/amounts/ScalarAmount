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
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ScalarAmount<S : Unit<Scalar>>(
        amount: Number,
        unit: S)
    : Amount<Scalar>(amount = amount.toBigDecimal(),
                     unit = CompositeUnit(
                         domain = Scalar::class,
                         units = mapOf(unit to 1)))
{
    val scalar = unit

    // region -> Scalar

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                     unit = scalar)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                     unit = scalar)

    @JvmName("plusScalar")
    operator fun <S : Unit<Scalar>> plus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount + that.amount * convert(that.scalar to scalar),
                     unit = scalar)

    operator fun plus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount + that.amount,
                     unit = scalar)

    @JvmName("minusScalar")
    operator fun <S : Unit<Scalar>> minus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount - that.amount * convert(that.scalar to scalar),
                     unit = scalar)

    operator fun minus(that: ScalarAmount<S>) =
        ScalarAmount(amount = amount - that.amount,
                     unit = scalar)

    // endregion

    // region -> Acceleration

    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<D, T>) =
        AccelerationAmount(amount = amount * that.amount,
                           unit = that.distance to that.time)

    // endregion

    // region -> Angle

    operator fun <A : Unit<Angle>> times(that: AngleAmount<A>) =
        AngleAmount(amount = amount * that.amount,
                    unit = that.angle)

    // endregion

    // region -> Area

    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        AreaAmount(amount = amount * that.amount,
                   unit = that.distance)

    // endregion

    // region -> Capacitance

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: CapacitanceAmount<D, EC, M, T>) =
        CapacitanceAmount(amount = amount * that.amount,
                          unit = U2(that.time, that.electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> Currency

    operator fun <C : Unit<Currency>> times(that: CurrencyAmount<C>) =
        CurrencyAmount(amount = amount * that.amount,
                       unit = that.currency)

    // endregion

    // region -> Debt

    operator fun <C : Unit<Currency>, T : Unit<Time>> times(that: DebtAmount<C, T>) =
        DebtAmount(amount = amount * that.amount,
                   unit = U2(that.currency, that.time))

    // endregion

    // region -> Distance

    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = that.distance)

    // endregion

    // region -> ElectricCharge

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(that.electricCurrent, that.time))

    // endregion

    // region -> ElectricConductance

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductanceAmount<D, EC, M, T>) =
        ElectricConductanceAmount(amount = amount * that.amount,
                                  unit = U2(that.time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricResistanceAmount<D, EC, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount,
                                  unit = U2(that.time, that.electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> ElectricCurrent

    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricResistance

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<D, EC, M, T>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(that.distance, that.mass) to U2(that.electricCurrent, that.time))

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<D, EC, M, T>) =
        ElectricResistanceAmount(amount = amount * that.amount,
                                 unit = U2(that.distance, that.mass) to U2(that.electricCurrent, that.time))

    // endregion

    // region -> ElectricTension

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<D, EC, M, T>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              unit = U2(that.distance, that.mass) to U2(that.electricCurrent, that.time))

    // endregion

    // region -> Energy

    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyAmount<D, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to that.time)

    // endregion

    // region -> EnergyDensity

    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: EnergyDensityAmount<D, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(that.distance, that.time))

    // endregion

    // region -> Force

    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<D, M, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.distance, that.mass) to that.time)

    // endregion

    // region -> Frequency

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount * that.amount,
                        unit = NUMERIC to that.time)

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to that.time)

    // endregion

    // region -> Illuminance

    operator fun <A : Unit<Angle>, D : Unit<Distance>, LI : Unit<LuminousIntensity>> times(that: IlluminanceAmount<A, D, LI>) =
        IlluminanceAmount(amount = amount * that.amount,
                          unit = U2(that.angle, that.luminousIntensity) to that.distance)

    // endregion

    // region -> Inductance

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: InductanceAmount<D, EC, M, T>) =
        InductanceAmount(amount = amount * that.amount,
                         unit = U2(that.distance, that.mass) to U2(that.electricCurrent, that.time))

    // endregion

    // region -> Luminance

    operator fun <D : Unit<Distance>, LI : Unit<LuminousIntensity>> times(that: LuminanceAmount<D, LI>) =
        LuminanceAmount(amount = amount * that.amount,
                        unit = that.luminousIntensity to that.distance)

    // endregion

    // region -> LuminousEnergy

    operator fun <A : Unit<Angle>, LI : Unit<LuminousIntensity>, T : Unit<Time>> times(that: LuminousEnergyAmount<A, LI, T>) =
        LuminousEnergyAmount(amount = amount * that.amount,
                             unit = U3(that.angle, that.luminousIntensity, that.time))

    // endregion

    // region -> LuminousFlux

    operator fun <A : Unit<Angle>, LI : Unit<LuminousIntensity>> times(that: LuminousFluxAmount<A, LI>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(that.angle, that.luminousIntensity))

    // endregion

    // region -> LuminousIntensity

    operator fun <LI : Unit<LuminousIntensity>> times(that: LuminousIntensityAmount<LI>) =
        LuminousIntensityAmount(amount = amount * that.amount,
                                unit = that.luminousIntensity)

    // endregion

    // region -> MagneticFlux

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<D, EC, M, T>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(that.distance, that.mass) to U2(that.electricCurrent, that.time))

    // endregion

    // region -> MagneticFluxDensity

    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<EC, M, T>) =
        MagneticFluxDensityAmount(amount = amount * that.amount,
                                  unit = that.mass to U2(that.electricCurrent, that.time))

    // endregion

    // region -> Mass

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    // endregion

    // region -> MassFlowRate

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to that.time)

    // endregion

    // region -> Momentum

    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<D, M, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(that.distance, that.mass) to that.time)

    // endregion

    // region -> Pace

    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<D, T>) =
        PaceAmount(amount = amount * that.amount,
                   unit = that.time to that.distance)

    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = that.time to that.distance)

    // endregion

    // region -> Power

    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerAmount<D, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(that.distance, that.mass) to that.time)

    // endregion

    // region -> Rent

    operator fun <C : Unit<Currency>, T : Unit<Time>> times(that: RentAmount<C, T>) =
        RentAmount(amount = amount * that.amount,
                   unit = that.currency to that.time)

    // endregion

    // region -> SolidAngle

    operator fun <A : Unit<Angle>> times(that: SolidAngleAmount<A>) =
        SolidAngleAmount(amount = amount * that.amount,
                         unit = that.angle)

    // endregion

    // region -> Speed

    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<D, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = that.distance to that.time)

    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = that.distance to that.time)

    // endregion

    // region -> Temperature

    operator fun <K : Unit<Temperature>> times(that: TemperatureAmount<K>) =
        TemperatureAmount(amount = amount * that.amount,
                          unit = that.temperature)

    // endregion

    // region -> Time

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        TimeAmount(amount = amount * that.amount,
                   unit = that.time)

    // endregion

    // region -> Volume

    operator fun <D : Unit<Distance>> times(that: VolumeAmount<D>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = that.distance)

    // endregion
}