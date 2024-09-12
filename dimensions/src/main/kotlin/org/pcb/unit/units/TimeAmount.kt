package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.base.Numeric
import org.pcb.unit.units.base.Numeric.NUMERIC

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

    // region -> Natural

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        Numeric(amount = amount / that.amount / convert(that.time to time))

    operator fun div(that: TimeAmount<T>) =
        Numeric(amount = amount / that.amount)

    @JvmName("timesHertz")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        Numeric(amount = amount * that.amount / convert(that.time to time))

    operator fun times(that: FrequencyAmount<T>) =
        Numeric(amount = amount * that.amount)

    // endregion

    // region -> Pace

    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to that.distance)

    // endregion

    // region -> Mass

    @JvmName("timesMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        MassAmount(amount = amount * that.amount / convert(that.time to time),
                   unit = that.mass)

    operator fun <M : Unit<Mass>> times(that: MassFlowRateAmount<M, T>) =
        MassAmount(amount = amount * that.amount,
                   unit = that.mass)

    // endregion

    // region -> Energy

    @JvmName("timesPower")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> times(that: PowerAmount<M, D, T>) =
        EnergyAmount(amount = amount * that.amount / convert(that.time to time,
                                                             that.time to time,
                                                             that.time to time),
                     unit = U2(that.mass, that.distance) to time)

    operator fun <M : Unit<Mass>, D : Unit<Distance>> times(that: PowerAmount<M, D, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.mass, that.distance) to time)

    // endregion

    // region -> ElectricCharge

    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(that.electricCurrent, time))

    // endregion

    // region -> Capacitance

    @JvmName("divElectricResistance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<M, D, T, EC>) =
        CapacitanceAmount(amount = amount / that.amount * convert(that.time to time,
                                                                  that.time to time,
                                                                  that.time to time),
                          unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> div(that: ElectricResistanceAmount<M, D, T, EC>) =
        CapacitanceAmount(amount = amount / that.amount,
                          unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    @JvmName("timesElectricConductance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> times(that: ElectricConductanceAmount<T, EC, M, D>) =
        CapacitanceAmount(amount = amount * that.amount * convert(that.time to time,
                                                                  that.time to time,
                                                                  that.time to time),
                          unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> times(that: ElectricConductanceAmount<T, EC, M, D>) =
        CapacitanceAmount(amount = amount * that.amount,
                          unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> ElectricResistance

    @JvmName("divCapacitance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: CapacitanceAmount<T, EC, M, D>) =
        ElectricResistanceAmount(amount = amount / that.amount / convert(that.time to time,
                                                                         that.time to time,
                                                                         that.time to time,
                                                                         that.time to time),
                                 unit = U2(that.mass, that.distance) to U2(time, that.electricCurrent))

    operator fun <M : Unit<Mass>, D : Unit<Distance>, EC : Unit<ElectricCurrent>> div(that: CapacitanceAmount<T, EC, M, D>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(that.mass, that.distance) to U2(time, that.electricCurrent))

    // endregion

    // region -> MagneticFlux

    @JvmName("timesElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricTensionAmount<M, D, T, EC>) =
        MagneticFluxAmount(amount = amount * that.amount * convert(that.time to time,
                                                                   that.time to time,
                                                                   that.time to time),
                           unit = U2(that.mass, that.distance) to U2(time, that.electricCurrent))

    operator fun <M : Unit<Mass>, D : Unit<Distance>, EC : Unit<ElectricCurrent>> times(that: ElectricTensionAmount<M, D, T, EC>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(that.mass, that.distance) to U2(time, that.electricCurrent))

    // endregion
}
