package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Currency
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.NaturalAmount
import org.pcb.unit.units.scalar.NaturalAmount.ONE

open class FrequencyAmount<T : Unit<Time>>(
        amount: Number,
        unit: Pair<Unit<Scalar>, T>)
    : Amount<Time>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Time::class,
                       units = listOf(unit.second to -1)))
{
    val time = unit.second

    // region Frequency

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        FrequencyAmount(amount = amount * that.amount * that.unit.scale,
                        unit = ONE / time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        FrequencyAmount(amount = amount / that.amount / that.unit.scale,
                        unit = ONE / time)

    operator fun <T : Unit<Time>> plus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount + that.amount / convert(that.time to time),
                        unit = ONE / time)

    @JvmName("plusFrequency")
    operator fun plus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount + that.amount,
                        unit = ONE / time)

    operator fun <T : Unit<Time>> minus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount - that.amount / convert(that.time to time),
                        unit = ONE / time)

    @JvmName("minusFrequency")
    operator fun minus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount - that.amount,
                        unit = ONE / time)

    // endregion

    // region Scalar

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        NaturalAmount(amount = amount / that.amount * convert(that.time to time))

    @JvmName("divFrequency")
    operator fun div(that: FrequencyAmount<T>) =
        NaturalAmount(amount = amount / that.amount)

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        NaturalAmount(amount = amount * that.amount / convert(that.time to time))

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        NaturalAmount(amount = amount * that.amount)

    // endregion

    // region Speed

    operator fun <L : Unit<Length>> times(that: LengthAmount<L>) =
        SpeedAmount(amount = that.amount / amount,
                    unit = that.length / time)

    // endregion

    // region Acceleration

    operator fun <L : Unit<Length>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        AccelerationAmount(amount = that.amount / amount / convert(that.time to time),
                           unit = that.length / (time * time))

    @JvmName("timesSpeed")
    operator fun <L : Unit<Length>> times(that: SpeedAmount<L, T>) =
        AccelerationAmount(amount = that.amount / amount,
                           unit = that.length / (time * time))

    // endregion

    // region Currency

    operator fun <C : Unit<Currency>, T : Unit<Time>> times(that: DebtAmount<C, T>) =
        CurrencyAmount(amount = that.amount / amount * convert(that.time to time),
                       unit = that.currency)

    @JvmName("timesDebt")
    operator fun <C : Unit<Currency>> times(that: DebtAmount<C, T>) =
        CurrencyAmount(amount = that.amount / amount,
                       unit = that.currency)

    // endregion

    // region Rent

    operator fun <C : Unit<Currency>> times(that: CurrencyAmount<C>) =
        RentAmount(amount = that.amount / amount,
                   major = that.currency,
                   minor = time,
                   unit = that.currency / time)

    // endregion
}
