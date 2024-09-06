package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Currency
import org.pcb.unit.dimension.Frequency
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric.ONE

open class FrequencyAmount<T : Unit<Time>>(
        amount: Number,
        unit: Pair<Unit<Scalar>, T>)
    : Amount<Frequency>(amount = amount.toBigBigDecimal(),
                        unit = CompositeUnit(
                            domain = Frequency::class,
                            units = listOf(unit.minor to -1)))
{
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Frequency>) =
            FrequencyAmount(amount = amount.toBigBigDecimal() / unit.scale,
                            unit = Pair(ONE, SECOND))
    }

    // region -> Frequency

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        FrequencyAmount(amount = amount * that.amount * that.unit.scale,
                        unit = Pair(ONE, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        FrequencyAmount(amount = amount / that.amount / that.unit.scale,
                        unit = Pair(ONE, time))

    operator fun <T : Unit<Time>> plus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount + that.amount / convert(that.time to time),
                        unit = Pair(ONE, time))

    @JvmName("plusFrequency")
    operator fun plus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount + that.amount,
                        unit = Pair(ONE, time))

    operator fun <T : Unit<Time>> minus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount - that.amount / convert(that.time to time),
                        unit = Pair(ONE, time))

    @JvmName("minusFrequency")
    operator fun minus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount - that.amount,
                        unit = Pair(ONE, time))

    // endregion

    // region -> Natural

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        amount / that.amount * convert(that.time to time)

    @JvmName("divFrequency")
    operator fun div(that: FrequencyAmount<T>) =
        amount / that.amount

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        amount * that.amount / convert(that.time to time)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        amount * that.amount

    // endregion

    // region -> Speed

    operator fun <L : Unit<Length>> times(that: LengthAmount<L>) =
        SpeedAmount(amount = that.amount / amount,
                    unit = Pair(that.length, time))

    // endregion

    // region -> Acceleration

    operator fun <L : Unit<Length>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        AccelerationAmount(amount = that.amount / amount / convert(that.time to time),
                           unit = Pair(that.length, time))

    @JvmName("timesSpeed")
    operator fun <L : Unit<Length>> times(that: SpeedAmount<L, T>) =
        AccelerationAmount(amount = that.amount / amount,
                           unit = Pair(that.length, time))

    // endregion

    // region -> Currency

    operator fun <C : Unit<Currency>, T : Unit<Time>> times(that: DebtAmount<C, T>) =
        CurrencyAmount(amount = that.amount / amount * convert(that.time to time),
                       unit = that.currency)

    @JvmName("timesDebt")
    operator fun <C : Unit<Currency>> times(that: DebtAmount<C, T>) =
        CurrencyAmount(amount = that.amount / amount,
                       unit = that.currency)

    // endregion

    // region -> Rent

    operator fun <C : Unit<Currency>> times(that: CurrencyAmount<C>) =
        RentAmount(amount = that.amount / amount,
                   unit = Pair(that.currency, time))

    // endregion
}
