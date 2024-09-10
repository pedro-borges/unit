package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Pressure
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric

open class PressureAmount<M : Unit<Mass>, D : Unit<Distance>, T2 : Unit<Time>>(
        amount: Number,
        unit: Pair<M, U2<D, T2>>)
    : Amount<Pressure>(amount = amount.toBigBigDecimal(),
                       unit = CompositeUnit(
                           domain = Pressure::class,
                           units = listOf(unit.major to 1,
                                          unit.minor.first to -1,
                                          unit.minor.second to -2)))
{
    val mass = unit.major
    val distance = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Pressure>) =
            PressureAmount(amount = amount.toBigBigDecimal() * unit.scale,
                           unit = KILOGRAM to U2(METER, SECOND))
    }

    // region -> Force

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PressureAmount(amount = amount * that.amount * that.unit.scale,
                       unit = mass to U2(distance, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PressureAmount(amount = amount / that.amount / that.unit.scale,
                       unit = mass to U2(distance, time))

    @JvmName("plusPressure")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> plus(that: PressureAmount<M, D, T>) =
        PressureAmount(amount = amount + that.amount
                                * convert(that.mass to mass)
                                / convert(that.distance to distance,
                                          that.time to time,
                                          that.time to time),
                       unit = mass to U2(distance, time))

    operator fun plus(that: PressureAmount<M, D, T2>) =
        PressureAmount(amount = amount + that.amount,
                       unit = mass to U2(distance, time))

    @JvmName("minusPressure")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> minus(that: PressureAmount<M, D, T>) =
        PressureAmount(amount = amount - that.amount
                                * convert(that.mass to mass)
                                / convert(that.distance to distance,
                                          that.time to time,
                                          that.time to time),
                       unit = mass to U2(distance, time))

    operator fun minus(that: PressureAmount<M, D, T2>) =
        PressureAmount(amount = amount - that.amount,
                       unit = mass to U2(distance, time))

    // endregion

    // region -> Natural

    @JvmName("divPressure")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: PressureAmount<M, D, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.mass to mass)
                         * convert(that.distance to distance,
                                   that.time to time,
                                   that.time to time))

    operator fun div(that: PressureAmount<M, D, T2>) =
        Numeric(amount = amount / that.amount)

    // endregion
}