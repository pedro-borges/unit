package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.T2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal

open class TimeAmount<T : Unit<Time>>(
        amount: Number,
        unit: T)
    : Amount<Time>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Time::class,
                       units = listOf(unit to 1)))
{
    val time = unit

    // region -> Time

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        TimeAmount(amount = amount * that.amount * that.unit.scale,
                   unit = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        TimeAmount(amount = amount / that.amount / that.unit.scale,
                   unit = time)

    operator fun <T : Unit<Time>> plus(that: TimeAmount<T>) =
        TimeAmount(amount = amount + that.amount * convert(that.time to time),
                   unit = time)

    @JvmName("plusTime")
    operator fun plus(that: TimeAmount<T>) =
        TimeAmount(amount = amount + that.amount,
                   unit = time)

    operator fun <T : Unit<Time>> minus(that: TimeAmount<T>) =
        TimeAmount(amount = amount - that.amount * convert(that.time to time),
                   unit = time)

    @JvmName("minusTime")
    operator fun minus(that: TimeAmount<T>) =
        TimeAmount(amount = amount - that.amount,
                   unit = time)

    // endregion

    // region -> Natural

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        amount / that.amount / convert(that.time to time)

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T>) =
        amount / that.amount

    // endregion

    // region -> Pace

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to that.length)

    // endregion

    // region -> Energy

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: PowerAmount<M, L, T>) =
        EnergyAmount(amount = amount * that.amount / convert(that.time to time,
                                                             that.time to time,
                                                             that.time to time),
                     unit = T2(that.mass, that.length) to time)

    @JvmName("timesPower")
    operator fun <M : Unit<Mass>, L : Unit<Length>> times(that: PowerAmount<M, L, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = T2(that.mass, that.length) to time)

    // endregion

    // region -> ElectricCharge

    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = T2(that.electricCurrent, time))

    // endregion

    // region -> ElectricCapacitance

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricCapacitanceAmount(amount = amount / that.amount * convert(that.time to time,
                                                                          that.time to time,
                                                                          that.time to time),
                                  unit = T2(time, that.electricCurrent) to T2(that.mass, that.length))

    @JvmName("divElectricResistance")
    operator fun <M : Unit<Mass>, L : Unit<Length>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricCapacitanceAmount(amount = amount / that.amount,
                                  unit = T2(time, that.electricCurrent) to T2(that.mass, that.length))

    // endregion

    // region -> ElectricResistance

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricCapacitanceAmount<T, EC, M, L>) =
        ElectricResistanceAmount(amount = amount / that.amount / convert(that.time to time,
                                                                         that.time to time,
                                                                         that.time to time,
                                                                         that.time to time),
                                 unit = T2(that.mass, that.length) to T2(time, that.electricCurrent))

    @JvmName("divElectricCapacitance")
    operator fun <M : Unit<Mass>, L : Unit<Length>, EC : Unit<ElectricCurrent>> div(that: ElectricCapacitanceAmount<T, EC, M, L>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = T2(that.mass, that.length) to T2(time, that.electricCurrent))

    // endregion

    // region -> MagneticFlux

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        MagneticFluxAmount(amount = amount * that.amount * convert(that.time to time,
                                                                   that.time to time,
                                                                   that.time to time),
                           unit = T2(that.mass, that.length) to T2(time, that.electricCurrent))

    @JvmName("timesElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>, EC : Unit<ElectricCurrent>> times(that: ElectricTensionAmount<M, L, T, EC>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = T2(that.mass, that.length) to T2(time, that.electricCurrent))

    // endregion
}
