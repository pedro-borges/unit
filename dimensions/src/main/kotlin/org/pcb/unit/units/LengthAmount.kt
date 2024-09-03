package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.scalar.NaturalAmount

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
        LengthAmount(amount = amount + that.amount * convert(that.length to length),
                     length = length)

    @JvmName("plusLength")
    operator fun plus(that: LengthAmount<L>) =
        LengthAmount(amount = amount + that.amount,
                     length = length)

    operator fun <L : Unit<Length>> minus(that: LengthAmount<L>) =
        LengthAmount(amount = amount - that.amount * convert(that.length to length),
                     length = length)

    @JvmName("minusLength")
    operator fun minus(that: LengthAmount<L>) =
        LengthAmount(amount = amount - that.amount,
                     length = length)

    // endregion

    // region Scalar

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        NaturalAmount(amount = amount / that.amount / convert(that.length to length))

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

    // region Area

    operator fun <L : Unit<Length>> times(that: LengthAmount<L>) =
        AreaAmount(amount = amount * that.amount * convert(that.length to length),
                   length = length,
                   length2 = length)

    @JvmName("timesLength")
    operator fun times(that: LengthAmount<L>) =
        AreaAmount(amount = amount * that.amount,
                   length = length,
                   length2 = length)

    // endregion

    // region Volume

    operator fun <L : Unit<Length>> times(that: AreaAmount<L>) =
        VolumeAmount(amount = amount * that.amount * convert(that.length to length,
                                                             that.length to length),
                     length = length,
                     length2 = length,
                     length3 = length)

    @JvmName("timesArea")
    operator fun times(that: AreaAmount<L>) =
        VolumeAmount(amount = amount * that.amount,
                     length = length,
                     length2 = length,
                     length3 = length)

    // endregion
}