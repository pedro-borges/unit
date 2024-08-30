package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Length
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Speed
import org.pcb.units.domain.Time
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

open class SpeedAmount<L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        val length: L,
        val time: T)
    : Amount<Speed>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Speed::class,
                        units = listOf(length to 1,
                                       time to -1)))
{
    // region Speed

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpeedAmount(amount = amount * that.amount * that.unit.scale,
                    length = length,
                    time = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpeedAmount(amount = amount / that.amount / that.unit.scale,
                    length = length,
                    time = time)

    operator fun <L : Unit<Length>, T : Unit<Time>> plus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount + that.amount * Companion.convert(that.length to length,
                                                                      time to that.time),
                    length = length,
                    time = time)

    @JvmName("plusSpeed")
    operator fun plus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount + that.amount,
                    length = length,
                    time = time)

    operator fun <L : Unit<Length>, T : Unit<Time>> minus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount - that.amount * Companion.convert(that.length to length,
                                                                      time to that.time),
                    length = length,
                    time = time)

    @JvmName("minusSpeed")
    operator fun minus(that: SpeedAmount<L, T>) =
        SpeedAmount(amount = amount - that.amount,
                    length = length,
                    time = time)

    // endregion

    // region Scalar
    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        NaturalAmount(amount = amount / that.amount / Companion.convert(that.length to length, time to that.time))

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<L, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Length

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        LengthAmount(amount = amount * that.amount * Companion.convert(that.time to time),
                     length = length)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        LengthAmount(amount = amount * that.amount,
                     length = length)

    // endregion

    // region Acceleration

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        AccelerationAmount(amount = amount / that.amount / Companion.convert(that.time to time),
                           length = length,
                           time = time,
                           time2 = time)

    // endregion

    // region Frequency

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        FrequencyAmount(amount = amount / that.amount / Companion.convert(that.length to length),
                        time = time)

    @JvmName("divLength")
    operator fun div(that: LengthAmount<L>) =
        FrequencyAmount(amount = amount / that.amount,
                        time = time)

    // endregion
}