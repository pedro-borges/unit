package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Length
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Time
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

open class LengthAmount<L : Unit<Length>>(
        amount: Number,
        val length: L)
    : Amount<Length>(amount = amount.toBigBigDecimal(),
                     unit = CompositeUnit(
                         domain = Length::class,
                         units = listOf(length to 1)))
{
    // region Length

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        LengthAmount(amount = amount * that.amount * that.unit.scale,
                     length = length)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        LengthAmount(amount = amount / that.amount / that.unit.scale,
                     length = length)

    operator fun <L : Unit<Length>> plus(that: LengthAmount<L>) =
        LengthAmount(amount = amount + that.amount * Companion.convert(that.length to length),
                     length = length)

    @JvmName("plusLength")
    operator fun plus(that: LengthAmount<L>) =
        LengthAmount(amount = amount + that.amount,
                     length = length)

    operator fun <L : Unit<Length>> minus(that: LengthAmount<L>) =
        LengthAmount(amount = amount - that.amount * Companion.convert(that.length to length),
                     length = length)

    @JvmName("minusLength")
    operator fun minus(that: LengthAmount<L>) =
        LengthAmount(amount = amount - that.amount,
                     length = length)

    // endregion

    // region Scalar

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        NaturalAmount(amount = amount / that.amount / Companion.convert(that.length to length))

    @JvmName("divLength")
    operator fun div(that: LengthAmount<L>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Speed

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        SpeedAmount(amount = amount / that.amount,
                    length = length,
                    time = that.time)

    // endregion
}