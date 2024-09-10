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
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.Numeric

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

    // endregion

    // region -> Pace

    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to that.distance)

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

    // region -> ElectricCapacitance

    @JvmName("divElectricResistance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<M, D, T, EC>) =
        ElectricCapacitanceAmount(amount = amount / that.amount * convert(that.time to time,
                                                                          that.time to time,
                                                                          that.time to time),
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> div(that: ElectricResistanceAmount<M, D, T, EC>) =
        ElectricCapacitanceAmount(amount = amount / that.amount,
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    @JvmName("timesElectricConductivity")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> times(that: ElectricConductivityAmount<T, EC, M, D>) =
        ElectricCapacitanceAmount(amount = amount * that.amount * convert(that.time to time,
                                                                          that.time to time,
                                                                          that.time to time),
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> times(that: ElectricConductivityAmount<T, EC, M, D>) =
        ElectricCapacitanceAmount(amount = amount * that.amount,
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))


    // endregion

    // region -> ElectricResistance

    @JvmName("divElectricCapacitance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricCapacitanceAmount<T, EC, M, D>) =
        ElectricResistanceAmount(amount = amount / that.amount / convert(that.time to time,
                                                                         that.time to time,
                                                                         that.time to time,
                                                                         that.time to time),
                                 unit = U2(that.mass, that.distance) to U2(time, that.electricCurrent))

    operator fun <M : Unit<Mass>, D : Unit<Distance>, EC : Unit<ElectricCurrent>> div(that: ElectricCapacitanceAmount<T, EC, M, D>) =
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
