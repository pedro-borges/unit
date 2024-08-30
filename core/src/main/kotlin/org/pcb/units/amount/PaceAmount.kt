package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.convert
import org.pcb.units.div
import org.pcb.units.domain.Length
import org.pcb.units.domain.Pace
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Time
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

open class PaceAmount<T : Unit<Time>, L : Unit<Length>>(
        amount: Number,
        val time: T,
        val length: L)
    : Amount<Pace>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Pace::class,
                       units = listOf(time to 1,
                                      length to -1)))
{
    // region Pace

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PaceAmount(amount = amount * that.amount * that.unit.scale,
                   time = time,
                   length = length)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PaceAmount(amount = amount / that.amount / that.unit.scale,
                   time = time,
                   length = length)

    operator fun <T : Unit<Time>, L : Unit<Length>> plus(that: PaceAmount<T, L>) =
        PaceAmount(amount = amount + that.amount * convert(that.time to time,
                                                           length to that.length),
                   time = time,
                   length = length)

    @JvmName("plusPace")
    operator fun plus(that: PaceAmount<T, L>) =
        PaceAmount(amount = amount + that.amount,
                   time = time,
                   length = length)

    operator fun <T : Unit<Time>, L : Unit<Length>> minus(that: PaceAmount<T, L>) =
        PaceAmount(amount = amount - that.amount * convert(that.time to time,
                                                           length to that.length),
                   time = time,
                   length = length)

    @JvmName("minusPace")
    operator fun minus(that: PaceAmount<T, L>) =
        PaceAmount(amount = amount - that.amount,
                   time = time,
                   length = length)

    // endregion

    // region Scalar

    operator fun <T : Unit<Time>, L : Unit<Length>> div(that: PaceAmount<T, L>) =
        NaturalAmount(amount = amount / that.amount / convert(that.time to time,
                                                              length to that.length))

    @JvmName("divPace")
    operator fun div(that: PaceAmount<T, L>) =
        NaturalAmount(amount = amount / that.amount)

    operator fun <L : Unit<Length>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.time to time,
                                                              length to that.length))

    @JvmName("timesSpeed")
    operator fun times(that: SpeedAmount<L, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Time

    operator fun <L : Unit<Length>> times(that: LengthAmount<L>) =
        TimeAmount(amount = amount * that.amount * convert(that.length to length),
                   time = time)

    @JvmName("timesLength")
    operator fun times(that: LengthAmount<L>) =
        TimeAmount(amount = amount * that.amount,
                   time = time)

    // endregion
}