package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Currency
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

open class FrequencyAmount<T : Unit<Time>>(
        amount: Number,
        val time: T)
    : Amount<Time>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Time::class,
                       units = listOf(time to -1)))
{
    // region Frequency

    operator fun plus(that: FrequencyAmount<*>) =
        FrequencyAmount(amount = amount + that.amount * convert(that.time to time),
                        time = time)

    @JvmName("plusT")
    operator fun plus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount + that.amount,
                        time = time)

    operator fun minus(that: FrequencyAmount<*>) =
        FrequencyAmount(amount = amount - that.amount * convert(that.time to time),
                        time = time)

    @JvmName("minusT")
    operator fun minus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount - that.amount,
                        time = time)

    // endregion

    // region Scalar

    operator fun div(that: FrequencyAmount<*>) =
        NaturalAmount(amount = amount / that.amount / convert(that.time to time))

    @JvmName("divT")
    operator fun div(that: FrequencyAmount<T>) =
        NaturalAmount(amount = amount / that.amount)

    operator fun times(that: TimeAmount<*>) =
        NaturalAmount(amount = amount * that.amount * convert(that.time to time))

    @JvmName("timesT")
    operator fun times(that: TimeAmount<T>) =
        NaturalAmount(amount = amount * that.amount)

    // endregion

    // region Speed

    operator fun <S : Unit<Space>> times(that: SpaceAmount<S>) =
        SpeedAmount(amount = amount * that.amount,
                    space = that.space,
                    time = time)

    // endregion

    // region Acceleration

    operator fun <S : Unit<Space>, T : Unit<Time>> times(that: SpeedAmount<S, T>) =
        AccelerationAmount(amount = amount * that.amount,
                           space = that.space,
                           time1 = that.time,
                           time2 = time)

    // endregion

    // region Rent

    operator fun <C : Unit<Currency>> times(that: CurrencyAmount<C>) =
        RentAmount(amount = amount * that.amount,
                   currency = that.currency,
                   time = time)

    // endregion
}
