package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Currency
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
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
        TimeAmount(amount = amount + that.amount * convert(that.time to time),
                   unit = time)

    operator fun plus(that: TimeAmount<T>) =
        TimeAmount(amount = amount + that.amount,
                   unit = time)

    @JvmName("minusTime")
    operator fun <T : Unit<Time>> minus(that: TimeAmount<T>) =
        TimeAmount(amount = amount - that.amount * convert(that.time to time),
                   unit = time)

    operator fun minus(that: TimeAmount<T>) =
        TimeAmount(amount = amount - that.amount,
                   unit = time)

    // endregion

    // region -> Capacitance

    @JvmName("timesElectricConductance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductanceAmount<D, EC, M, T>) =
        CapacitanceAmount(amount = amount * that.amount
                                   * convert(that.time to time,
                                             that.time to time,
                                             that.time to time),
                          unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: ElectricConductanceAmount<D, EC, M, T>) =
        CapacitanceAmount(amount = amount * that.amount,
                          unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    @JvmName("divElectricResistance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricResistanceAmount<D, EC, M, T>) =
        CapacitanceAmount(amount = amount / that.amount
                                   * convert(that.time to time,
                                             that.time to time,
                                             that.time to time),
                          unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>> div(that: ElectricResistanceAmount<D, EC, M, T>) =
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
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<D, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.time to time),
                       unit = that.distance)

    operator fun <D : Unit<Distance>> div(that: PaceAmount<D, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = that.distance)

    @JvmName("timesSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        DistanceAmount(amount = amount * that.amount
                                / convert(that.time to time),
                       unit = that.distance)

    operator fun <D : Unit<Distance>> times(that: SpeedAmount<D, T>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = that.distance)

    // endregion

    // region -> ElectricCharge

    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(that.electricCurrent, time))

    // endregion

    // region -> ElectricConductance

    @JvmName("divInductance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: InductanceAmount<D, EC, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount
                                           * convert(that.time to time,
                                                     that.time to time),
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>> div(that: InductanceAmount<D, EC, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount,
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> ElectricResistance

    @JvmName("divCapacitance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: CapacitanceAmount<D, EC, M, T>) =
        ElectricResistanceAmount(amount = amount / that.amount
                                          / convert(that.time to time,
                                                    that.time to time,
                                                    that.time to time,
                                                    that.time to time),
                                 unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>> div(that: CapacitanceAmount<D, EC, M, T>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesPower")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: PowerAmount<D, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = U2(that.distance, that.mass) to time)

    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: PowerAmount<D, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> Inductance

    @JvmName("divElectricConductance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<D, EC, M, T>) =
        InductanceAmount(amount = amount / that.amount
                                  / convert(that.time to time,
                                            that.time to time,
                                            that.time to time),
                         unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>> div(that: ElectricConductanceAmount<D, EC, M, T>) =
        InductanceAmount(amount = amount / that.amount,
                         unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    @JvmName("timesElectricResistance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<D, EC, M, T>) =
        InductanceAmount(amount = amount * that.amount
                                  / convert(that.time to time,
                                            that.time to time,
                                            that.time to time),
                         unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: ElectricResistanceAmount<D, EC, M, T>) =
        InductanceAmount(amount = amount * that.amount,
                         unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> LuminousEnergy

    operator fun <A : Unit<Angle>, LI : Unit<LuminousIntensity>> times(that: LuminousFluxAmount<A, LI>) =
        LuminousEnergyAmount(amount = amount * that.amount,
                             unit = U3(that.angle, that.luminousIntensity, time))

    // endregion

    // region -> MagneticFlux

    @JvmName("timesElectricTension")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<D, EC, M, T>) =
        MagneticFluxAmount(amount = amount * that.amount
                                    / convert(that.time to time,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(that.distance, that.mass) to U2(that.electricCurrent, time))

    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>> times(that: ElectricTensionAmount<D, EC, M, T>) =
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

    // region -> Momentum

    @JvmName("timesForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: ForceAmount<D, M, T>) =
        MomentumAmount(amount = amount * that.amount
                                / convert(that.time to time,
                                          that.time to time),
                       unit = U2(that.distance, that.mass) to time)

    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: ForceAmount<D, M, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(that.distance, that.mass) to time)

    // endregion

    // region -> Pace

    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to that.distance)

    // endregion

    // region -> Scalar

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        Numeric(amount = amount * that.amount
                         / convert(that.time to time))

    operator fun times(that: FrequencyAmount<T>) =
        Numeric(amount = amount * that.amount)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.time to time))

    operator fun div(that: TimeAmount<T>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Speed

    @JvmName("timesAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<D, T>) =
        SpeedAmount(amount = amount * that.amount
                             / convert(that.time to time,
                                       that.time to time),
                    unit = that.distance to time)

    operator fun <D : Unit<Distance>> times(that: AccelerationAmount<D, T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = that.distance to time)

    // endregion
}
